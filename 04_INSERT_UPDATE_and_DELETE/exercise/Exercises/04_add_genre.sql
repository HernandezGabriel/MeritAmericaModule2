-- 4. Add a "Sports" genre to the genre table. Add the movie "Coach Carter" to the newly created genre. (1 row each)
begin transaction;
INSERT INTO genre (genre_name)
VALUES ('Sports');

INSERT INTO movie_genre (genre_id, movie_id)
values ((select genre_id from genre where genre_name = 'Sports'),
		(select movie_id from movie where title = 'Coach Carter'));


commit