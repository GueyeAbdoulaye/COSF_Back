-- Création du schéma si nécessaire
CREATE SCHEMA IF NOT EXISTS COSF;

-- Création de la table (optionnelle si Spring Boot le fait via JPA, mais utile en test pur H2)
CREATE TABLE IF NOT EXISTS COSF.inscription (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    sex VARCHAR(10),
    birth_date DATE,
    phone VARCHAR(20),
    address VARCHAR(255),
    postal_code VARCHAR(20),
    city VARCHAR(100),
    country VARCHAR(100),
    email VARCHAR(100),
    mutated VARCHAR(100)
);
INSERT INTO COSF.inscription (first_name, last_name, sex, birth_date, phone, address, postal_code, city, country, email, mutated) VALUES
('Alioune', 'Sow', 'male', '1990-05-12', '771234567', '123 rue Dakar', '11000', 'Dakar', 'Senegal', 'alioune@example.com', 'No'),
('Fatou', 'Diop', 'female', '1995-08-23', '770987654', '456 rue Thies', '22000', 'Thies', 'Senegal', 'fatou@example.com', 'Yes'),
('Mamadou', 'Ndiaye', 'male', '2000-02-14', '760123456', '789 rue Rufisque', '33000', 'Rufisque', 'Senegal', 'mamadou@example.com', 'No');


-- Crée la table calendar
CREATE TABLE IF NOT EXISTS COSF.calendar (
    id INT PRIMARY KEY AUTO_INCREMENT,
    journeys VARCHAR(255),
    await_team_id INT,
    match_date DATE,
    location VARCHAR(255),
    status VARCHAR(50),
    score_home INT,
    score_away INT,
    season_id INT
);

INSERT INTO COSF.calendar (journeys, await_team_id, match_date, location, status, score_home, score_away, season_id) VALUES
('Journée 1', 1, '2025-07-15', 'Stade Léopold Sédar Senghor', 'À venir', NULL, NULL, 1),
('Journée 2', 2, '2025-07-22', 'Stade Iba Mar Diop', 'Terminé', 78, 82, 1),
('Journée 3', 3, '2025-10-01', 'Arène Nationale', 'Reporté', NULL, NULL, 1),
('Journée 4', 1, '2025-10-08', 'Stade Marius Ndiaye', 'Terminé', 90, 85, 1);

-- Crée la table joueur
CREATE TABLE IF NOT EXISTS COSF.joueur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    poste VARCHAR(50),
    numero INT
);

INSERT INTO COSF.joueur (nom, prenom, poste, numero) VALUES
('Doncic', 'Luka', 'Meneur', 77),
('Fall', 'Moustapha', 'Pivot', 21),
('Anderson', 'Kyle', 'Ailier', 1),
('Gasol', 'Pau', 'Pivot', 16),
('Ndiaye', 'Moussa', 'Ailier fort', 12),
('Smith', 'Jalen', 'Arrière', 8);


-- Création de la table standing
CREATE TABLE IF NOT EXISTS COSF.standing (
    id INT PRIMARY KEY AUTO_INCREMENT,
    team_id INT NOT NULL,
    points INT NOT NULL,
    played INT NOT NULL,
    wins INT NOT NULL,
    losses INT NOT NULL,
    draws INT NOT NULL,
    penalties INT NOT NULL,
    forfeits INT NOT NULL,
    defaulted INT NOT NULL,
    point_scored INT NOT NULL,
    point_against INT NOT NULL,
    point_difference INT NOT NULL,
    season_id INT NOT NULL
);

INSERT INTO COSF.standing (team_id, points, played, wins, losses, draws, penalties, forfeits, defaulted, point_scored, point_against, point_difference, season_id) VALUES
(1, 10, 5, 3, 2, 0, 1, 0, 0, 300, 250, 50, 1),
(2, 8, 5, 2, 3, 0, 0, 0, 0, 280, 270, 10, 1),
(3, 6, 5, 1, 4, 0, 0, 0, 0, 260, 290, -30, 2),
(4, 4, 5, 0, 5, 0, 0, 0, 0, 240, 310, -70, 2);

-- Crée la table Season
CREATE TABLE IF NOT EXISTS COSF.season (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
    );

INSERT INTO COSF.season (name) VALUES
('seasonRegular'),
('seasonPlayoffs'),
('seasonFinals');

-- Crée la table Team

CREATE TABLE IF NOT EXISTS COSF.teams (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

INSERT INTO COSF.teams (name) VALUES
('Team A'),
('Team B'),
('Team C'),
('Team D');

-- Crée la table User
CREATE TABLE IF NOT EXISTS COSF."user" (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(50)
);

INSERT INTO COSF."user" (username, lastname, password, email, role) VALUES
('Abdoulaye', 'Gueye', 'password123', 'gueye@example.com', 'ADMIN'),
('Fatou', 'Diop', 'password456', 'fatou@example.com', 'USER'),
('Luka', 'Doncic', 'mavspass', 'luka@example.com', 'USER');

