INSERT INTO calendar_db.users
	(`first_name`, `last_name`, `email`, `password`)
VALUES
	("Spencer", "Keene", "spencermail@email.com", "password123"),
    ("Connor", "Della-Savia", "connboy@email.com", "Testing");

INSERT INTO calendar_db.events
	(`name`, `start_date`, `end_date`)
VALUES
	("Birthday party", "2022 3 31 18:00", "2022 3 31 21:00"),
    ("Submit project report", "2021 11 25 23:30", "2021 11 25 23:59"),
	("Project presentation", "2021 12 2 17:00", "2021 12 2 17:30"),
    ("Some past event", "2021 11 10 11:00", "2021 11 10 13:00"),
    ("Study for exam", "2021 12 5 19:30", "2021 12 5 22:00"),
    ("Breakfast with a friend", "2021 12 4 9:00", "2021 12 4 10:00"),
    ("Work on assignment", "2021 12 04 12:00", "2021 12 04 14:00");

INSERT INTO calendar_db.event_user
	(`user_email`, `event_id`)
VALUES
	("spencermail@email.com", 1),
	("connboy@email.com", 2),
    ("connboy@email.com", 3),
	("connboy@email.com", 4),
    ("spencermail@email.com", 5),
    ("spencermail@email.com", 6),
    ("spencermail@email.com", 7);

