
INSERT INTO chauffeurs (id, nom, telephone, permis_type, disponible) VALUES
(3, 'Ahmed Benani', '0661223344', 'CE', TRUE),
(4, 'Said El Alami', '0665556677', 'B', TRUE);

INSERT INTO client (nom, telephone, ville, email, password) VALUES
('Société Alpha', '0522112233', 'Casablanca', 'contact@alpha.ma', 'clientpass1'),
('Logistique Beta', '0537445566', 'Rabat', 'info@beta.ma', 'clientpass2'),
('Distri Gamma', '0539112233', 'Tanger', 'sales@gamma.ma', 'clientpass3');

INSERT INTO vehicule (matricule, type, capacite, statut) VALUES
('12345|A|1', 'CAMION', 12000, 'DISPONIBLE'),
('67890|B|15', 'FOURGON', 3500, 'DISPONIBLE'),
('11223|C|20', 'VOITURE', 500, 'MAINTENANCE'),
('44556|D|6', 'MOTO', 50, 'DISPONIBLE');


INSERT INTO livraisons (date_livraison, adresse_depart, adresse_destination, statut, client_id, chauffeur_id, vehicule_id) VALUES
('2026-06-15', 'Port de Casablanca', 'Zone Industrielle Sapino', 'EN_ATTENTE', 1, 3, 1),
('2026-06-16', 'Entrepôt Rabat', 'Centre Ville Tanger', 'EN_ATTENTE', 2, 4, 2),
('2026-06-17', 'Magasin Casablanca', 'Quartier Agdal Rabat', 'EN_ATTENTE', 1, 3, 2);
