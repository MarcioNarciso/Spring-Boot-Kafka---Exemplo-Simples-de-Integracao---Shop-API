#!/bin/bash

# Script de instalação do OpenJDK 20

which java &> /dev/null

if [ $? -eq 0 ]
then
    echo "OpenJDK já está instalado!!!"
    return 0
fi

BASEDIR=$(readlink -f "$0")

echo $BASEDIR

exit 1

echo "Baixando o OpenJDK 20..."
wget -cq https://download.java.net/java/GA/jdk20.0.2/6e380f22cbe7469fa75fb448bd903d8e/9/GPL/openjdk-20.0.2_linux-x64_bin.tar.gz -O ~/openjdk.tar.gz

if [ $? -ne 0 ]
then
    echo "Erro no Download do OpenJDK!"
    exit 1
fi

echo "Download concluído."

# Troca pro diretório em que o arquivo foi baixado
cd ~/

echo "Extraindo o OpenJDK..."
tar xzf openjdk.tar.gz 

if [ $? -ne 0 ]
then
    echo "Erro na extração do OpenJDK!"
    exit 1
fi

echo "Extração terminada."

echo "Instalando o OpenJDK..."

# Move o openjdk para seu diretório permanente
sudo mv jdk-20.0.2 /opt/

# Inclui as variáveis de ambiente no shell atual
source /etc/environment

which java &> /dev/null

if [ $? -ne 0 ]
then 
    echo "Erro na instalação do OpenJDK!"
    exit 1
fi

cd $BASEDIR

echo "Instalação do OpenJDK concluída."

pwd
