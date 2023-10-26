-- [1532](https://leetcode.com/problems/the-most-recent-three-orders/)
WITH cte AS (SELECT *,
                    RANK() OVER (PARTITION BY customer_id ORDER BY order_date DESC) rnk
             FROM Orders)
SELECT c.name customer_name,
       cte.customer_id,
       cte.order_id,
       cte.order_date
FROM cte
         JOIN Customers c USING (customer_id)
WHERE rnk <= 3
ORDER BY 1, 2, 4 DESC;

-- [1831](https://leetcode.com/problems/maximum-transaction-each-day/)
SELECT transaction_id
FROM (SELECT *,
             DENSE_RANK() OVER (PARTITION BY DATE(day) ORDER BY amount DESC) dr
      FROM Transactions) t
WHERE dr = 1
ORDER BY 1;

-- [1077](https://leetcode.com/problems/project-employees-iii/description/)
SELECT project_id,
       employee_id
FROM (SELECT *,
             DENSE_RANK() OVER (PARTITION BY project_id ORDER BY experience_years DESC) dr
      FROM Project
               NATURAL JOIN Employee) cte
WHERE dr = 1;

-- [1285](https://leetcode.com/problems/find-the-start-and-end-number-of-continuous-ranges/description/)
SELECT MIN(log_id) start_id,
       MAX(log_id) end_id
FROM (SELECT log_id,
             ROW_NUMBER() OVER (ORDER BY log_id) rn
      FROM Logs) t
GROUP BY log_id - rn;

-- [1596](https://leetcode.com/problems/the-most-frequently-ordered-products-for-each-customer/)
SELECT customer_id,
       product_id,
       product_name
FROM (SELECT *,
             RANK() OVER (PARTITION BY customer_id ORDER BY COUNT(product_id) DESC) rnk
      FROM Orders
               NATURAL JOIN Products
      GROUP BY customer_id, product_id) t
WHERE rnk = 1;

-- [1709](https://leetcode.com/problems/biggest-window-between-visits/description/)
SELECT user_id,
       MAX(diff) biggest_window
FROM (SELECT user_id,
             DATEDIFF(LEAD(visit_date, 1, '2021-1-1') OVER (PARTITION BY user_id ORDER BY visit_date),
                      visit_date) diff
      FROM UserVisits) t
GROUP BY 1
ORDER BY 1;

-- [1270](https://leetcode.com/problems/all-people-report-to-the-given-manager/)
WITH RECURSIVE cte AS (SELECT employee_id
                       FROM Employees
                       WHERE manager_id = 1
                         AND employee_id != 1
                       UNION ALL
                       SELECT e.employee_id
                       FROM Employees e
                                JOIN cte ON
                           e.manager_id = cte.employee_id)

SELECT employee_id
FROM cte
ORDER BY employee_id;

-- [1412](https://leetcode.com/problems/find-the-quiet-students-in-all-exams/)
WITH highest_lowest_scores AS (SELECT exam_id, MAX(score) max_score, MIN(score) min_score
                               FROM Exam
                               GROUP BY exam_id),
     not_valid_ids AS (SELECT DISTINCT e.student_id
                       FROM Exam e
                                NATURAL JOIN highest_lowest_scores b
                       WHERE e.score = b.min_score
                          OR e.score = b.max_score

                       UNION

                       SELECT student_id
                       FROM Student
                       WHERE student_id NOT IN (SELECT DISTINCT student_id FROM Exam))
SELECT *
FROM Student
WHERE student_id NOT IN (SELECT * FROM not_valid_ids)
ORDER BY student_id;

-- [1767](https://leetcode.com/problems/find-the-subtasks-that-did-not-execute/)
WITH RECURSIVE cte AS
                   (SELECT task_id,
                           1 subtask_id,
                           subtasks_count
                    FROM Tasks
                    UNION ALL
                    SELECT task_id,
                           subtask_id + 1 subtask_id,
                           subtasks_count
                    FROM cte
                    WHERE subtasks_count > subtask_id)
SELECT task_id,
       subtask_id
FROM cte
WHERE (task_id, subtask_id) NOT IN (SELECT * FROM Executed);

-- [1225](https://leetcode.com/problems/report-contiguous-dates/)
WITH intervals AS
         (SELECT fail_date                              date,
                 'failed'                               period_state,
                 ROW_NUMBER() OVER (ORDER BY fail_date) num
          FROM Failed
          WHERE fail_date BETWEEN '2019-01-01' AND '2019-12-31'
          UNION ALL
          SELECT success_date                              date,
                 'succeeded'                               period_state,
                 ROW_NUMBER() OVER (ORDER BY success_date) num
          FROM Succeeded
          WHERE success_date BETWEEN '2019-01-01' AND '2019-12-31')
SELECT period_state,
       MIN(date) start_date,
       MAX(date) end_date
FROM intervals
GROUP BY period_state, DATE_SUB(date, INTERVAL num DAY)
ORDER BY start_date;