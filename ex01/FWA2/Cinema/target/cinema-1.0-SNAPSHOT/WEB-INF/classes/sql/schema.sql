DROP TABLE IF EXISTS users;
CREATE TABLE users(
    id SERIAL,
    email varchar(50) NOT NULL,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    phone varchar(50) NOT NULL,
    password varchar(250) NOT NULL
);