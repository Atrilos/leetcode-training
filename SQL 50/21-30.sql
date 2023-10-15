-- [1174](https://leetcode.com/problems/immediate-food-delivery-ii/description/)
SELECT ROUND(AVG(order_date = customer_pref_delivery_date) * 100, 2) as immediate_percentage
FROM Delivery
WHERE (customer_id, order_date) IN
      (SELECT customer_id, MIN(order_date)
       FROM Delivery
       GROUP BY customer_id);

-- [550](https://leetcode.com/problems/game-play-analysis-iv/)
SELECT ROUND(COUNT(DISTINCT player_id) / (SELECT COUNT(DISTINCT player_id) FROM Activity), 2) AS fraction
FROM Activity
WHERE (player_id, DATE_SUB(event_date, INTERVAL 1 DAY))
          IN (SELECT player_id, MIN(event_date) AS first_login FROM Activity GROUP BY player_id);

-- [2356](https://leetcode.com/problems/number-of-unique-subjects-taught-by-each-teacher/)
SELECT teacher_id,
       COUNT(DISTINCT subject_id) cnt
FROM Teacher
GROUP BY teacher_id;

-- [1141](https://leetcode.com/problems/user-activity-for-the-past-30-days-i/description/)
SELECT activity_date           day,
       COUNT(DISTINCT user_id) active_users
FROM Activity
WHERE DATEDIFF('2019-07-27', activity_date) BETWEEN 0 AND 29
GROUP BY 1;

-- [1070](https://leetcode.com/problems/product-sales-analysis-iii/description/)
SELECT product_id,
       year first_year,
       quantity,
       price
FROM Sales
WHERE (product_id, year) IN
      (SELECT product_id,
              MIN(year) year
       FROM Sales
       GROUP BY 1);

-- [596](https://leetcode.com/problems/classes-more-than-5-students/description/)
SELECT class
FROM Courses
GROUP BY 1
HAVING COUNT(1) >= 5;

-- [1729](https://leetcode.com/problems/find-followers-count/description/)
SELECT user_id,
       COUNT(*) followers_count
FROM Followers
GROUP BY 1
ORDER BY 1;

-- [619](https://leetcode.com/problems/biggest-single-number/description/)
SELECT MAX(num) num
FROM (SELECT *
      FROM MyNumbers
      GROUP BY num
      HAVING COUNT(num) = 1) a;

-- [1045](https://leetcode.com/problems/customers-who-bought-all-products/description/)
SELECT customer_id
FROM Customer
GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = (SELECT COUNT(*) FROM Product);

-- [1731](https://leetcode.com/problems/the-number-of-employees-which-report-to-each-employee/description/)
SELECT a.employee_id,
       a.name,
       COUNT(*)          reports_count,
       ROUND(AVG(b.age)) average_age
FROM Employees a
         JOIN Employees b ON a.employee_id = b.reports_to
GROUP BY 1
ORDER BY 1;