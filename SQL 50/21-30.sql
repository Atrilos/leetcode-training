-- [1174](https://leetcode.com/problems/immediate-food-delivery-ii/description/)
SELECT ROUND(AVG(order_date = customer_pref_delivery_date) * 100, 2) as immediate_percentage
FROM Delivery
WHERE (customer_id, order_date) IN
      (SELECT customer_id, MIN(order_date)
       FROM Delivery
       GROUP BY customer_id);

-- [550](https://leetcode.com/problems/game-play-analysis-iv/)
