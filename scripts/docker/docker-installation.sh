#!/bin/bash

# Verifica se o docker já está instalado.

which docker &> /dev/null

# Verifica se o retorno foi diferente de zero
# -ne = not equal; -eq = equal
if [ $? -eq 0 ]
then
    echo -e "${AZUL}Docker já instalado.${BRANCO}"
    return 0
fi

echo "Baixando Docker..."
wget -cq https://download.docker.com/linux/fedora/39/x86_64/stable/Packages/containerd.io-1.6.24-3.1.fc39.x86_64.rpm -O ~/Downloads/docker.rpm

if [ $? -eq 1 ]
then
    echo -e "${VERMELHO}Erro no download do Docker!${BRANCO}"
    exit 1
fi

echo -e "${VERDE}Download concluído.${BRANCO}"

echo "Instalando Docker..."
sudo dnf -y install $PATH_DOCKER_PACKAGE

# Verificando a instalação...
which docker &> /dev/null

if [ $? -ne 0 ]
then
    echo -e "${VERMELHO}Erro na instalação do Docker!${BRANCO}"
    exit 1
fi

#Inicializando o serviço do docker
sudo systemctl start docker

# Habilitando o serviço do Docker para ser inicializado no boot
sudo systemctl enable docker

# Adicionando o usuário ao grupo do Docker. 
# Para que não seja necessário o uso do "sudo"
sudo adduser ec2-user docker

echo -e "${VERDE}Instalação do Docker concluída.${BRANCO}"




