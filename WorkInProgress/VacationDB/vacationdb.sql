BEGIN TRANSACTION;

DROP TABLE IF EXISTS person;

CREATE TABLE person (
    person_id int NOT NULL DEFAULT nextval('person_serial'),
    leader_id int NULL ,
    person_name varchar(200) NOT NULL,
    birthday date NULL,
    group_name varchar(200) NULL,
    phone_number varchar(10) NULL,


	CONSTRAINT PK_person PRIMARY KEY(person_id),
   CONSTRAINT FK_person_leader FOREIGN KEY leader_id REFERENCES person(person_id)
   );

CREATE TABLE guide (
    guide_id int NOT NULL DEFAULT nextval('guide_serial'),
    company_name varchar(200) NULL,
    person_name varchar(200) NOT NULL,
    birthday date NULL,
    phone_number varchar(10) NOT NULL,

	CONSTRAINT PK_guide PRIMARY KEY(guide_id)
   );

CREATE TABLE activity (
   activity_id int NOT NULL DEFAULT nextval('activity_serial'),
   guide_id int NULL,
   activity_website varchar(200) NULL,
   activity_type varchar(30),
   price money NULL,
   place_address varchar(200) NOT NULL,
   town_name varchar(200) NOT NULL,
   country_name varchar(200) NULL,
   notes varchar(200) NULL,



   CONSTRAINT PK_activity PRIMARY KEY(activity_id)),
   CONSTRAINT FK_activity_guide FOREIGN KEY guide_id REFERENCES guide(guide_id)

)

CREATE TABLE reservation (
   activity_id int NOT NULL

)





   SELECT setval('person_serial', (SELECT MAX(person_id) + 1000000 FROM person));

commit;