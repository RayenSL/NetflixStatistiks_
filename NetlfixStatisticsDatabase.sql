USE master
DROP DATABASE IF EXISTS NetflixStatistics;
CREATE DATABASE NetflixStatistics;
GO
USE NetflixStatistics;

-- CREATING TABLES
CREATE TABLE Account(
AccountID int NOT NULL IDENTITY PRIMARY KEY,
Name nvarchar(15) NOT NULL,
StreetName nvarchar(25) NOT NULL,
HouseNumber nvarchar(5) NOT NULL,
HouseNumberAddition nvarchar(5),
PostalCode nvarchar(7) NOT NULL,
City nvarchar(25) NOT NULL
);

CREATE TABLE Profile(
ProfileID int NOT NULL IDENTITY,
ProfileName nvarchar(15) NOT NULL,
DateOfBirth date NOT NULL,
AccountID int CONSTRAINT Profile_FK REFERENCES Account(AccountID) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY (ProfileID, ProfileName, AccountID)
);

CREATE TABLE Movie(
MovieID int NOT NULL IDENTITY PRIMARY KEY,
Title nvarchar(50) NOT NULL UNIQUE,
Duration time(2) NOT NULL,
Genre nvarchar(20) NOT NULL,
Language nvarchar(20) NOT NULL,
AgeIndication int NOT NULL,
);

CREATE TABLE Serie(
SerieID int NOT NULL IDENTITY,
Title nvarchar(40) NOT NULL UNIQUE,
Genre nvarchar(20) NOT NULL,
Language nvarchar(20) NOT NULL,
AgeIndication int NOT NULL,
SimilarTo nvarchar(40),
PRIMARY KEY (SerieID)
);

CREATE TABLE Episode(
EpisodeID int NOT NULL IDENTITY PRIMARY KEY,
SerieID int CONSTRAINT Serie_FK REFERENCES Serie(SerieID) ON DELETE CASCADE ON UPDATE CASCADE,
Title nvarchar(50) NOT NULL,
Duration time(2) NOT NULL
);



-- PROFILES & ACCOUNTS

-- RAYEN
INSERT INTO Account (Name,Streetname,HouseNumber,HouseNumberAddition,PostalCode,City)
VALUES ('Rayen Syal','Laan van Avant-Garde','22','','3069SZ','Rotterdam');

INSERT INTO Profile (ProfileName,DateOfBirth,AccountID) VALUES
('Ray','2000-10-26','1'),
('lodewijck','1990-04-01','1'),
('Hendricks','1996-07-22','1');

-- KELVIN
INSERT INTO Account (Name,Streetname,HouseNumber,HouseNumberAddition,PostalCode,City)
VALUES ('Kelvin van Dam','Peergaarde','25','','3044GK','Hendrick-Ido-Ambacht');

INSERT INTO Profile (ProfileName,DateOfBirth,AccountID) VALUES
('Kelvin','1999-01-04','2'),
('Julia','1999-06-01','2'),
('Annie','1999-04-01', '2');

-- RUUD
INSERT INTO Account (Name,Streetname,HouseNumber,HouseNumberAddition,PostalCode,City)
VALUES ('Ruud Hermans','Lovensdijkstraat','63','','4818AJ','Breda');

INSERT INTO Profile (ProfileName,DateOfBirth,AccountID) VALUES
('Ruud','1985-05-01','3');



-- FILMS
INSERT INTO Movie (Title, Duration, Genre, Language, AgeIndication) VALUES
('The Scavengers', '02:10:10', 'ACTION', 'ENGLISH', '12'),
('Mission ispossible 20', '01:50:10', 'COMEDY', 'ENGLISH', '12'),
('Harry Snotter and the order of the Illuminati', '05:01:10', 'FANTASY', 'ENGLISH', '18'),
('Titanics', '02:10:30', 'DRAMA', 'FRENCH', '18'),
('Slow and the Furious 68', '04:10:40', 'ACTION', 'DUTCH', '12'),
('De Zeemerminnen van de Noordzee', '01:20:00', 'ADVENTURE', 'DUTCH', '12');

-- SERIE
INSERT INTO Serie (Title, Genre, Language, AgeIndication, SimilarTo) VALUES
('De rennende levenden', 'ACTION', 'DUTCH', '18', 'Whitelist'),
('Whitelist', 'ACTION', 'ENGLISH', '18', 'Riverdale'),
('Riverdale', 'ADVENTURE', 'ENGLISH', '18', 'Breaking good'),
('Breaking good', 'ACTION', 'ENGLISH', '18', 'Scareddevil'),
('Scareddevil', 'ACTION', 'DUTCH', '18', 'De rennede levenden');

-- EPISODE
INSERT INTO Episode (SerieID, Title, Duration) VALUES
('1', 'De rennende levenden Episode 1', '00:55:20'),
('1', 'De rennende levenden Episode 2', '00:54:50'),
('1', 'De rennende levenden Episode 3', '00:53:40'),
('1', 'De rennende levenden Episode 4', '00:52:20'),
('1', 'De rennende levenden Episode 5', '00:51:10'),

('2', 'Whitelist Episode 1', '00:40:50'),
('2', 'Whitelist Episode 2', '00:42:16'),
('2', 'Whitelist Episode 3', '00:47:12'),
('2', 'Whitelist Episode 4', '00:42:55'),
('2', 'Whitelist Episode 5', '00:40:50'),

('3', 'Riverdale Episode 1', '00:53:51'),
('3', 'Riverdale Episode 2', '00:42:00'),
('3', 'Riverdale Episode 3', '00:41:00'),
('3', 'Riverdale Episode 4', '00:38:20'),
('3', 'Riverdale Episode 5', '00:42:00'),

('4', 'Breaking good Episode 1', '00:47:39'),
('4', 'Breaking good Episode 2', '00:38:30'),
('4', 'Breaking good Episode 3', '00:48:36'),
('4', 'Breaking good Episode 4', '00:38:20'),
('4', 'Breaking good Episode 5', '00:53:27'),

('5', 'Scareddevil Episode 1', '00:41:42'),
('5', 'Scareddevil Episode 2', '00:49:20'),
('5', 'Scareddevil Episode 3', '00:38:52'),
('5', 'Scareddevil Episode 4', '00:45:27'),
('5', 'Scareddevil Episode 5', '00:49:00');



