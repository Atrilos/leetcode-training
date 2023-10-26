-- [182](https://leetcode.com/problems/duplicate-emails/)
SELECT email
FROM Person
GROUP BY email
HAVING COUNT(*) > 1;

-- [1050](https://leetcode.com/problems/actors-and-directors-who-cooperated-at-least-three-times/description/)
SELECT actor_id,
       director_id
FROM ActorDirector
GROUP BY 1, 2
HAVING COUNT(*) >= 3;

-- [1511](https://leetcode.com/problems/customer-order-frequency/description/)
SELECT customer_id,
       name
FROM Customers
         JOIN Orders USING (customer_id)
         JOIN Product USING (product_id)
GROUP BY 1
HAVING SUM(IF(LEFT(order_date, 7) = '2020-06', quantity, 0) * price) >= 100
   AND SUM(IF(LEFT(order_date, 7) = '2020-07', quantity, 0) * price) >= 100;

-- [1693](https://leetcode.com/problems/daily-leads-and-partners/description/)
SELECT date_id,
       make_name,
       COUNT(DISTINCT lead_id)    unique_leads,
       COUNT(DISTINCT partner_id) unique_partners
FROM DailySales
GROUP BY 1, 2;

-- [1495](https://leetcode.com/problems/friendly-movies-streamed-last-month/)
SELECT DISTINCT c.title
FROM Content c
         JOIN TVProgram tv USING (content_id)
WHERE c.Kids_content = 'Y'
  AND DATE_FORMAT(tv.program_date, '%Y-%m') = '2020-06'
  AND c.content_type = 'Movies';

-- [1501](https://leetcode.com/problems/countries-you-can-safely-invest-in/)
SELECT co.name country
FROM Calls c
         JOIN Person p ON p.id IN (c.caller_id, c.callee_id)
         JOIN Country co ON LEFT(p.phone_number, 3) = co.country_code
GROUP BY co.name
HAVING AVG(c.duration) > (SELECT AVG(duration) FROM Calls);

-- [603](https://leetcode.com/problems/consecutive-available-seats/)
SELECT seat_id
FROM (SELECT seat_id,
             free,
             LEAD(free) OVER (ORDER BY seat_id) next_free,
             LAG(free) OVER (ORDER BY seat_id)  prev_free
      FROM Cinema) tmp
WHERE (free, next_free) = (1, 1)
   OR (free, prev_free) = (1, 1)
ORDER BY 1;

-- [1795](https://leetcode.com/problems/rearrange-products-table/)
SELECT *
FROM (SELECT product_id, 'store1' store, store1 price
      FROM Products
      UNION ALL
      SELECT product_id, 'store2' store, store2 price
      FROM Products
      UNION ALL
      SELECT product_id, 'store3' store, store3 price
      FROM Products) p
WHERE price IS NOT NULL;

-- [613](https://leetcode.com/problems/shortest-distance-in-a-line/)
SELECT MIN(next_x - x) shortest
FROM (SELECT x,
             LEAD(x) OVER (ORDER BY x) next_x
      FROM Point) t;

-- [614](https://leetcode.com/problems/second-degree-follower/)
WITH followed_by AS (SELECT followee user_name,
                            COUNT(*) num
                     FROM Follow
                     GROUP BY 1),
     follows AS (SELECT follower user_name,
                        COUNT(*) num
                 FROM Follow
                 GROUP BY 1)
SELECT f1.user_name follower,
       f1.num
FROM followed_by f1
         JOIN follows f2 USING (user_name)
WHERE f2.num > 0
  AND f1.num > 0
ORDER BY 1;

-- [1965](https://leetcode.com/problems/employees-with-missing-information/)
WITH cte AS (SELECT employee_id
             FROM Employees
             UNION
             SELECT employee_id
             FROM Salaries)
SELECT cte.employee_id
FROM cte
         LEFT JOIN Salaries s USING (employee_id)
         LEFT JOIN Employees e USING (employee_id)
WHERE e.name IS NULL
   OR s.salary IS NULL
ORDER BY 1;