# SHOP API

Microsserviço simples com dois *endpoints* : um recebe a solicitação de compra para ser processada e o outro retorna todas as solicitações realizadas.

As solicitações de compras que são recebidas por essa API são enviadas, como mensagens, à fila de processamento do Kafka, de onde serão consumidas pelos outros microsserviços.

Essa aplicação faz parte de um conjunto de microsserviços  simples ([shop-api](https://github.com/MarcioNarciso/Spring-Boot-Kafka---Exemplo-Simples-de-Integracao---Shop-API), shop-validator, shop-report e shop-retry) que tem como objetivo a prática e exemplificação da integração do Spring Boot 3 com o Kafka. 
