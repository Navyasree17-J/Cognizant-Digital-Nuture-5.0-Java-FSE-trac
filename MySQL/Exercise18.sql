-- EXERCISE 18: Resource Availability Check
-- Problem Statement: List all events that do not have any resources uploaded.
SELECT e.event_id, e.title, e.status
FROM events e
LEFT JOIN resources r ON e.event_id = r.event_id
WHERE r.resource_id IS NULL;