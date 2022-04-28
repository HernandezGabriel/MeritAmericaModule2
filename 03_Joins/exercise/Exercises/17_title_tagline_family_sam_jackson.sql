-- 17. The titles and taglines of movies that are in the "Family" genre and Samuel L. Jackson has acted in (4 rows)


select movie.title, movie.tagline from movie
join movie_actor on movie_actor.movie_id = movie.movie_id
join person on person.person_id = movie_actor.actor_id
join movie_genre on movie_genre.movie_id= movie.movie_id
join genre on genre.genre_id = movie_genre.genre_id
where (genre.genre_name='Family')
and (person.person_name= 'Samuel L. Jackson');