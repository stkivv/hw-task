DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS car;

CREATE TABLE user (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE car (
    id INTEGER PRIMARY KEY,
    user_id INTEGER, 
    make TEXT NOT NULL,
    model TEXT NOT NULL,
    number_plate TEXT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES user(id)
);

