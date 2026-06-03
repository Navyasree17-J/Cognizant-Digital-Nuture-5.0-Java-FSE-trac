-- EXERCISE 6: Event Resource Summary
-- Problem Statement: Generate a report showing the number of resources (PDFs, images, links) uploaded for each event.
SELECT e.event_id, e.title,
       SUM(CASE WHEN r.resource_type = 'pdf' THEN 1 ELSE 0 END) AS total_pdfs,
       SUM(CASE WHEN r.resource_type = 'image' THEN 1 ELSE 0 END) AS total_images,
       SUM(CASE WHEN r.resource_type = 'link' THEN 1 ELSE 0 END) AS total_links
FROM events e
LEFT JOIN resources r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title;