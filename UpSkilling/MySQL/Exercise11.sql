-- EXERCISE 11: Daily New User Count
-- Problem Statement: Find the number of users who registered each day in the last 7 days.
SELECT registration_date, COUNT(user_id) AS new_users_count
FROM users
WHERE registration_date >= DATE_SUB('2025-02-05', INTERVAL 7 DAY)
GROUP BY registration_date
ORDER BY registration_date DESC;