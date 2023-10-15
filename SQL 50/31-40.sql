-- [1789](https://leetcode.com/problems/primary-department-for-each-employee/description/)
SELECT employee_id, department_id
FROM Employee
GROUP BY employee_id
HAVING COUNT(department_id) = 1

UNION

SELECT employee_id, department_id
FROM Employee
WHERE primary_flag = 'Y';

-- [610](https://leetcode.com/problems/triangle-judgement/description/)
SELECT *,
       IF(x + y > z AND x + z > y AND y + z > x, 'Yes', 'No') triangle
FROM Triangle;

-- [180](https://leetcode.com/problems/consecutive-numbers/description/)
WITH cte as (SELECT num,
                    LEAD(num, 1) over () num1,
                    LEAD(num, 2) over () num2
             FROM Logs)
SELECT DISTINCT num ConsecutiveNums
FROM cte
WHERE num = num1
  AND num = num2;

-- [1164](https://leetcode.com/problems/product-price-at-a-given-date/description/)
WITH Last_price_change AS (SELECT product_id, MAX(change_date) last_change
                           FROM Products
                           WHERE change_date <= '2019-08-16'
                           GROUP BY product_id)
SELECT pi.product_id,
       IFNULL(p.new_price, 10) price
FROM (SELECT DISTINCT product_id FROM Products) pi
         LEFT JOIN Last_price_change lpc USING (product_id)
         LEFT JOIN Products p ON p.product_id = pi.product_id AND lpc.last_change = p.change_date
ORDER BY 1;

SELECT product_id,
       10 AS price
FROM Products
GROUP BY product_id
HAVING MIN(change_date) > '2019-08-16'

UNION ALL

SELECT product_id,
       new_price AS price
FROM Products
WHERE (product_id, change_date) IN (SELECT product_id,
                                           MAX(change_date)
                                    FROM Products
                                    WHERE change_date <= '2019-08-16'
                                    GROUP BY product_id);

-- [1204](https://leetcode.com/problems/last-person-to-fit-in-the-bus/description/)
WITH run AS
         (SELECT person_name,
                 SUM(weight) OVER (ORDER BY turn) total
          FROM Queue)
SELECT person_name
FROM run
WHERE total <= 1000
ORDER BY total DESC
LIMIT 1;

-- [1907](https://leetcode.com/problems/count-salary-categories/description/)
SELECT 'Low Salary'                                         category,
       (SELECT COUNT(*) FROM Accounts WHERE income < 20000) accounts_count
UNION ALL
SELECT 'Average Salary'                                                     category,
       (SELECT COUNT(*) FROM Accounts WHERE income BETWEEN 20000 AND 50000) accounts_count
UNION ALL
SELECT 'High Salary'                                        category,
       (SELECT COUNT(*) FROM Accounts WHERE income > 50000) accounts_count;

-- [1978](https://leetcode.com/problems/employees-whose-manager-left-the-company/description/)
SELECT employee_id
FROM Employees
WHERE manager_id NOT IN
      (SELECT employee_id
       FROM Employees)
  AND salary < 30000;

-- [626](https://leetcode.com/problems/exchange-seats/description/)
WITH tempSeat AS (SELECT *,
                         LEAD(student) OVER () AS next_student,
                         LAG(student) OVER ()  AS prev_student
                  FROM Seat)
SELECT id,
       CASE
           WHEN MOD(id, 2) = 1 AND next_student IS NULL THEN student
           WHEN MOD(id, 2) = 0 THEN prev_student
           ELSE next_student
           END AS student
FROM tempSeat;

-- [1341](https://leetcode.com/problems/movie-rating/description/)
(SELECT u.name results
 FROM MovieRating mr
          JOIN Users u USING (user_id)
 GROUP BY mr.user_id
 ORDER BY COUNT(*) DESC, 1
 LIMIT 1)

UNION ALL

(SELECT m.title results
 FROM MovieRating mr
          JOIN Movies m USING (movie_id)
 WHERE DATE_FORMAT(mr.created_at, '%Y-%m') = '2020-02'
 GROUP BY movie_id
 ORDER BY AVG(mr.rating) DESC, 1
 LIMIT 1);

-- [1321](https://leetcode.com/problems/restaurant-growth/description/)
SELECT visited_on, amount, ROUND(amount / 7, 2) average_amount
FROM (SELECT DISTINCT visited_on,
                      SUM(amount)
                          OVER (ORDER BY visited_on RANGE BETWEEN INTERVAL 6 DAY PRECEDING AND CURRENT ROW) amount,
                      MIN(visited_on) OVER ()                                                               1st_date
      FROM Customer) t
WHERE visited_on >= DATE_ADD(1st_date, INTERVAL 6 DAY);
