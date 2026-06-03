-- EXERCISE 23: Registration Trends
-- Problem Statement: Show a month-wise registration count trend over the past 12 months.
SELECT 
    DATE_FORMAT(registration_date, '%Y-%m') AS registration_month,
    COUNT(registration_id) AS total_registrations
FROM registrations
WHERE registration_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
GROUP BY DATE_FORMAT(registration_date, '%Y-%m')
ORDER BY registration_month DESC;