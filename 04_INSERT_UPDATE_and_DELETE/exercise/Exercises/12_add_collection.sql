-- 12. Create a "Bill Murray Collection" in the collection table. For the movies that have Bill Murray in them, set their collection ID to the "Bill Murray Collection". (1 row, 6 rows)
begin transaction;

insert into collection (collection_name)
values ('Bill Murray Collection');

UPDATE 
    movie 
SET 
    collection_id=(select collection_id from collection where collection_name like 'Bill Murray%' limit 1)
WHERE
	movie_id IN
	(Select movie_id from 
	 		movie_actor JOIN person on person.person_id = movie_actor.actor_id
	 		where person_name = 'Bill Murray');

Commit;
	






--1795486