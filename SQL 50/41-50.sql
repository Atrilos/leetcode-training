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

-- [1527](https://leetcode.com/problems/patients-with-a-condition/)
SELECT patient_id, patient_name, conditions
FROM Patients
WHERE conditions REGEXP '\\bDIAB1';

-- [1517](https://leetcode.com/problems/find-users-with-valid-e-mails/description/)
SELECT *
FROM Users
WHERE mail REGEXP '^[a-zA-Z][a-zA-Z0-9_.-]*@leetcode\\.com$';

-- [196](https://leetcode.com/problems/delete-duplicate-emails/)
WITH CTE AS (SELECT *,
                    ROW_NUMBER() OVER (PARTITION BY email ORDER BY id) AS row_num
             FROM person)
DELETE
FROM person
WHERE id IN (SELECT id FROM CTE WHERE row_num > 1);

-- [176](https://leetcode.com/problems/second-highest-salary/description/)
WITH cte AS
         (SELECT ROW_NUMBER() over (ORDER BY salary DESC) rn, salary
          FROM Employee
          GROUP BY 2)
SELECT CASE
           WHEN MAX(rn) < 2 THEN NULL
           ELSE
               salary END SecondHighestSalary
FROM cte
WHERE rn = 2;

-- [1484](https://leetcode.com/problems/group-sold-products-by-the-date/description/)
WITH cte AS
             (SELECT DISTINCT * FROM Activities)
SELECT sell_date,
       COUNT(*)                                    num_sold,
       GROUP_CONCAT(product ORDER BY product, ',') products
FROM cte
GROUP BY 1
ORDER BY 1;

-- [1327](https://leetcode.com/problems/list-the-products-ordered-in-a-period/description/)
SELECT p.product_name,
       SUM(o.unit) unit
FROM Orders o
         JOIN Products p USING (product_id)
WHERE o.order_date LIKE '2020-02%'
GROUP BY o.product_id
HAVING unit >= 100;