#!/bin/bash

# Script de preparação do ambiente na AWS

# Realiza a compilação da aplicação
. $(dirname "$0")/scripts/app/app-build.sh

# Realiza a instalação do Docker
. $(dirname "$0")/scripts/docker/docker-installation.sh

# Realiza a compilação do Dockerifle
. $(dirname "$0")/scripts/docker/docker-build.sh

# Executa o contêiner do Docker
. $(dirname "$0")/scripts/docker/docker-run.sh
