-- EXERCISE 8: Sessions per Upcoming Event
SELECT e.event_id, e.title, COUNT(s.session_id) AS total_sessions
FROM events e
LEFT JOIN sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id, e.title;