-- EXERCISE 15: Event Session Time Conflict
-- Problem Statement: Identify overlapping sessions within the same event (session start and end times that conflict).
SELECT 
    s1.event_id,
    e.title AS event_name,
    s1.session_id AS session1_id,
    s1.title AS session1_title,
    s1.start_time AS session1_start,
    s1.end_time AS session1_end,
    s2.session_id AS session2_id,
    s2.title AS session2_title,
    s2.start_time AS session2_start,
    s2.end_time AS session2_end
FROM sessions s1
JOIN sessions s2 ON s1.event_id = s2.event_id AND s1.session_id < s2.session_id
JOIN events e ON s1.event_id = e.event_id
WHERE s1.start_time < s2.end_time AND s1.end_time > s2.start_time;