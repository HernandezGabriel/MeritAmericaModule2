--select title, release_date from movie 
--where movie_id = (select person_id from person where person_name = 'Tom Hanks' );

--SELECT person.name, mov.name FROM actor
--JOIN actor_film ON actor_film.actor_id = actor.actor_id
--JOIN film ON film.film_id = actor_film.film_id
--WHERE actor.name LIKE 'B%';

select movie.title, movie.release_date from person 
join movie_actor on movie_actor.actor_id = person.person_id
join movie on movie.movie_id= movie_actor.movie_id
where person.person_name = 'Tom Hanks';
-- 2. The names and birthdays of actors in "The Fifth Element" (15 rows)

select person.person_name, person.birthday from person
join movie_actor on movie_actor.actor_id=person.person_id
join movie on movie.movie_id= movie_actor.movie_id
where movie.title = 'The Fifth Element';

-- 3. For all actors with the last name of "Jones", 
--display the actor's name and movie titles they appeared in. 
--Order the results by the actor names (A-Z). (48 rows)

select person.person_name, movie.title from person
join movie_actor on movie_actor.actor_id=person.person_id
join movie on movie.movie_id= movie_actor.movie_id
where person.person_name like '% Jones'
order by person.person_name;

-- 21. For every person in the person table with the first name of "George", 
--list the number of movies they've been in--include all Georges, 
--even those that have not appeared in any movies. Display the names 
--in alphabetical order. (59 rows)
-- Name the count column 'num_of_movies'

select person.person_name, count(movie.title) as num_of_movies from person
left outer join movie_actor on movie_actor.actor_id=person.person_id
left outer join movie on movie.movie_id= movie_actor.movie_id
where person.person_name like 'George %'
group by person.person_name
order by person.person_name;

-- 4. The titles and taglines of all the movies that are in the Fantasy genre. Order the results by title (A-Z) (81 rows)

select movie.title tagline from movie
join movie_genre on movie.movie_id = movie_genre.movie_id
join genre on movie_genre.genre_id= genre.genre_id
where genre.genre_name = 'Fantasy'
order by movie.title;
-- 5. The titles and release dates of all the movies that are in the Comedy genre. Order the results by release date, earliest to latest. (220 rows)

select title, release_date from movie 
join movie_genre on movie_genre.movie_id = movie.movie_id
join genre on movie_genre.genre_id = genre.genre_id
where genre.genre_name = 'Comedy'
order by movie.release_date desc;

-- 6. The genres of "The Wizard of Oz" (3 rows)

select genre.genre_name from genre
join movie_genre on movie_genre.genre_id = genre.genre_id
join movie on movie_genre.movie_id = movie.movie_id
where movie.title='The Wizard of Oz';

-- 7. The genres of movies that Christopher Lloyd has appeared in (8 rows)
-- Hint: DISTINCT will prevent duplicate values in your query results.

select distinct genre.genre_name from genre
join movie_genre on movie_genre.genre_id = genre.genre_id
join movie on movie_genre.movie_id = movie.movie_id
join movie_actor ON movie_actor.movie_id = movie.movie_id
join person on person.person_id = movie_actor.actor_id
where person.person_name= 'Christopher Lloyd';

-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later (6 rows)

select distinct genre.genre_name from genre
join movie_genre on movie_genre.genre_id = genre.genre_id
join movie on movie_genre.movie_id = movie.movie_id
join movie_actor ON movie_actor.movie_id = movie.movie_id
join person on person.person_id = movie_actor.actor_id
where person.person_name= 'Robert De Niro' and movie.release_date >= '2010-01-01';


-- 9. The titles of movies directed by James Cameron (6 rows)

select title from movie
join person on person.person_id = movie.director_id
where person.person_name = 'James Cameron';

-- 10. The names of directors who have directed a movie over 3 hours [180 minutes] (15 rows)

select distinct person.person_name from person
join movie on movie.director_id = person.person_id
where movie.length_minutes > 180 ;
-- 11. The titles of the movies in the "Star Wars Collection" ordered by release date, most recent to earliest (9 rows)

select title from movie 
join collection on collection.collection_id = movie.collection_id
where collection.collection_name= 'Star Wars Collection'
order by movie.release_date desc;

-- 12. The titles of the movies in the "Star Wars Collection" that weren't directed by George Lucas (5 rows)


select title from movie 
join collection on collection.collection_id = movie.collection_id
join person on person.person_id = movie.director_id
where collection.collection_name= 'Star Wars Collection' and person.person_name != 'George Lucas'
order by movie.release_date desc;
-- 13. The directors of the movies in the "Harry Potter Collection" (4 rows)

select distinct person.person_name from movie
join collection on collection.collection_id = movie.collection_id
join person on person.person_id = movie.director_id
where collection.collection_name= 'Harry Potter Collection';
-- 14. The names of actors who've appeared in the movies in the "Back to the Future Collection" (28 rows)

select distinct person.person_name from movie
join collection on collection.collection_id = movie.collection_id
join movie_actor on movie_actor.movie_id = movie.movie_id
join person on person.person_id = movie_actor.actor_id
where collection.collection_name= 'Back to the Future Collection';
-- 15. The title of the movie and the name of director for movies where the director was also an actor in the same movie (73 rows)

select title, person.person_name from movie
join movie_actor on movie_actor.movie_id = movie.movie_id
join person on person.person_id = movie_actor.actor_id
where person.person_id= movie.director_id;

-- 16. The names and birthdays of actors born in the 1950s who acted in movies that were released in 1985 (20 rows)

select distinct person_name,person.birthday from person
join movie_actor on movie_actor.actor_id = person.person_id
join movie on movie_actor.movie_id= movie.movie_id
where (person.birthday between '1950-01-01' and '1959-12-31')
and (movie.release_date BETWEEN '1985-01-01' and '1985-12-31');
-- 17. The titles and taglines of movies that are in the "Family" genre and Samuel L. Jackson has acted in (4 rows)

select movie.title, movie.tagline from movie 
join movie_actor on movie_actor.movie_id = movie.movie_id
join person on person.person_id = movie_actor.actor_id
join movie_genre on movie_genre.movie_id= movie.movie_id
join genre on genre.genre_id = movie_genre.genre_id
where (genre.genre_name='Family')
and (person.person_name= 'Samuel L. Jackson');

-- 18. The average length of movies in the "Science Fiction" genre. Name the column 'average_length'.
-- (1 row, expected result around 110-120)

select avg(movie.length_minutes) as average_length from movie
join movie_genre on movie_genre.movie_id = movie.movie_id
join genre ON genre.genre_id = movie_genre.genre_id
where genre.genre_name='Science Fiction';

-- 19. The genre name and the number of movies in each genre. Name the count column 'num_of_movies'. 
-- (19 rows, expected result for Action is around 180).

select genre.genre_name, count(movie.title) as num_of_movies
from movie
join movie_genre on movie_genre.movie_id= movie.movie_id
join genre on genre.genre_id= movie_genre.genre_id
group by genre.genre_name;




-- 20. The titles, lengths, and release dates of the 5 longest movies in the "Action" genre. Order the movies by length (highest first), then by release date (latest first).
-- (5 rows, expected lengths around 180 - 200)


select movie.title, movie.length_minutes, movie.release_date from movie
join movie_genre on movie_genre.movie_id = movie.movie_id
join genre on genre.genre_id = movie_genre.genre_id
where genre.genre_name = 'Action'
order by movie.length_minutes desc, movie.release_date desc
limit 5;






