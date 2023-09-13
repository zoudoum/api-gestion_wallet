Create DATABASE wallet;
CREATE TABLE client (
                        UserID INT AUTO_INCREMENT PRIMARY KEY,
                        Nom VARCHAR(255),
                        Prenom VARCHAR(255),
                        Email VARCHAR(255) UNIQUE,
                        MotDePasse VARCHAR(255)
);

CREATE TABLE compte (
                        AccountID INT AUTO_INCREMENT PRIMARY KEY,
                        USER_ID INT,
                        Solde DECIMAL(10, 2),
                        TypeDeCompte VARCHAR(50),

                        FOREIGN KEY (USER_ID) REFERENCES client(UserID)
);

CREATE TABLE transaction (
                             TransactionID INT AUTO_INCREMENT PRIMARY KEY,
                             COMPTE_SOURCE INT,
                             COMPTE_DESTINATION INT,
                             Montant DECIMAL(10, 2),
                             DateHeure DATETIME,
                             Statut VARCHAR(50),
                             FOREIGN KEY (COMPTE_SOURCE) REFERENCES compte(AccountID),
                             FOREIGN KEY (COMPTE_DESTINATION) REFERENCES compte(AccountID)
);



