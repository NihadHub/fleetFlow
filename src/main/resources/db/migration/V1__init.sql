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
    permis_type VARCHAR(50) NOT NULL,
    disponible BOOLEAN NOT NULL
);

CREATE TABLE vehicule (
    vehicule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    matricule VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL,
    capacite DOUBLE NOT NULL,
    statut VARCHAR(50) NOT NULL
);

CREATE TABLE livraisons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_livraison DATE NOT NULL,
    adresse_depart VARCHAR(255) NOT NULL,
    adresse_destination VARCHAR(255) NOT NULL,
    statut VARCHAR(50) NOT NULL,
    client_id BIGINT NOT NULL,
    chauffeur_id BIGINT,
    vehicule_id BIGINT
);
