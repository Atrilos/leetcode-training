-- [569](https://leetcode.com/problems/median-employee-salary/description/)
WITH cte AS (SELECT id,
                    company,
                    salary,
                    COUNT(id) OVER (PARTITION BY company)                    cnt,
                    ROW_NUMBER() OVER (PARTITION BY company ORDER BY salary) rn
             FROM Employee)
SELECT id, company, salary
FROM cte
WHERE rn BETWEEN cnt::decimal / 2 AND cnt::decimal / 2 + 1;

-- [571](https://leetcode.com/problems/find-median-given-frequency-of-numbers/)
SELECT round(avg(n.num::decimal), 1) median
FROM numbers n
WHERE n.frequency >= ABS((SELECT sum(frequency) FROM numbers WHERE num <= n.num) -
                         (SELECT sum(frequency) FROM numbers WHERE num >= n.num));

-- [578](https://leetcode.com/problems/get-highest-answer-rate-question/)
SELECT question_id survey_log
FROM SurveyLog
GROUP BY 1
ORDER BY SUM(CASE WHEN action = 'answer' THEN 1 ELSE 0 END) /
         SUM(CASE WHEN action <> 'answer' THEN 1 ELSE 0 END)::numeric DESC,
         1
LIMIT 1;

-- [579](https://leetcode.com/problems/find-cumulative-salary-of-an-employee/description/)
WITH cte AS (SELECT e1.id,
                    e1.month,
                    e1.salary,
                    e2.salary                               prev_salary,
                    e3.salary                               second_prev_salary,
                    MAX(e1.month) OVER (PARTITION BY e1.id) last_month
             FROM Employee e1
                      LEFT JOIN Employee e2 ON e1.id = e2.id AND e1.month = e2.month + 1
                      LEFT JOIN Employee e3 ON e1.id = e3.id AND e1.month = e3.month + 2)
SELECT id,
       month,
       salary + COALESCE(prev_salary, 0) + COALESCE(second_prev_salary, 0) salary
FROM cte
WHERE month <> last_month
ORDER BY 1, 2 DESC;

-- [580](https://leetcode.com/problems/count-student-number-in-departments/description/)
SELECT dept_name,
       COUNT(DISTINCT student_id) student_number
FROM Department d
         LEFT JOIN Student s USING (dept_id)
GROUP BY 1
ORDER BY 2 DESC, 1;

-- [597](https://leetcode.com/problems/friend-requests-i-overall-acceptance-rate/description/)
WITH acceptance_counts AS
         (SELECT DISTINCT requester_id,
                          accepter_id
          FROM requestaccepted),
     request_counts AS (SELECT DISTINCT sender_id,
                                        send_to_id
                        FROM friendrequest),
     result_table AS
         (SELECT (SELECT count(*) FROM acceptance_counts)::NUMERIC accept_count,
                 (SELECT count(*) FROM request_counts)::NUMERIC    request_count)
SELECT CASE
           WHEN request_count != 0 THEN round(accept_count / request_count, 2)
           ELSE 0.0::NUMERIC
           END accept_rate
FROM result_table;

-- [3052](https://leetcode.com/problems/maximize-items/)
with cte as
         (select item_type,
                 sum(square_footage)                                 sum_space,
                 count(*)                                            cnt,
                 lead(sum(square_footage)) over (order by item_type) lead_space,
                 lead(count(*)) over (order by item_type)            lead_cnt
          from Inventory
          group by 1)
select item_type,
       case
           when item_type = 'prime_eligible' then floor(500000 / sum_space) * cnt
           else floor((500000 - floor(500000 / lead_space) * lead_space) / sum_space) * cnt end item_count
from cte
order by 2 desc;

-- [3051](https://leetcode.com/problems/find-candidates-for-data-scientist-position/)
SELECT candidate_id
FROM Candidates
WHERE skill IN ('Python', 'Tableau', 'PostgreSQL')
GROUP BY 1
HAVING COUNT(distinct skill) = 3
ORDER BY 1;

-- [3050](https://leetcode.com/problems/pizza-toppings-cost-analysis/description/)
SELECT DISTINCT CONCAT(t1.topping_name, ',', t2.topping_name, ',', t3.topping_name) pizza,
                ROUND(t1.cost + t2.cost + t3.cost, 2)                               total_cost
FROM toppings t1,
     toppings t2,
     toppings t3
WHERE t1.topping_name < t2.topping_name
  AND t1.topping_name < t3.topping_name
  AND t2.topping_name < t3.topping_name
ORDER BY 2 DESC;

-- [2995](https://leetcode.com/problems/viewers-turned-streamers/)
WITH cte AS (SELECT user_id,
                    CASE
                        WHEN FIRST_VALUE(session_type) OVER (PARTITION BY user_id ORDER BY session_start) = 'Viewer'
                            AND session_type = 'Streamer' THEN 1
                        ELSE 0 END session_count
             FROM Sessions)
SELECT user_id, SUM(session_count) sessions_count
FROM cte
GROUP BY 1
HAVING SUM(session_count) > 0
ORDER BY 2 DESC, 1 DESC;

-- [2994](https://leetcode.com/problems/friday-purchases-ii/description/)
WITH RECURSIVE cte AS (SELECT '2023-11-03'::date purchase_date
                       UNION ALL
                       SELECT (purchase_date + 7) purchase_date
                       FROM cte
                       WHERE purchase_date < '2023-11-30')
SELECT FLOOR(
               (EXTRACT(day FROM cte.purchase_date) / 7) + 1) week_of_month,
       cte.purchase_date,
       COALESCE(SUM(p.amount_spend), 0)                       total_amount
FROM cte
         LEFT JOIN Purchases p ON cte.purchase_date = p.purchase_date
WHERE cte.purchase_date < '2023-11-30'
GROUP BY 1, 2
ORDER BY 1;
