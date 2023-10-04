#!/bin/bash

# Compila o Dockerfile para criar a imagem

echo "Compilando Dockerfile..."
docker buildx build -t shop-api:1.0  $(dirname "$0")
echo "Imagem shop-api:1.0 criada."
