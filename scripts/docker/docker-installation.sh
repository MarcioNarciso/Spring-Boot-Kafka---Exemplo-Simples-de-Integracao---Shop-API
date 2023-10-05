#!/bin/bash

# Verifica se o docker já está instalado.

which docker &> /dev/null

# Verifica se o retorno foi diferente de zero
# -ne = not equal; -eq = equal
if [ $? -eq 0 ]
then
    echo -e "${AZUL}Docker já instalado."
    return 0
fi

echo "Instalando Docker..."
#curl -fsSL https://get.docker.com/ | sh
sudo dnf update -y
sudo dnf install docker -y

# Verificando a instalação...
which docker &> /dev/null

if [ $? -ne 0 ]
then
    echo -e "${VERMELHO}Erro na instalação do Docker!"
    exit 1
fi

#Inicializando o serviço do docker
sudo systemctl start docker

# Habilitando o serviço do Docker para ser inicializado no boot
sudo systemctl enable docker

# Adicionando o usuário ao grupo do Docker. 
# Para que não seja necessário o uso do "sudo"
sudo adduser ec2-user docker

echo -e "${VERDE}Instalação do Docker concluída."




