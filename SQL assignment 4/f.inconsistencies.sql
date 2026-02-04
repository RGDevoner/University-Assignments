SELECT person_id
FROM Person
GROUP BY person_id
HAVING COUNT(*) > 1;

UPDATE movie_cast
SET person_id = (
    SELECT MIN(Person.person_id)
    FROM Person
    WHERE Person.name = movie_cast.name AND Person.gender = movie_cast.gender
)
FROM movie_cast
JOIN (
    SELECT person_id, MIN(person_id) AS min_person_id
    FROM movie_cast
    GROUP BY person_id
    HAVING COUNT(*) > 1
) AS subquery_mc
ON movie_cast.person_id = subquery_mc.person_id AND movie_cast.person_id != subquery_mc.min_person_id;

UPDATE movie_crew
SET person_id = (
    SELECT MIN(Person.person_id)
    FROM Person
    WHERE Person.name = movie_crew.name AND Person.gender = movie_crew.gender
)
FROM movie_crew
JOIN (
    SELECT person_id, MIN(person_id) AS min_person_id
    FROM movie_crew
    GROUP BY person_id
    HAVING COUNT(*) > 1
) AS subquery_mc
ON movie_crew.person_id = subquery_mc.person_id AND movie_crew.person_id != subquery_mc.min_person_id;