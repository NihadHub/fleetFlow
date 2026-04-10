CREATE TABLE client (
    client_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    telephone VARCHAR(255) NOT NULL,
    ville VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE chauffeurs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    telephone VARCHAR(255) NOT NULL,
    permis_type ENUM('B', 'C', 'D', 'CE', 'DE') NOT NULL,
    disponible BOOLEAN NOT NULL
);

CREATE TABLE vehicule (
    vehicule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    matricule VARCHAR(255) NOT NULL UNIQUE,
    type ENUM('CAMION', 'FOURGON', 'VOITURE', 'MOTO') NOT NULL,
    capacite DOUBLE NOT NULL,
    statut ENUM('DISPONIBLE', 'EN_LIVRAISON', 'MAINTENANCE') NOT NULL
);

CREATE TABLE livraisons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_livraison DATE NOT NULL,
    adresse_depart VARCHAR(255) NOT NULL,
    adresse_destination VARCHAR(255) NOT NULL,
    statut ENUM('EN_ATTENTE', 'EN_COURS', 'LIVRE') NOT NULL,
    client_id BIGINT NOT NULL,
    chauffeur_id BIGINT,
    vehicule_id BIGINT
);
