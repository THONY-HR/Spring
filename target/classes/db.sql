CREATE DATABASE Compte;
USE Compte;

CREATE TABLE Compte (
    id INT PRIMARY KEY AUTO_INCREMENT,
    num VARCHAR(30) NOT NULL,
    status ENUM('Actif', 'Inactif') NOT NULL,
    solde DECIMAL(10,2) NOT NULL DEFAULT 0.00
);

CREATE TABLE Mouvement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    compte_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL CHECK (amount >= 0),
    type ENUM('Debit', 'Credit') NOT NULL,
    FOREIGN KEY (compte_id) REFERENCES Compte(id) ON DELETE CASCADE
);

SELECT * FROM Compte ORDER BY id DESC LIMIT 1;
UPDATE Compte set solde=1000 where id=14;
DELETE FROM Mouvement;