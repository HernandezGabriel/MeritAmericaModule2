BEGIN TRANSACTION;
DROP TABLE IF EXISTS users,raffle, ticket;

create table users (
   users_id serial unique not NULL,
   username varchar(20) unique not Null,
   first_name varchar(20) not null,
   last_name varchar(30) not null,
   email varchar(50) not null,
   referred_by_users_id int null,
   CONSTRAINT pk_users Primary key(users_id),
   CONSTRAINT fk_users foreign key(referred_by_users_id) REFERENCES users(users_id)
);

create table raffle(
   raffle_id serial unique not Null,
   raffle_name varchar(20) not Null,
   raffle_item_name varchar(20) not Null,
   raffle_owner_id int not null,
   raffle_description varchar(200) null,
   raffle_picture_location varchar(200) null,
   raffle_link varchar(200) unique null,
   raffle_end_size_limit int null,
   raffle_end_date_and_time timestamp null,
   expired boolean null,
   CONSTRAINT pk_raffle Primary key(raffle_id),
   CONSTRAINT fk_raffle_owner_id foreign key(raffle_owner_id) REFERENCES users(users_id)

);

create table ticket(
   ticket_id serial unique not null,
   users_id int not null,
   raffle_id int not null,
   CONSTRAINT pk_ticket Primary key(ticket_id),
   CONSTRAINT fk_ticket_users_id foreign key(users_id) REFERENCES users(users_id),
   CONSTRAINT fk_ticket_raffle_id foreign key(raffle_id) REFERENCES raffle(raffle_id)
);


INSERT INTO users(username, first_name,last_name, email)
VALUES('users1','gabe', 'h', 'fakeemail@gmail.com'),
('users2','mike', 'jones', 'fakeemail2@gmail.com'),
('users3','yaili', 'mendez', 'fakeemail3@gmail.com');



INSERT INTO raffle(raffle_name, raffle_item_name, raffle_owner_id)
VALUES('raffle1', 'ps5',(select users_id from users where username='users1'));



INSERT INTO ticket (users_id,raffle_id)
VALUES(
   (select users_id from users where username= 'users2'),(select raffle_id from raffle where raffle_name ='raffle1'))
,((select users_id from users where username= 'users3'),(select raffle_id from raffle where raffle_name ='raffle1'))
,((select users_id from users where username= 'users3'),(select raffle_id from raffle where raffle_name ='raffle1'))
,((select users_id from users where username= 'users3'),(select raffle_id from raffle where raffle_name ='raffle1'));




COMMIT;




