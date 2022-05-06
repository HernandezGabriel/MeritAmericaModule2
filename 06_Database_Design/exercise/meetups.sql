BEGIN TRANSACTION;

DROP TABLE IF EXISTS event_member, group_member, event, "group", member CASCADE;



CREATE TABLE "group" (
    group_id serial NOT NULL,
    group_name varchar(50) NOT NULL,
	CONSTRAINT PK_group PRIMARY KEY(group_id)
);


CREATE TABLE member (
    member_id serial NOT NULL ,
    first_name varchar(200) NOT NULL,
   last_name varchar(200) NOT NULL,
	    email varchar(200) NULL,
	    phone_number varchar(200) NULL,
    birthday date NULL,
	remindersOn boolean not NULL,
	CONSTRAINT PK_member PRIMARY KEY(member_id)
);



CREATE TABLE event (
    event_id serial NOT NULL,
    event_name varchar(200) NOT NULL,
    description text NULL,
    start_datetime date NULL,
    length_minutes int NOT NULL,
    group_in_charge_id int NOT NULL,
	CONSTRAINT PK_event PRIMARY KEY(event_id),
    CONSTRAINT FK_group_in_charge FOREIGN KEY(group_in_charge_id) REFERENCES "group"(group_id)
);


CREATE TABLE event_member (
    event_id int NOT NULL,
    member_id int NOT NULL,
	CONSTRAINT PK_event_member PRIMARY KEY(event_id, member_id),
    CONSTRAINT FK_event_member_event FOREIGN KEY(event_id) REFERENCES event(event_id),
    CONSTRAINT FK_event_member_member FOREIGN KEY(member_id) REFERENCES member(member_id)
);

CREATE TABLE group_member (
    group_id int NOT NULL,
    member_id int NOT NULL,
	CONSTRAINT PK_group_member PRIMARY KEY(group_id, member_id),
    CONSTRAINT FK_group_member_group FOREIGN KEY(group_id) REFERENCES "group"(group_id),
    CONSTRAINT FK_group_member_member FOREIGN KEY(member_id) REFERENCES member(member_id)
);
COMMIT;

