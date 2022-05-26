DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    email varchar(50) NOT NULL UNIQUE,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    phone varchar(50) NOT NULL,
    password varchar(250) NOT NULL
);

INSERT INTO users (email, firstName, lastName, phone, password) VALUES ('john@gmail.com', 'John', 'Thompson', '353664', '123');
INSERT INTO users (email, firstName, lastName, phone, password) VALUES ('pinzuk@gmail.com', 'Pinchas', 'Zukerman', 'qwerty', '444');

CREATE TABLE sessions(
    session_id SERIAL,
    user_email varchar(50) NOT NULL,
    ip varchar(250) NOT NULL,
    date varchar(250) NOT NULL,
    FOREIGN KEY (user_email) REFERENCES users(email)
);

