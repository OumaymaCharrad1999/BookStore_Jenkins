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

INSERT INTO book (name, author, language, pages, price) 
VALUES
('Less Than Zero', 'Stuart J. Murphy, Frank Remkiewicz', 'English', 40, 5.99),
('Truesight', 'David Stahler', 'English', 256, 10.99),
('Living On Luck', 'Charles Bukowski', 'English', 304, 18.99),
('Stardust', 'Neil Gaiman', 'English', 352, 7.99);
