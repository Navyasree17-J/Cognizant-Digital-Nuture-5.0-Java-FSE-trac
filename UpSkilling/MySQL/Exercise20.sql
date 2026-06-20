-- EXERCISE 20: User Engagement Index
-- Problem Statement: For each user, calculate how many events they registered for and how many feedbacks they submitted.
SELECT 
    u.user_id, 
    u.full_name, 
    COUNT(DISTINCT r.registration_id) AS events_registered,
    COUNT(DISTINCT f.feedback_id) AS feedback_submitted
FROM users u
LEFT JOIN registrations r ON u.user_id = r.user_id
LEFT JOIN feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name
ORDER BY events_registered DESC, feedback_submitted DESC;