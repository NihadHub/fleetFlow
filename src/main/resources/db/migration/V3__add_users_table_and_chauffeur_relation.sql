CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    role ENUM('ADMIN', 'MANAGER', 'CHAUFFEUR') NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Drop the foreign key constraint that prevents dropping the id column in chauffeurs
ALTER TABLE livraisons DROP FOREIGN KEY fk_livraisons_chauffeur;

-- Refactor chauffeurs table for JOINED inheritance
ALTER TABLE chauffeurs DROP COLUMN id;
ALTER TABLE chauffeurs ADD COLUMN id BIGINT PRIMARY KEY;
ALTER TABLE chauffeurs ADD CONSTRAINT fk_chauffeurs_user_id FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE;

-- Re-add the foreign key constraint for livraisons
ALTER TABLE livraisons ADD CONSTRAINT fk_livraisons_chauffeur FOREIGN KEY (chauffeur_id) REFERENCES chauffeurs(id);
