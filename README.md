# FleetFlow API

## 1. Nom du projet

**Nom du projet :** FleetFlow API – Système de Gestion des Livraisons

---

# 2. Présentation du projet

FleetFlow API est une API REST développée avec Spring Boot permettant de gérer les clients, les véhicules, les chauffeurs et les livraisons d'une entreprise de transport. Elle s'adresse aux gestionnaires logistiques et aux chauffeurs. Son objectif principal est de faciliter la planification, le suivi et la gestion des livraisons grâce à une architecture sécurisée, performante et maintenable.

---

# 3. Problématique

Le problème identifié est que la gestion des livraisons est souvent réalisée de manière manuelle, ce qui complique l'organisation des tournées, le suivi des véhicules et l'affectation des chauffeurs.

La solution proposée permet de centraliser toutes les opérations logistiques dans une API REST sécurisée offrant la gestion des clients, des véhicules, des chauffeurs et des livraisons.

---

# 4. Fonctionnalités principales

- Gérer les clients (CRUD).
- Gérer les véhicules (CRUD).
- Gérer les chauffeurs (CRUD).
- Gérer les livraisons (CRUD).
- Affecter un chauffeur et un véhicule à une livraison.
- Rechercher les livraisons par client, statut ou période.
- Authentifier les utilisateurs avec JWT.
- Gérer les rôles (ADMIN, MANAGER et CHAUFFEUR).
- Paginer et trier les données.
- Valider les données avec Bean Validation.
- Documenter l'API avec Swagger.
- Déployer l'application avec Docker et Docker Compose.
- Automatiser le build et les tests avec GitHub Actions.

---

# 5. Technologies utilisées

| Technologie | Utilisation dans le projet |
|-------------|----------------------------|
| Java 21 | Développement de l'application |
| Spring Boot | Développement de l'API REST |
| Spring Data JPA | Accès aux données |
| Hibernate | Mapping Objet-Relationnel |
| MySQL | Base de données relationnelle |
| Flyway | Gestion des migrations |
| Spring Security | Sécurisation de l'API |
| JWT | Authentification des utilisateurs |
| MapStruct | Conversion Entity ↔ DTO |
| Lombok | Réduction du code répétitif |
| Maven | Gestion des dépendances |
| Swagger / OpenAPI | Documentation interactive |
| JUnit 5 | Tests unitaires |
| Docker | Conteneurisation |
| Docker Compose | Orchestration des conteneurs |
| GitHub Actions | Intégration Continue (CI/CD) |
| Git & GitHub | Gestion des versions |

Nous avons utilisé **Spring Boot** pour développer une API REST professionnelle.

Nous avons utilisé **Spring Security** et **JWT** afin de sécuriser les endpoints selon les rôles utilisateurs.

Nous avons utilisé **Flyway** pour gérer automatiquement les migrations de la base de données.

Nous avons utilisé **Docker** et **Docker Compose** afin de faciliter le déploiement de l'application.

---

# 6. Installation et lancement

## 6.1 Prérequis

Pour utiliser ce projet, vous devez disposer de :

- Java 21
- Maven
- Docker
- Docker Compose
- Git
- IntelliJ IDEA ou Visual Studio Code

---

## 6.2 Cloner le dépôt

```bash
git clone https://github.com/VOTRE_COMPTE/fleetflow-api.git
```

---

## 6.3 Ouvrir le dossier

```bash
cd fleetflow-api
```

---

## 6.4 Installer les dépendances

```bash
mvn clean install
```

---

## 6.5 Variables d'environnement

Créer un fichier `.env` ou configurer les variables suivantes :

```env
DB_URL=jdbc:mysql://mysql:3306/fleetflow
DB_USERNAME=root
DB_PASSWORD=password
JWT_SECRET=votre_secret
JWT_EXPIRATION=86400000
```

---

## 6.6 Lancer le projet

Avec Maven :

```bash
mvn spring-boot:run
```

Ou avec Docker Compose :

```bash
docker compose up --build
```

Arrêter les conteneurs :

```bash
docker compose down
```

---

## 6.7 Ouvrir le projet

Swagger :

```
http://localhost:8080/swagger-ui/index.html
```

API :

```
http://localhost:8080
```

### Point de vigilance

- Vérifier que Docker est lancé.
- Vérifier que MySQL fonctionne correctement.
- Ne jamais publier les secrets JWT ni les mots de passe.

---

# 7. Captures d'écran

## Capture 1

### Titre

Documentation Swagger

```md
![Swagger](images/swagger.png)
```

### Explication

Cette capture montre la documentation Swagger avec les différents endpoints de l'API.

---

## Capture 2

### Titre

Docker Compose

```md
![Docker](images/docker-compose.png)
```

### Explication

Cette capture montre l'application exécutée avec Docker Compose et les conteneurs MySQL et Spring Boot.

---

# 8. Contribution personnelle

Ce projet a été réalisé individuellement.

J'ai développé l'ensemble de l'API REST, conçu la base de données, créé les entités, les DTO, les mappers MapStruct, les services, les contrôleurs REST et les repositories.

J'ai également intégré Spring Security, JWT, la gestion des rôles, Flyway, Docker, Docker Compose, GitHub Actions, Swagger, les tests unitaires avec JUnit, la pagination, le tri ainsi que les interfaces de services et leurs implémentations.

---

# 9. Difficultés rencontrées

## Difficulté 1

### Problème rencontré

Mettre en place une authentification JWT avec gestion des rôles.

### Recherches / Tests

J'ai étudié Spring Security, AuthenticationManager, JWT Filter, UserDetailsService et la gestion des rôles.

### Solution

J'ai développé un système complet d'authentification avec JWT, BCryptPasswordEncoder, SecurityFilterChain et un contrôle d'accès basé sur les rôles.

### Ce que j'ai appris

J'ai appris à sécuriser une API REST professionnelle avec Spring Security et JWT.

### Texte final

J'ai rencontré des difficultés lors de la mise en place de la sécurité de l'API. Après plusieurs recherches, j'ai configuré Spring Security, JWT et les rôles utilisateurs afin de protéger efficacement les endpoints. Cette expérience m'a permis d'améliorer mes compétences en sécurité des applications backend.

---

## Difficulté 2

### Problème rencontré

Déployer correctement l'application avec Docker Compose tout en appliquant automatiquement les migrations Flyway.

### Recherches / Tests

J'ai étudié le fonctionnement de Docker Compose, des réseaux Docker et de Flyway.

### Solution

J'ai configuré Docker Compose avec deux conteneurs (MySQL et Spring Boot), les variables d'environnement et Flyway afin que les migrations soient exécutées automatiquement au démarrage.

### Ce que j'ai appris

J'ai appris à déployer une application Spring Boot dans un environnement Docker proche d'un contexte professionnel.

---

# 10. Améliorations possibles

Dans une prochaine version, je pourrais :

- ajouter un système de notifications pour les chauffeurs ;
- intégrer le suivi GPS des véhicules ;
- développer des tableaux de bord statistiques ;
- mettre en cache les données avec Redis afin d'améliorer les performances.

### Conclusion

Ces améliorations permettraient d'optimiser les performances, le suivi des livraisons et l'expérience des utilisateurs.

---

# ✅ Checklist finale

## Présentation

- [x] Le nom du projet est clair.
- [x] Le projet est présenté en 3 à 5 lignes.
- [x] Le public cible est identifié.
- [x] Le besoin est expliqué.
- [x] L'objectif est précisé.

## Fonctionnalités

- [x] Les fonctionnalités principales sont présentes.
- [x] Chaque fonctionnalité commence par un verbe.
- [x] Elles correspondent aux fonctionnalités développées.

## Technologies

- [x] Les technologies sont indiquées.
- [x] Leur rôle est expliqué.

## Installation

- [x] Les prérequis sont présents.
- [x] Les commandes fonctionnent.
- [x] Les variables d'environnement sont indiquées.
- [x] Swagger est accessible.
- [x] Aucune donnée sensible n'est publiée.

## Captures

- [ ] Ajouter les captures Swagger, Docker et Postman.

## Contribution

- [x] La contribution personnelle est clairement expliquée.

## Difficultés

- [x] Les difficultés sont expliquées.
- [x] Les solutions sont présentées.
- [x] Les apprentissages sont décrits.

## Améliorations

- [x] Les améliorations sont réalistes.

---

# Validation finale

Une personne qui découvre ce projet peut comprendre :

- l'objectif de l'API FleetFlow ;
- les fonctionnalités proposées ;
- les technologies utilisées ;
- l'architecture mise en place ;
- la manière d'exécuter le projet ;
- le fonctionnement de la sécurité (JWT et rôles) ;
- l'utilisation de Docker, Docker Compose et GitHub Actions ;
- les améliorations prévues.