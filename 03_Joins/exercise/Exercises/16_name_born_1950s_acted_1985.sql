-- 16. The names and birthdays of actors born in the 1950s who acted in movies that were released in 1985 (20 rows)

select distinct person_name,person.birthday from person
join movie_actor on movie_actor.actor_id = person.person_id
join movie on movie_actor.movie_id= movie.movie_id
where (person.birthday between '1950-01-01' and '1959-12-31')
and (movie.release_date BETWEEN '1985-01-01' and '1985-12-31');