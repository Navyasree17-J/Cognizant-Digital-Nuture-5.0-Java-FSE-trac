-- EXERCISE 13: Average Rating per City
-- Problem Statement: Calculate the average feedback rating of events conducted in each city.
SELECT e.city, 
       ROUND(AVG(f.rating), 2) AS average_rating, 
       COUNT(f.feedback_id) AS total_feedback_received
FROM events e
JOIN feedback f ON e.event_id = f.event_id
GROUP BY e.city
ORDER BY average_rating DESC;