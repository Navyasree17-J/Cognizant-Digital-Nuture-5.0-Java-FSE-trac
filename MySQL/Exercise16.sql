-- EXERCISE 16: Unregistered Active Users
-- Problem Statement: Find users who created an account in the last 30 days but haven’t registered for any events.
SELECT u.user_id, u.full_name, u.email, u.registration_date
FROM users u
LEFT JOIN registrations r ON u.user_id = r.user_id
WHERE r.registration_id IS NULL
  AND u.registration_date >= DATE_SUB('2025-02-05', INTERVAL 30 DAY)
ORDER BY u.registration_date DESC;