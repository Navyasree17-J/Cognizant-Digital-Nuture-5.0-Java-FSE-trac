-- EXERCISE 3: Inactive Users
SELECT user_id, full_name, email
FROM Users
WHERE user_id NOT IN (
    SELECT DISTINCT user_id 
    FROM Registrations 
    WHERE registration_date >= DATE_SUB('2025-07-15', INTERVAL 90 DAY)
);