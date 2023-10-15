-- [1757](https://leetcode.com/problems/recyclable-and-low-fat-products/description/)
SELECT product_id
FROM Products p
WHERE low_fats = 'Y'
  AND recyclable = 'Y';

-- [584](https://leetcode.com/problems/find-customer-referee/description/)
SELECT name
FROM Customer
WHERE IFNULL(referee_id, 0) <> 2;

-- [595](https://leetcode.com/problems/big-countries/description/)
SELECT name, population, area
FROM World
WHERE area >= 3000000
UNION
SELECT name, population, area
FROM World
WHERE population >= 25000000;

-- [1148](https://leetcode.com/problems/article-views-i/description/)
SELECT DISTINCT author_id id
FROM Views
WHERE author_id = viewer_id
ORDER BY 1 ASC;

-- [1683](https://leetcode.com/problems/invalid-tweets/description/)
SELECT tweet_id
FROM Tweets
WHERE CHAR_LENGTH(content) > 15;

-- [1378](https://leetcode.com/problems/replace-employee-id-with-the-unique-identifier/)
SELECT u.unique_id, e.name
FROM Employees e
         LEFT JOIN EmployeeUNI u
                   USING (id);

-- [1068](https://leetcode.com/problems/product-sales-analysis-i/description/)
SELECT p.product_name, s.year, s.price
FROM sales s
         JOIN product USING (product_id);

-- [1581](https://leetcode.com/problems/customer-who-visited-but-did-not-make-any-transactions/)
SELECT v.customer_id, COUNT(v.visit_id) count_no_trans
FROM visits v
         LEFT JOIN transactions t USING (visit_id)
WHERE t.transaction_id IS NULL
GROUP BY v.customer_id;

-- [197](https://leetcode.com/problems/rising-temperature/description/)
SELECT w1.id Id
FROM Weather w1
         JOIN Weather w2
              ON DATEDIFF(w1.recordDate, w2.recordDate) = 1
                  AND w1.temperature > w2.temperature;

-- [1661](https://leetcode.com/problems/average-time-of-process-per-machine/)
SELECT a.machine_id, ROUND(AVG(b.timestamp - a.timestamp), 3) AS processing_time
FROM Activity a
         JOIN Activity b ON
    a.machine_id = b.machine_id AND a.process_id = b.process_id
WHERE a.activity_type = 'start'
  AND b.activity_type = 'end'
GROUP BY 1
ORDER BY 1 ASC;