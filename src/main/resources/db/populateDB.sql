DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES
    (100000, '2020-01-30 07:00', 'Завтрак(блины)', 800),
    (100000, '2020-01-30 12:00', 'Обед', 1000),
    (100001, '2020-01-30 07:00', 'Завтрак(бутерброды)', 800),
    (100001, '2020-01-30 12:00', 'Обед', 1000);