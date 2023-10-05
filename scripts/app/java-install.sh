#!/bin/bash

# Script de instalação do OpenJDK 20

which java &> /dev/null

if [ $? -eq 0 ]
then
    echo -e "${AZUL}OpenJDK já está instalado!!!${BRANCO}"
    return 0
fi

BASEDIR=$(readlink -f "$0")
BASEDIR=$(dirname $BASEDIR)

echo "Baixando o OpenJDK 20..."
wget -cq https://download.java.net/java/GA/jdk20.0.2/6e380f22cbe7469fa75fb448bd903d8e/9/GPL/openjdk-20.0.2_linux-x64_bin.tar.gz -O ~/openjdk.tar.gz

if [ $? -ne 0 ]
then
    echo -e "${VERMELHO}Erro no Download do OpenJDK! Não foi possível baixar o OpenJDK.${BRANCO}"
    exit 1
fi

echo -e "${VERDE}Download concluído.${BRANCO}"

# Troca pro diretório em que o arquivo foi baixado
cd ~/

echo "Extraindo o OpenJDK..."
tar xzf openjdk.tar.gz 

if [ $? -ne 0 ]
then
    echo -e "${VERMELHO}Erro na extração do OpenJDK! Não foi possível extraír o OpenJDK.${BRANCO}"
    exit 1
fi

echo -e "${VERDE}Extração terminada.${BRANCO}"

echo "Instalando o OpenJDK..."

# Move o openjdk para seu diretório permanente
sudo mv jdk-20.0.2 /opt/

JAVA_DIR="/opt/jdk-20.0.2/"

if [ ! -d JAVA_DIR ] 
then
    echo -e "${VERMELHO}Erro na instalação do OpenJDK! Não foi possível movê-lo.${BRANCO}"
fi

# Inclui as variáveis de ambiente no shell atual
source /etc/environment

which java &> /dev/null

if [ $? -ne 0 ]
then 
    echo -e "${VERMELHO}Erro na instalação do OpenJDK! Java não instalado.${BRANCO}"
    exit 1
fi

cd $BASEDIR

echo -e "${VERDE}Instalação do OpenJDK concluída.${BRANCO}"
