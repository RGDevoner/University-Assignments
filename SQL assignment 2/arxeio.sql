/*1 */

SELECT YEAR(release_date) AS year, COUNT(*) AS movies_per_year
FROM Movie
WHERE budget > 1000000
GROUP BY YEAR(release_date)
ORDER BY year;

/*2*/

SELECT g.name AS genre, COUNT(*) AS movies_per_genre
FROM Movie m
JOIN hasGenre hg ON m.id = hg.movie_id
JOIN genre g ON hg.genre_id = g.id
WHERE m.budget > 1000000 OR m.runtime > 120
GROUP BY g.name
ORDER BY movies_per_genre DESC;

/*3*/

SELECT g.name AS genre, YEAR(m.release_date) AS year, COUNT(*) AS movies_per_gy
FROM Movie m
JOIN hasGenre hg ON m.id = hg.movie_id
JOIN genre g ON hg.genre_id = g.id
GROUP BY g.name, YEAR(m.release_date)
ORDER BY year, genre;

/*4*/

SELECT YEAR(m.release_date) AS year, SUM(m.revenue) AS revenues_per_year
FROM Movie m
JOIN Movie_Cast mc ON m.id = mc.movie_id
JOIN Actor a ON mc.person_id = a.person_id
WHERE a.name = 'Turo Pajala'
GROUP BY YEAR(m.release_date)
ORDER BY year;

/*για παραδειγμα πειρα τον Turo pajala, βαλε το δικο σας αμα θελετε, χρειαζονται τα '' !! */

/*5*/

SELECT YEAR(m.release_date) AS year, MAX(m.budget) AS max_budget
FROM Movie m
WHERE m.budget > 0
GROUP BY YEAR(m.release_date)
ORDER BY year;

/*6*/

SELECT c.name AS trilogy_name
FROM collection c
JOIN belongsToCollection bc ON c.id = bc.collection_id
GROUP BY c.name
HAVING COUNT(bc.movie_id) = 3;

/*7*/

SELECT user_id, AVG(rating) AS avg_rating, COUNT(rating) AS rating_count
FROM Ratings
GROUP BY user_id;

/*8*/

SELECT TOP 10 title AS movie_title, budget
FROM movie
ORDER BY budget DESC;

/*9*/

SELECT m.title AS movie_title, m.release_date AS year, m.budget
FROM Movie m
JOIN (
    SELECT YEAR(m.release_date) AS year, MAX(m.budget) AS max_budget
    FROM Movie m
    WHERE m.budget > 0
    GROUP BY YEAR(m.release_date)
) AS max_budget_per_year
ON YEAR(m.release_date) = max_budget_per_year.year AND m.budget = max_budget_per_year.max_budget
ORDER BY m.release_date, m.title;

/*12*/

CREATE VIEW Popular_Movie_Pairs AS
SELECT 
    r1.movie_id AS id1,
    r2.movie_id AS id2
FROM 
    ratings r1
JOIN 
    ratings r2 ON r1.user_id = r2.user_id
WHERE 
    r1.rating > 4 AND r2.rating > 4
    AND r1.movie_id < r2.movie_id
GROUP BY 
    r1.movie_id, r2.movie_id
HAVING 
    COUNT(DISTINCT r1.user_id) > 10;

/*10*/

SELECT DISTINCT c1.name
FROM movie_crew c1
JOIN hasGenre hg1 ON c1.movie_id = hg1.movie_id
JOIN genre g1 ON hg1.genre_id = g1.id
WHERE g1.name = 'Horror' AND c1.job = 'Director' 
AND EXISTS (
    SELECT 1
    FROM movie_crew c2
    JOIN hasGenre hg2 ON c2.movie_id = hg2.movie_id
    JOIN genre g2 ON hg2.genre_id = g2.id
    WHERE c2.person_id = c1.person_id AND g2.name = 'Comedy'
    AND c2.job = 'Director'
)
AND NOT EXISTS (
    SELECT 1
    FROM movie_crew c3
    JOIN hasGenre hg3 ON c3.movie_id = hg3.movie_id
    JOIN genre g3 ON hg3.genre_id = g3.id
    WHERE c3.person_id = c1.person_id 
    AND c3.job = 'Director'
    AND g3.name NOT IN ('Horror', 'Comedy')
);

/*11*/

SELECT c1.person_id, c1.name
FROM movie_crew c1
JOIN hasGenre hg1 ON c1.movie_id = hg1.movie_id
JOIN genre g1 ON hg1.genre_id = g1.id
WHERE g1.name = 'Horror' AND c1.job = 'Director'
INTERSECT
SELECT c2.person_id, c2.name
FROM movie_crew c2
JOIN hasGenre hg2 ON c2.movie_id = hg2.movie_id
JOIN genre g2 ON hg2.genre_id = g2.id
WHERE g2.name = 'Comedy' AND c2.job = 'Director'
EXCEPT
SELECT c3.person_id, c3.name
FROM movie_crew c3
JOIN hasGenre hg3 ON c3.movie_id = hg3.movie_id
JOIN genre g3 ON hg3.genre_id = g3.id
WHERE g3.name NOT IN ('Horror', 'Comedy') AND c3.job = 'Director';

/*το 10 και 11 δεν φενεται να υπαρχει καποιος που να ικανοποιει της προηποθεσης (να εχει πεξει σε ταινια τρομου,κωμοδια και σε καμοια αλλη) οποτε δεν επιστρεφει κανεναν */