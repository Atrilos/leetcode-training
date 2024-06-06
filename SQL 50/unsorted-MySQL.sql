-- [601](https://leetcode.com/problems/human-traffic-of-stadium/description/)
WITH cte as (SELECT id,
                    visit_date,
                    people,
                    LEAD(people, 1) OVER (ORDER BY id) nxt1,
                    LEAD(people, 2) OVER (ORDER BY id) nxt2,
                    LAG(people, 1) OVER (ORDER BY id)  pre1,
                    LAG(people, 2) OVER (ORDER BY id)  pre2
             FROM Stadium)
SELECT id,
       visit_date,
       people
FROM cte
WHERE cte.people >= 100 AND pre1 >= 100 AND pre2 >= 100
   OR cte.people >= 100 AND pre1 >= 100 AND nxt1 >= 100
   OR cte.people >= 100 AND nxt1 >= 100 AND nxt2 >= 100;

-- [627](https://leetcode.com/problems/swap-salary/description/)
UPDATE salary
SET sex = IF(sex = 'm', 'f', 'm');

-- [1965](https://leetcode.com/problems/employees-with-missing-information/description/)
SELECT employee_id
FROM Employees
WHERE employee_id NOT IN (SELECT employee_id
                          FROM Salaries)

UNION ALL

SELECT employee_id
FROM Salaries
WHERE employee_id NOT IN (SELECT employee_id
                          FROM Employees)
ORDER BY employee_id;

-- [1407](https://leetcode.com/problems/top-travellers/description/)
# Write your MySQL query statement below
SELECT DISTINCT u.name,
                IFNULL(SUM(r.distance), 0) travelled_distance
FROM Users u
         LEFT JOIN Rides r ON u.id = r.user_id
GROUP BY u.id
ORDER BY 2 DESC, 1;

-- [1393](https://leetcode.com/problems/capital-gainloss/description/)
SELECT stock_name,
       SUM(IF(operation = 'Buy', -price, price)) capital_gain_loss
FROM Stocks
GROUP BY 1;

-- [1084](https://leetcode.com/problems/sales-analysis-iii/description/)
SELECT p.product_id, p.product_name
FROM Product p
         JOIN Sales s
              USING (product_id)
GROUP BY s.product_id
HAVING MIN(s.sale_date) >= '2019-01-01'
   AND MAX(s.sale_date) <= '2019-03-31';

-- [1158](https://leetcode.com/problems/market-analysis-i/description/)
SELECT u.user_id buyer_id, u.join_date, COUNT(o.buyer_id) orders_in_2019
FROM users u
         LEFT JOIN orders o ON u.user_id = o.buyer_id AND YEAR(o.order_date) = 2019
GROUP BY 1
ORDER BY 1;

-- [180](https://leetcode.com/problems/consecutive-numbers/description/)
WITH cte AS
         (SELECT id,
                 num,
                 LEAD(num, 1) OVER (ORDER BY id)                                                     next_val,
                 LEAD(num, 2) OVER (ORDER BY id)                                                     next_2,
                 id = LEAD(id, 1) OVER (ORDER BY id) - 1 AND id = LEAD(id, 2) OVER (ORDER BY id) - 2 flag
          FROM logs)
SELECT DISTINCT num ConsecutiveNums
FROM cte
WHERE num = next_val
  AND num = next_2
  AND flag;
