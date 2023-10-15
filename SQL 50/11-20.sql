-- [577](https://leetcode.com/problems/employee-bonus/description/)
SELECT e.name, b.bonus
FROM Employee e
         LEFT JOIN Bonus b
                   USING (empId)
WHERE IFNULL(b.bonus, 0) < 1000;

-- [1280](https://leetcode.com/problems/students-and-examinations/description/)
SELECT s.student_id,
       s.student_name,
       sub.subject_name,
       COUNT(e.student_id) attended_exams
FROM Students s
         CROSS JOIN Subjects sub
         LEFT JOIN Examinations e
                   ON s.student_id = e.student_id AND sub.subject_name = e.subject_name
GROUP BY s.student_id, sub.subject_name
ORDER BY 1, 3;

-- [570](https://leetcode.com/problems/managers-with-at-least-5-direct-reports/description/)
SELECT a.name
FROM Employee a
         JOIN Employee b
              ON a.id = b.managerId
GROUP BY b.managerId
HAVING COUNT(b.managerId) >= 5;

-- [1934](https://leetcode.com/problems/confirmation-rate/description/)
SELECT s.user_id,
       ROUND(AVG(IF(c.action = 'confirmed', 1, 0)), 2) AS 'confirmation_rate'
FROM Signups s
         LEFT JOIN Confirmations c
                   USING (user_id)
GROUP BY s.user_id;

-- [620](https://leetcode.com/problems/not-boring-movies/description/)
SELECT *
FROM Cinema
WHERE id % 2 = 1
  AND description <> 'boring'
ORDER BY rating DESC;

-- [1251](https://leetcode.com/problems/average-selling-price/)
SELECT p.product_id, COALESCE(ROUND(SUM(p.price * u.units) / SUM(u.units), 2), 0) average_price
FROM Prices p
         LEFT JOIN UnitsSold u
                   ON
                               p.product_id = u.product_id
                           AND purchase_date BETWEEN p.start_date AND p.end_date
GROUP BY 1;

-- [1075](https://leetcode.com/problems/project-employees-i/description/)
SELECT p.project_id,
       ROUND(AVG(e.experience_years), 2) average_years
FROM Project p
         JOIN Employee e
              USING (employee_id)
GROUP BY p.project_id;

-- [1633](https://leetcode.com/problems/percentage-of-users-attended-a-contest/)
SELECT r.contest_id,
       ROUND(COUNT(r.user_id) / (SELECT COUNT(*) FROM Users u) * 100, 2) percentage
FROM Register r
GROUP BY r.contest_id
ORDER BY 2 DESC, 1;

-- [1211](https://leetcode.com/problems/queries-quality-and-percentage/)
SELECT query_name,
       ROUND(AVG(rating / position), 2)                                                quality,
       ROUND(SUM(CASE WHEN rating < 3 THEN 1 ELSE 0 END) / COUNT(query_name) * 100, 2) poor_query_percentage
FROM Queries
GROUP BY 1;

-- [1193](https://leetcode.com/problems/monthly-transactions-i/description/)
SELECT DATE_FORMAT(trans_date, '%Y-%m')       month,
       country,
       COUNT(*)                               trans_count,
       SUM(IF(state = 'approved', 1, 0))      approved_count,
       SUM(amount)                            trans_total_amount,
       SUM(IF(state = 'approved', amount, 0)) approved_total_amount
FROM Transactions t
GROUP BY 1, 2;