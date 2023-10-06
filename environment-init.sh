#!/bin/bash

# Definindo as cores das mensagens
AZUL='\033[0;34m'
BRANCO='\033[0;37m'
VERMELHO='\033[1;31m'
VERDE='\033[0;32m'

# Cria a pasta de ~/Downloads caso ela não exista
if [ ! -d ~/Downloads ]; then mkdir ~/Downloads; fi;

# Script de preparação do ambiente na AWS

# Realiza a instalação do OpenJDK
. $(dirname $0)/scripts/app/java-install.sh

# Realiza a compilação da aplicação
. $(dirname "$0")/scripts/app/app-build.sh

# Realiza a instalação do Docker
. $(dirname "$0")/scripts/docker/docker-installation.sh

# Realiza a compilação do Dockerifle
. $(dirname "$0")/scripts/docker/docker-build.sh

# Executa o contêiner do Docker
. $(dirname "$0")/scripts/docker/docker-run.sh
