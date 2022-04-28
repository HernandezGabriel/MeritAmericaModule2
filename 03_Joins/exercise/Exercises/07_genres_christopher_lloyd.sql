-- 7. The genres of movies that Christopher Lloyd has appeared in (8 rows)
-- Hint: DISTINCT will prevent duplicate values in your query results.

select distinct genre.genre_name from genre
join movie_genre on movie_genre.genre_id = genre.genre_id
join movie on movie_genre.movie_id = movie.movie_id
join movie_actor ON movie_actor.movie_id = movie.movie_id
join person on person.person_id = movie_actor.actor_id
where person.person_name= 'Christopher Lloyd';
