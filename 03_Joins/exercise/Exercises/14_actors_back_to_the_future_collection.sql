-- 14. The names of actors who've appeared in the movies in the "Back to the Future Collection" (28 rows)

--select distinct person.person_name from movie
--left outer join collection on collection.collection_id = movie.collection_id
--left join movie_actor on movie_actor.movie_id = movie.movie_id
--left join person on person.person_id = movie_actor.actor_id
--where collection.collection_name= 'Back to the Future Collection'

SELECT DISTINCT p.person_name FROM movie AS m
JOIN movie_actor AS ma
ON ma.movie_id = m.movie_id
JOIN person AS p
ON p.person_id = ma.actor_id
JOIN collection AS c
ON c.collection_id = m.collection_id
WHERE c.collection_name LIKE '%Back to the Future%';
