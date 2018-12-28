# Favoride
Projet C.O.O. M1-MIAGE 2018-2019

## Présentation générale
Il s’agit de développer un logiciel qui gère une plate-forme de covoiturage. La plate-forme
comporte un certain nombre d’utilisateurs qui peuvent participer à des trajets, soit en tant que
conducteurs, soit en tant que passagers.
On considère que les utilisateurs existent déjà dans la base de données, il n’est pas demandé
d’implémenter de fonctionnalité de gestion de la liste des utilisateurs.
Les utilisateurs peuvent proposer un trajet (l’utilisateur qui propose le trajet est alors le conducteur),
et ensuite d’autres utilisateurs peuvent s’inscrire sur ce trajet en tant que passager.
Il existe une plate-forme de recherche qui permet de lister les trajets à venir selon des critères (au
minimum : lieu de départ et d’arrivée, ainsi que date), permettant aux futurs passagers de trouver un
trajet adéquat.

## Cas d'utilisation
* CU1 - Connexion à l'application et consultation des informations du profil
* CU2 - Proposition d'un trajet
* CU3 - Recherche d'un trajet et inscription
* CU4 - Consultation des trajets selon une période
* CU5 - Consultation des détails d'un trajet et annulation

## Taches Maven
* `mvn clean` : Réinitialise le dossier target
* `mvn test` : Déploie la base de données (Purge)
* `mvn jfx:run`  : Déploie la base de données (Purge) et exécute l'application
* `mvn jfx:jar` : Génère un fichier .jar exécutable
* `java -jar target/jfx/app/favoride-1.0-jfx.jar` : Exécute le fichier .jar

## Déploiement
/!\ Le projet a été développé sous JDK 8, il est impératif de disposer de Java 1.8 par défaut sur la machine pour exécuter l'application /!\
* Démarrer les serveurs MySQL
* Modifier le fichier pom.xml en fonction des informations de connexion (versionning mysql-connector-java, url, utilisateur et mot de passe)
* Modifier le fichier resources/config/config.yaml (url, username, password)
* Déployer la base de données sur la machine
* Générer une archive .jar de l'application
* Exécuter l'archive .jar générée

## Mises-à-jour depuis la recette
* Accès bloqué à l'application lors d'une mauvaise saisie d'identifiants
* Consultation de ses trajets en tant que passager
* Suppression d'un trajet en tant que conducteur

## Auteurs
Simon BACQUET & Yacine CHTAIRI