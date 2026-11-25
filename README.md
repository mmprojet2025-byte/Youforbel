# Youforbel

Application web de **réservation de spectacles** développée avec Spring Boot : gestion des artistes, des spectacles, des lieux et des représentations, avec authentification, rôles et API REST sécurisée par JWT.

> **État : en cours de développement.** Le backend est fonctionnel. Le frontend React ne contient pour l’instant que trois pages (accueil, connexion, artistes) et les tests sont encore minimes.

## Technologies

| Partie | Technologies |
|---|---|
| Backend | Java 21, Spring Boot 4.0.2, Spring Security, JWT (jjwt), Spring Data JPA, Thymeleaf |
| Base de données | MySQL, migrations Flyway (19 scripts avec données de démonstration) |
| Documentation API | springdoc-openapi (Swagger UI) |
| Frontend | React 19, Vite, React Router |

## Données gérées

Artistes, types de spectacle, localités, lieux, spectacles, représentations, rôles et utilisateurs. Le schéma est défini dans `src/main/resources/db/migration/`.

## Lancer le projet

**Prérequis :** JDK 21, MySQL, Node.js.

1. Créer une base MySQL nommée `reservations` et un utilisateur ayant accès à cette base.
2. Renseigner l’URL, l’utilisateur et le mot de passe de la base, de préférence par variables d’environnement plutôt que dans le fichier :

```bash
export SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/reservations?serverTimezone=Europe/Brussels"
export SPRING_DATASOURCE_USERNAME="votre_utilisateur"
export SPRING_DATASOURCE_PASSWORD="votre_mot_de_passe"
```

3. Démarrer le backend. Flyway crée les tables et charge les données de démonstration :

```bash
./mvnw spring-boot:run
```

4. Démarrer le frontend dans un second terminal :

```bash
cd frontend
npm install
npm run dev
```

## Structure

```text
src/main/java/be/iccbxl/pid/youforbel/
  controller/   contrôleurs web et API
  service/      logique métier
  repository/   accès aux données (JPA)
  model/ dto/   entités et objets de transfert
  security/     authentification et JWT
src/main/resources/
  templates/    vues Thymeleaf
  db/migration/ scripts Flyway
frontend/       application React (Vite)
```

## Prochaines étapes

- [ ] Sortir les identifiants de base de données de `application.properties` (variables d’environnement).
- [ ] Ajouter des tests pour les services et les contrôleurs.
- [ ] Compléter le frontend React (spectacles, représentations, réservation).
- [ ] Remplacer la description générique du `pom.xml`.
