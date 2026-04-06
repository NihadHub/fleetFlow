ALTER TABLE chauffeurs MODIFY disponible BOOLEAN NOT NULL DEFAULT TRUE;
ALTER TABLE vehicule MODIFY statut VARCHAR(50) NOT NULL DEFAULT 'DISPONIBLE';
ALTER TABLE livraisons MODIFY statut VARCHAR(50) NOT NULL DEFAULT 'EN_ATTENTE';

ALTER TABLE vehicule ADD CONSTRAINT check_capacite_positive CHECK (capacite > 0);

ALTER TABLE livraisons
    ADD CONSTRAINT fk_livraisons_client FOREIGN KEY (client_id) REFERENCES client(client_id),
    ADD CONSTRAINT fk_livraisons_chauffeur FOREIGN KEY (chauffeur_id) REFERENCES chauffeurs(id),
    ADD CONSTRAINT fk_livraisons_vehicule FOREIGN KEY (vehicule_id) REFERENCES vehicule(vehicule_id);
