-- [1821](https://leetcode.com/problems/find-customers-with-positive-revenue-this-year/description/)
SELECT customer_id
FROM Customers
WHERE year = 2021
  AND revenue > 0;

-- [1873](https://leetcode.com/problems/calculate-special-bonus/description/)
SELECT employee_id,
       CASE WHEN MOD(employee_id, 2) = 1 AND name NOT LIKE 'M%' THEN salary ELSE 0 END bonus
FROM Employees;

-- [1398](https://leetcode.com/problems/customers-who-bought-products-a-and-b-but-not-c/)
SELECT o.customer_id,
       c.customer_name
FROM Customers c
         JOIN Orders o USING (customer_id)
GROUP BY 1
HAVING SUM(o.product_name = 'A') >= 1
   AND SUM(o.product_name = 'B') >= 1
   AND SUM(o.product_name = 'C') = 0
ORDER BY 1;

-- [1112](https://leetcode.com/problems/highest-grade-for-each-student/description/)
WITH cte AS
         (SELECT *,
                 RANK() over (PARTITION BY student_id ORDER BY grade DESC, course_id) rnk
          FROM Enrollments)
SELECT student_id,
       course_id,
       grade
FROM cte
WHERE rnk = 1
ORDER BY 1;

-- [175](https://leetcode.com/problems/combine-two-tables/)
SELECT p.firstName,
       p.lastName,
       a.city,
       a.state
FROM Person p
         LEFT JOIN Address a USING (personId);

-- [1607](https://leetcode.com/problems/sellers-with-no-sales/description/)
SELECT seller_name
FROM Seller
WHERE seller_id NOT IN
      (SELECT DISTINCT seller_id
       FROM Orders
       WHERE YEAR(sale_date) = 2020)
ORDER BY 1;

-- [1407](https://leetcode.com/problems/top-travellers/)
SELECT u.name,
       IFNULL(SUM(distance), 0) travelled_distance
FROM Rides r
         RIGHT JOIN Users u ON r.user_id = u.id
GROUP BY u.id
ORDER BY 2 DESC, 1;

-- [607](https://leetcode.com/problems/sales-person/description/)
SELECT name
FROM SalesPerson
WHERE sales_id NOT IN
      (SELECT sales_id
       FROM Orders o
                JOIN Company c USING (com_id)
       WHERE c.name = 'RED');

-- [1440](https://leetcode.com/problems/evaluate-boolean-expression/description/)
SELECT e.left_operand,
       e.operator,
       e.right_operand,
       CASE
           WHEN operator = '>' THEN IF(v1.value > v2.value, 'true', 'false')
           WHEN operator = '<' THEN IF(v1.value < v2.value, 'true', 'false')
           ELSE IF(v1.value = v2.value, 'true', 'false') END AS value
FROM Expressions e
         JOIN Variables v1 ON left_operand = v1.name
         JOIN Variables v2 ON right_operand = v2.name;