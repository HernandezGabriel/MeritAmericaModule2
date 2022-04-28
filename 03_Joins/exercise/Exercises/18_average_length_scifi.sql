-- 18. The average length of movies in the "Science Fiction" genre. Name the column 'average_length'.
-- (1 row, expected result around 110-120)

select avg(movie.length_minutes) as average_length from movie
join movie_genre on movie_genre.movie_id = movie.movie_id
join genre ON genre.genre_id = movie_genre.genre_id
where genre.genre_name='Science Fiction';
