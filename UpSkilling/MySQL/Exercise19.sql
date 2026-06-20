-- EXERCISE 19: Completed Events with Feedback Summary
-- Problem Statement: For completed events, show total registrations and average feedback rating.
SELECT 
    e.event_id, 
    e.title AS event_name, 
    COUNT(DISTINCT r.registration_id) AS total_registrations,
    ROUND(AVG(f.rating), 2) AS average_feedback_rating
FROM events e
LEFT JOIN registrations r ON e.event_id = r.event_id
LEFT JOIN feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id, e.title;