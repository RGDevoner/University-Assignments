SELECT distinct k.name
FROM Keyword k ,haskeyword hk
WHERE k.id = hk.keyword_id AND k.name like 'a%'
ORDER BY k.name  

/*Βρες μου τα ονόματα των keywords που χρησιμοποιούν οι χρήστες τα οποιά ξεκινάνε με με "α" σε αλφαβητική σειρά"
(Output: 638 rows)*/

SELECT distinct g.name, hg.movie_id
FROM genre g
JOIN hasGenre hg ON g.id = hg.genre_id
WHERE name='Drama'
GROUP BY g.name, hg.movie_id;

/*Μόνο για την κατηγορία του 'Drama', βρές μου τους κωδικούς των ταινιών που ανήκουν στο είδος
(Output :5372 rows*/


SELECT MAX(m.budget)
FROM movie m
JOIN movie_cast mc ON mc.movie_id = m.id 
/*Βγάλε μου το μέγιστο budget που δόθηκε σε ταινία 
(Output :200000000)*/ 


SELECT distinct r.user_id,avg(r.rating)
FROM ratings r
WHERE rating between 10 AND 50
GROUP BY r.user_id
/*Βρες μου τον μέσο όρο βαθμολογίας που έχει δώσει κάθε χρήστης αν οι βαθμολογίες του είναι απο 10 εως 50
(Output: 670 rows*/


SELECT DISTINCT g.name, hg.movie_id
FROM genre g
LEFT JOIN hasGenre hg ON g.id = hg.genre_id
/*Για κάθε είδος ταινίας,βρες μο τους κωδικούς των ταινιών που ανήκουν στο είδος,ακόμα και για είδη ταινίας που δεν υπάρχει κάποιος κωδικός output 12071 rows */


SELECT distinct m.title, m.budget
FROM movie m
JOIN movie_cast mc ON m.id = mc.movie_id
WHERE m.budget = (SELECT MIN(budget) FROM movie)

/*δείτε μου την ταινία ή τις ταινίες αν υπάρχουν πάνω από μια με το μικρότερο budget και μαζί με το budget τους output 6262 rows */ 

SELECT TOP 5 *
FROM movie
ORDER BY popularity DESC;

/*Ολοκληρώνοντας, ο SQL κώδικας αυτός επιλέγει τα πεδία όλων των εγγραφών από τον πίνακα movie, ταξινομεί τις εγγραφές με βάση 
τη δημοφιλία τους με φθίνουσα σειρά και επιστρέφει μόνο τις 5 πρώτες εγγραφές με βάση αυτή την ταξινόμηση. output 5 rows */

SELECT m.title, r.rating
FROM movie m
JOIN ratings r ON m.id = r.movie_id;

/*Το ερώτημα επιστρέφει την αντιστοίχιση του τίτλου της ταινίας με τη βαθμολογία που έχει δοθεί για αυτήν την ταινία. output 30162 rows */

SELECT m.title, r.rating
FROM movie m
LEFT JOIN ratings r ON m.id = r.movie_id

/*Το ερώτημα επιστρέφει την αντιστοίχιση του τίτλου της ταινίας με τη βαθμολογία που έχει δοθεί για αυτήν την ταινία.(απλα ειναι με outer join!output 38642 )*/

SELECT m.title, AVG(r.rating) AS avg_rating, MIN(r.rating) AS min_rating, MAX(r.rating) AS MAX_RATING
FROM ratings u
JOIN ratings r ON u.user_id = r.user_id
JOIN movie m ON r.movie_id = m.id
GROUP BY m.title;

/*το ερωτημα επιστρέφει πινακα με τιτλο της ταινιας και μετα την μεση βαθμολογια, ελαχιστη βαθμολογια και την μεγιστη output 1501 rows*/

SELECT m.title, pc.name AS production_company
FROM movie m
JOIN productioncompany pc ON m.id = pc.id;

/*το ερωτημα επιστρεφει το τιτλο και το ονομα της αιτεριας για καθε ταινια output 1364*/

SELECT MAX(m.runtime)
FROM movie m
JOIN movie_cast mc ON mc.movie_id = m.id
/*εμφανιζει το μεγιστο runtime απο ολες της ταινιες output 7200 1 row*/



