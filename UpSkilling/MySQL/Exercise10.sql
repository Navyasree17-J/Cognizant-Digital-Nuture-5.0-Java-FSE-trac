-- EXERCISE 10: Feedback Gap
-- Problem Statement: Identify events that had registrations but received no feedback at all.
SELECT e.event_id, e.title
FROM events e
JOIN registrations r ON e.event_id = r.event_id
LEFT JOIN feedback f ON e.event_id = f.event_id
WHERE f.feedback_id IS NULL
GROUP BY e.event_id, e.title;