-- EXERCISE 12: Event with Maximum Sessions
-- Problem Statement: Identify the event(s) that have the highest number of sessions.
SELECT e.event_id, e.title, COUNT(s.session_id) AS session_count
FROM events e
JOIN sessions s ON e.event_id = s.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(s.session_id) = (
    SELECT MAX(session_count)
    FROM (
        SELECT COUNT(session_id) AS session_count
        FROM sessions
        GROUP BY event_id
    ) AS session_counts
);