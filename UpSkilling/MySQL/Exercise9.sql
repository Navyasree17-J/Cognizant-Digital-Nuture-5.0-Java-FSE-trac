-- EXERCISE 9: Organizer Event Summary
-- Problem Statement: For each event organizer, show the number of events created and their current status (upcoming, completed, cancelled).
SELECT u.user_id, u.full_name AS organizer_name, e.status, COUNT(e.event_id) AS event_count
FROM users u
JOIN events e ON u.user_id = e.organizer_id
GROUP BY u.user_id, u.full_name, e.status
ORDER BY u.full_name;