#!/bin/bash

# Executa o contêiner shop-api:1.0 do Docker
echo "Executando o contêiner shop-api:1.0..."
sudo docker container run -p 8080:8080 --name shop-api_1.0 shop-api:1.0
# echo "Contêiner rodando."
