/*GRANT ALL PRIVILEGES ON DATABASE bookstore TO ayadinouDB;
#CONNECT TO bookstore USER ayadinouDB PASSWORD;
*/

GO
USE bookstore
GO

CREATE TABLE book (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    author varchar(255),
    language varchar(255),
    pages int,
    price float,
    PRIMARY KEY (id)
);

INSERT INTO book (id, name, author, language, pages, price) 
VALUES
(1, 'Less Than Zero', 'Stuart J. Murphy, Frank Remkiewicz', 'English', 40, 5.99),
(2, 'Truesight', 'David Stahler', 'English', 256, 10.99),
(3, 'Living On Luck', 'Charles Bukowski', 'English', 304, 18.99),
(4, 'Stardust', 'Neil Gaiman', 'English', 352, 7.99);


CREATE TABLE member (
    id int NOT NULL AUTO_INCREMENT,
    cin int,
    nom varchar(45),
    prenom varchar(45),
    adresse varchar(45),
    PRIMARY KEY (id)
);

INSERT INTO member (id, cin, nom, prenom, adresse) 
VALUES
(1, 12345678, 'Charrad', 'Oumayma', 'Ariana'),
(2, 87654321, 'Doukali', 'Syrine', 'Tunis');