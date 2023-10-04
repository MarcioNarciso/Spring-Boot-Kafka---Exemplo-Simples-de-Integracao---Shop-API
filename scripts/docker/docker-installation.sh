#!/bin/bash

# Verifica se o docker já está instalado.

which docker &> /dev/null

# Verifica se o retorno foi diferente de zero
# -ne = not equal
if [ $? -ne 0 ]
then
    echo -e "Instalando Docker..."
    curl -fsSL https://get.docker.com/ | sh
else
    echo -e "Docker já instalado."
fi



