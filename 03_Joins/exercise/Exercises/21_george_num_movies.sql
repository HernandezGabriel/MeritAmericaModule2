-- 21. For every person in the person table with the first name of "George", list the number of movies they've been in--include all Georges, even those that have not appeared in any movies. Display the names in alphabetical order. (59 rows)
-- Name the count column 'num_of_movies'

select person.person_name, count(movie.title) as num_of_movies from person
left outer join movie_actor on movie_actor.actor_id=person.person_id
left outer join movie on movie.movie_id= movie_actor.movie_id
where person.person_name like 'George %'
group by person.person_id
order by person.person_name;

