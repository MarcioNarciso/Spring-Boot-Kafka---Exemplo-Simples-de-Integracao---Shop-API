#!/bin/bash

# Compila a aplicação Spring Boot para ser colocada dentro do Docker

echo "Compilando a aplicação..."
./mvnw clean package
echo -e "${VERDE}Aplicação compilada."

