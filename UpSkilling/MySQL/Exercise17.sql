-- EXERCISE 17: Multi-Session Speakers
-- Problem Statement: Identify speakers who are handling more than one session across all events.
SELECT speaker_name, COUNT(session_id) AS total_sessions_handled
FROM sessions
GROUP BY speaker_name
HAVING COUNT(session_id) > 1
ORDER BY total_sessions_handled DESC;