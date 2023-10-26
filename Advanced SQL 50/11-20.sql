-- [1212](https://leetcode.com/problems/team-scores-in-football-tournament/description/)
SELECT team_id,
       team_name,
       SUM(CASE
               WHEN team_id = host_team AND host_goals > guest_goals THEN 3
               WHEN team_id = guest_team AND guest_goals > host_goals THEN 3
               WHEN team_id = host_team AND host_goals = guest_goals THEN 1
               WHEN team_id = guest_team AND guest_goals = host_goals THEN 1
               ELSE 0 END) as num_points
FROM Teams
         LEFT JOIN Matches
                   ON team_id = host_team OR team_id = guest_team
GROUP BY team_id
ORDER BY 3 DESC, 1;

-- [1890](https://leetcode.com/problems/the-latest-login-in-2020/description/)
SELECT user_id,
       MAX(time_stamp) last_stamp
FROM Logins
WHERE YEAR(time_stamp) = 2020
GROUP BY 1;

-- [511](https://leetcode.com/problems/game-play-analysis-i/)
SELECT player_id,
       MIN(event_date) first_login
FROM Activity
GROUP BY 1;

-- [1571](https://leetcode.com/problems/warehouse-manager/description/)
SELECT name                                 warehouse_name,
       SUM(Width * Length * Height * units) volume
FROM Warehouse
         JOIN Products USING (product_id)
GROUP BY 1;

-- [586](https://leetcode.com/problems/customer-placing-the-largest-number-of-orders/description/)
SELECT customer_number
FROM orders
GROUP BY 1
ORDER BY COUNT(*) DESC
LIMIT 1;

-- [1741](https://leetcode.com/problems/find-total-time-spent-by-each-employee/)
SELECT event_day               day,
       emp_id,
       SUM(out_time - in_time) total_time
FROM Employees
GROUP BY 1, 2;

-- [1173](https://leetcode.com/problems/immediate-food-delivery-i/)
SELECT ROUND(AVG(order_date = customer_pref_delivery_date) * 100, 2) immediate_percentage
FROM Delivery;

-- [1445](https://leetcode.com/problems/apples-oranges/description/)
SELECT sale_date,
       SUM(IF(fruit = 'apples', sold_num, -sold_num)) AS diff
FROM Sales
GROUP BY 1
ORDER BY 1;

-- [1699](https://leetcode.com/problems/number-of-calls-between-two-persons/description/)
SELECT from_id       person1,
       to_id         person2,
       COUNT(*)      call_count,
       SUM(duration) total_duration
FROM Calls
GROUP BY LEAST(from_id, to_id), GREATEST(from_id, to_id);

-- [1587](https://leetcode.com/problems/bank-account-summary-ii/description/)
SELECT u.name,
       SUM(t.amount) balance
FROM Transactions t
         JOIN Users u USING (account)
GROUP BY t.account
HAVING balance > 10000;