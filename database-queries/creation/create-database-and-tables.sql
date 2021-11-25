CREATE DATABASE calendar_db;

CREATE TABLE calendar_db.users(
first_name varchar(150) NOT NULL,
last_name varchar(150) NOT NULL,
email varchar(150) NOT NULL UNIQUE,
`password` varchar(30)  NOT NULL,
primary key(email)
);

CREATE TABLE calendar_db.`events`(
id int UNIQUE auto_increment,
`name` varchar(150) NOT NULL,
start_date varchar(150) NOT NULL,
end_date varchar(150) NOT NULL,
primary key(id)
);

CREATE TABLE calendar_db.event_user(
user_email varchar(150) NOT NULL,
event_id int NOT NULL,
PRIMARY KEY (event_id, user_email),
FOREIGN KEY (event_id) REFERENCES calendar_db.`events` (id),
FOREIGN KEY (user_email) REFERENCES calendar_db.users (email)
);
