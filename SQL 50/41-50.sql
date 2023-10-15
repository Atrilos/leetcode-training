-- [602](https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends/description/)
SELECT id,
       COUNT(*) num
FROM (SELECT requester_id id
      FROM RequestAccepted
      UNION ALL
      SELECT accepter_id id
      FROM RequestAccepted) ra
GROUP BY 1
ORDER BY 2 DESC
LIMIT 1;

-- [585](https://leetcode.com/problems/investments-in-2016/description/)
SELECT ROUND(SUM(tiv_2016), 2) tiv_2016
FROM Insurance a
WHERE a.tiv_2015 IN (SELECT tiv_2015
                     FROM Insurance
                     WHERE pid <> a.pid)
  AND (a.lat, a.lon) NOT IN
      (SELECT lat, lon
       FROM Insurance
       WHERE pid <> a.pid);

-- [185](https://leetcode.com/problems/department-top-three-salaries/description/)
WITH cte AS
         (SELECT d.name                                                                 Department,
                 e.salary                                                               Salary,
                 e.name                                                                 Employee,
                 DENSE_RANK() over (PARTITION BY e.departmentId ORDER BY e.salary DESC) dr
          FROM Employee e
                   JOIN Department d
                        ON d.id = e.departmentId)
SELECT Department, Employee, Salary
FROM cte
WHERE dr <= 3;

-- [1667](https://leetcode.com/problems/fix-names-in-a-table/)
SELECT user_id,
       CONCAT(UPPER(SUBSTR(name, 1, 1)), LOWER(SUBSTR(name, 2))) name
FROM Users
ORDER BY 1;