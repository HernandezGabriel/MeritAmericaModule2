-- 13. The directors of the movies in the "Harry Potter Collection" (4 rows)

select distinct person.person_name from movie
join collection on collection.collection_id = movie.collection_id
join person on person.person_id = movie.director_id
where collection.collection_name= 'Harry Potter Collection';

