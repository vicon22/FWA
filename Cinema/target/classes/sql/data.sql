INSERT INTO fwa.users (email, firstName, lastName, phone, password) VALUES ('john@gmail.com', 'John', 'Thompson', '353664', '$2a$10$yvz9W21wxzQU1zVFCw994.tOuGYeZLRWy8Z01EJUzlz8xU4MnT4g2'); -- pass:123
INSERT INTO fwa.users (email, firstName, lastName, phone, password) VALUES ('pinzuk@gmail.com', 'Pinchas', 'Zukerman', 'qwerty', '$2a$10$yA9drzJachlWJ5jfejZN7OUtwfCCiYyGw/9KZMQDrd3uUgOWVnWxa'); -- pass:444
INSERT INTO fwa.users (email, firstName, lastName, phone, password) VALUES ('z.isupov@gmail.com', 'Evgenii', 'Isupov', '+7 (905) 353-56-73', '$2a$10$JCHTHwi/QC7WVUussjeKt./9s/Dn17OalJ2Ox.cRsQIvBEsDnqJ1G'); -- pass:pleaseHireMe

INSERT INTO fwa.sessions (user_email, ip, date, time) VALUES ('z.isupov@gmail.com', '127.0.0.1', '2022-05-27', '14:22:05');
INSERT INTO fwa.sessions (user_email, ip, date, time) VALUES ('z.isupov@gmail.com', '127.0.0.1', '2022-05-30', '16:54:37');
INSERT INTO fwa.sessions (user_email, ip, date, time) VALUES ('z.isupov@gmail.com', '127.0.0.1', '2022-06-02', '10:56:53');