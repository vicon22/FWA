DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS sesions;

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    email varchar(50) NOT NULL UNIQUE,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    phone varchar(50) NOT NULL,
    password varchar(250) NOT NULL
);

CREATE TABLE sessions(
    session_id SERIAL,
    user_id int NOT NULL,
    ip varchar(250) NOT NULL,
    date varchar(250) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


