-- [1264](https://leetcode.com/problems/page-recommendations/description/)
WITH friends AS
         (SELECT user1_id friend_id
          FROM Friendship
          WHERE user2_id = 1
          UNION
          SELECT user2_id friend_id
          FROM Friendship
          WHERE user1_id = 1)
SELECT DISTINCT l.page_id recommended_page
FROM friends f
         JOIN Likes l ON l.user_id = f.friend_id
WHERE l.page_id NOT IN (SELECT page_id FROM Likes WHERE user_id = 1);

-- [608](https://leetcode.com/problems/tree-node/description/)
SELECT id,
       CASE
           WHEN p_id IS NULL THEN 'Root'
           WHEN id IN (SELECT p_id FROM Tree) THEN 'Inner'
           ELSE 'Leaf' END type
FROM Tree;

-- [534](https://leetcode.com/problems/game-play-analysis-iii/)
SELECT player_id,
       event_date,
       SUM(games_played) OVER (PARTITION BY player_id ORDER BY event_date) games_played_so_far
FROM Activity;

-- [1783](https://leetcode.com/problems/grand-slam-titles/)
WITH cte AS (SELECT Wimbledon id
             FROM Championships
             UNION ALL
             SELECT Fr_open id
             FROM Championships
             UNION ALL
             SELECT US_open id
             FROM Championships
             UNION ALL
             SELECT Au_open id
             FROM Championships)
SELECT p.player_id,
       p.player_name,
       COUNT(*) grand_slams_count
FROM cte
         JOIN Players p ON p.player_id = cte.id
GROUP BY p.player_id;

-- [1747](https://leetcode.com/problems/leetflex-banned-accounts/)
SELECT DISTINCT b.account_id
FROM LogInfo a
         JOIN LogInfo b ON a.account_id = b.account_id
    AND a.ip_address <> b.ip_address
    AND LEAST(a.logout, b.logout) >= GREATEST(a.login, b.login);

-- [1350](https://leetcode.com/problems/students-with-invalid-departments/)
SELECT id,
       name
FROM Students s
WHERE department_id NOT IN (SELECT id FROM Departments);

-- [1303](https://leetcode.com/problems/find-the-team-size/)
SELECT employee_id,
       COUNT(*) OVER (PARTITION BY team_id) team_size
FROM Employee;

-- [512](https://leetcode.com/problems/game-play-analysis-ii/)
SELECT player_id,
       device_id
FROM Activity
WHERE (player_id, event_date) IN (SELECT player_id, MIN(event_date)
                                  FROM Activity
                                  GROUP BY 1)
GROUP BY 1;

-- [184](https://leetcode.com/problems/department-highest-salary/)
SELECT d.name   Department,
       e.name   Employee,
       e.salary Salary
FROM Employee e
         JOIN Department d ON e.departmentId = d.id
WHERE (e.departmentId, e.salary) IN (SELECT departmentId, MAX(salary)
                                     FROM Employee
                                     GROUP BY 1);

-- [1549](https://leetcode.com/problems/the-most-recent-orders-for-each-product/)
SELECT p.product_name,
       o.product_id,
       o.order_id,
       o.order_date
FROM Orders o
         JOIN Products p USING (product_id)
WHERE (o.product_id, o.order_date) IN (SELECT product_id, MAX(order_date)
                                       FROM Orders
                                       GROUP BY 1)
ORDER BY 1, 2, 3;