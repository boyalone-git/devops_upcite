# Solidarité Arrivants — Architecture Microservices & Kubernetes

Plateforme d'entraide mettant en relation des associations d'accueil et des bénévoles, déployée sur Kubernetes (Minikube).

## Architecture & Technologies
- Service Annuaire (annuaire/, port 8080) : API REST Spring Boot (Java 21) + Front-Office HTML/CSS/JS. Communique avec le service Bénévoles via RestClient (http://service-benevoles:8081).
- Service Bénévoles (benevoles/, port 8081) : API REST Spring Boot (Java 21) connectée à PostgreSQL via Spring Data JPA.
- Base de données (db-benevoles, port 5432) : PostgreSQL 16 avec stockage persistant Kubernetes (PersistentVolumeClaim).
- Gateway & Sécurité (k8s/) : Ingress NGINX (/api/benevoles et /) et contrôle d'accès RBAC (ServiceAccount, Role, RoleBinding).

## Images Docker Hub
- quelebara/service-annuaire:latest
- quelebara/service-benevoles:latest

## Déploiement sur Kubernetes (Minikube)
    minikube start --driver=docker --memory=3072mb
    minikube addons enable ingress
    kubectl apply -f k8s/
    minikube tunnel

## Endpoints principaux (via Gateway http://localhost)
- GET / : Interface web Front-Office
- GET /api/associations/synthese : Agrégation inter-services (annuaire -> benevoles)
- GET / POST / PUT / DELETE /api/associations : Gestion des associations
- GET / POST /api/benevoles : Gestion des bénévoles (persistés sous PostgreSQL)
