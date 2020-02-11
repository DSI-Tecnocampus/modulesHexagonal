INSERT INTO user_lab VALUES ('roure', 'Josep', 'Roure', 'roure@tecnocampus.cat', '{bcrypt}$2a$10$0VGzG8lfiDXBnFTE98lfiOLtP4uh62wnE6iWs5.2AMrJ3G9k7XZqu', 1);
INSERT INTO user_lab VALUES ('alvarez', 'Sergi', 'Alvarez', 'alvarez@mail.cat', '{bcrypt}$2a$10$7PFxXn4TQRiut9jNcAl7AubQZUWWck/eML3TDaQtoZiWNEN6o.Ig6', 1);
INSERT INTO user_lab VALUES ('castells', 'Esther', 'Castells', 'castells@mail.cat', '{bcrypt}$2a$10$vvAubuSVVGFBx0I39N6EQeU4Z9rVcXadAyANbSfsuvdJ5VaVOfURS', 1);
INSERT INTO user_lab VALUES ('riera', 'Joana', 'Riera', 'riera@mail.cat', '{bcrypt}$2a$10$wnw4VWrWAnFZmqPdWS5b1OtJxLyblMMNLImfbuFIm0hTUDp3q0SJy', 1);
INSERT INTO user_lab VALUES ('garcia', 'Marcel', 'Garcia', 'garcia@mail.cat', '{bcrypt}$2a$10$ljdVKsjPBx.P61hjFNFVHOGsUe76QpRxxGBLG5WCgnPNlNhxr0thG', 1);
INSERT INTO user_lab VALUES ('lecina', 'Maria', 'Lecina', 'lecina@mail.cat', '{bcrypt}$2a$10$8J6t9dJ7KCU1KGBd7E2/2enlG1tyNkzoswFbckO01Efwp6wF6MH7u', 1);


INSERT INTO note_lab (title, content, date_creation, date_edit, owner) VALUES ('Spring', 'va super be', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'roure');
INSERT INTO note_lab (title, content, date_creation, date_edit, owner) VALUES ('Spring Boot', 'va encara millor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'roure');
INSERT INTO note_lab (title, content, date_creation, date_edit, owner) VALUES ('I love this lab', 'in this fantastic lab Im learning quite a lot', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'roure');
INSERT INTO note_lab (title, content, date_creation, date_edit, owner) VALUES ('TCM', 'TCM is the best in the whole world', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'roure');


INSERT INTO authorities (username, role) VALUES ('roure', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('roure', 'ROLE_ADMIN');
INSERT INTO authorities (username, role) VALUES ('alvarez', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('castells', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('riera', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('garcia', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('lecina', 'ROLE_USER');
