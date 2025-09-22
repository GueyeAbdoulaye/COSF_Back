#!/bin/bash

# Script pour lancer l'analyse SonarQube du backend COSF uniquement

echo "==========================================="
echo "  Analyse SonarQube - Backend COSF Java   "
echo "==========================================="

# Couleurs pour l'affichage
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Fonction pour afficher les messages
print_info() {
    echo -e "${YELLOW}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Vérifier si Maven est installé
if ! command -v mvn &> /dev/null; then
    print_error "Maven n'est pas installé. Veuillez l'installer avant de continuer."
    exit 1
fi

# Vérifier si SonarQube est accessible
print_info "Vérification de la disponibilité de SonarQube..."
if ! curl -s http://localhost:9000/api/system/status | grep -q "UP"; then
    print_error "SonarQube n'est pas accessible sur http://localhost:9000"
    print_info "Veuillez démarrer SonarQube avant de lancer ce script"
    exit 1
fi
print_success "SonarQube est accessible"

# Nettoyer, compiler et exécuter les tests
print_info "Nettoyage du projet..."
mvn clean

print_info "Compilation du projet..."
mvn compile

if [ $? -ne 0 ]; then
    print_error "Erreur lors de la compilation"
    exit 1
fi
print_success "Compilation réussie"

print_info "Exécution des tests..."
mvn test

if [ $? -ne 0 ]; then
    print_error "Erreur lors de l'exécution des tests"
    print_info "Analyse SonarQube lancée malgré les erreurs de tests..."
fi

# Lancement de l'analyse SonarQube
print_info "Lancement de l'analyse SonarQube..."
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.token=squ_f695d7eb5138d051c48bbc3ccaf8591814ab6712

if [ $? -eq 0 ]; then
    print_success "Analyse SonarQube terminée avec succès !"
    print_success "Consultez les résultats sur http://localhost:9000"
    print_info "Projet: cosf-backend"
else
    print_error "Erreur lors de l'analyse SonarQube"
    exit 1
fi

echo "==========================================="
