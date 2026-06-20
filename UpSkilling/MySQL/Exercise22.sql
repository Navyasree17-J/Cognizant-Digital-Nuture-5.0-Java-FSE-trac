-- EXERCISE 22: Duplicate Registrations Check
-- Problem Statement: Detect if a user has been registered more than once for the same event.
SELECT user_id, event_id, COUNT(registration_id) AS total_registrations_found
FROM registrations
GROUP BY user_id, event_id
HAVING COUNT(registration_id) > 1;