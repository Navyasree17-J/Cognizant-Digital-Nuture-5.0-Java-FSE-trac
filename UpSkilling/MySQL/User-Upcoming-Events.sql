-- EXERCISE 2: Top Rated Events
-- NOTE: In production, the HAVING clause filters for >= 10 reviews. 
-- For our small 3-row testing dataset, we use >= 1 to see the results instantly.
SELECT e.event_id, e.title, AVG(f.rating) AS average_rating, COUNT(f.feedback_id) AS total_feedbacks
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(f.feedback_id) >= 1
ORDER BY average_rating DESC;