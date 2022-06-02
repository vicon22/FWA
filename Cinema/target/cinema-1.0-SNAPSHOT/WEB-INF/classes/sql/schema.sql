-- DROP TABLE IF EXISTS sessions;
-- DROP TABLE IF EXISTS users CASCADE;

DROP SCHEMA IF EXISTS fwa CASCADE;

CREATE SCHEMA fwa;

CREATE TABLE fwa.users(
    id SERIAL PRIMARY KEY,
    email varchar(50) NOT NULL UNIQUE,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    phone varchar(50) NOT NULL,
    password varchar(250) NOT NULL
);

CREATE TABLE fwa.sessions(
    session_id SERIAL,
    user_email varchar(50) NOT NULL,
    ip varchar(250) NOT NULL,
    date varchar(250) NOT NULL,
    time varchar(250) NOT NULL,
    FOREIGN KEY (user_email) REFERENCES fwa.users(email)
);
