-- EXERCISE 5: Most Active Cities
-- Problem Statement: List the top 5 cities with the highest number of distinct user registrations.
SELECT u.city, COUNT(DISTINCT r.user_id) AS unique_registered_users
FROM users u
JOIN registrations r ON u.user_id = r.user_id
GROUP BY u.city
ORDER BY unique_registered_users DESC
LIMIT 5;