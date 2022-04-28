-- 19. The genre name and the number of movies in each genre. Name the count column 'num_of_movies'.
-- (19 rows, expected result for Action is around 180).


select genre.genre_name, count(movie.title) as num_of_movies
from movie
join movie_genre on movie_genre.movie_id= movie.movie_id
join genre on genre.genre_id= movie_genre.genre_id
group by genre.genre_name;
