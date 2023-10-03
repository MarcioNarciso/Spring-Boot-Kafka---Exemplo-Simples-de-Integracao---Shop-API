# SHOP API

Microsserviço simples com dois *endpoints* : um recebe a solicitação de compra para ser processada e o outro retorna todas as solicitações realizadas.

As solicitações de compras que são recebidas por essa API são enviadas, como mensagens, à fila de processamento do Kafka, de onde serão consumidas pelos outros microsserviços.

Essa aplicação faz parte de um conjunto de microsserviços  simples ([shop-api](https://gitlab.com/mndutra/spring-boot-kafka-exemplo-simples-de-integracao-shop-api), [shop-validator](https://gitlab.com/mndutra/spring-boot-kafka-exemplo-simples-de-integracao-shop-validator), [shop-report](https://gitlab.com/mndutra/spring-boot-kafka-exemplo-simples-de-integracao-shop-report) e [shop-retry](https://gitlab.com/mndutra/spring-boot-kafka-exemplo-simples-de-integracao-shop-retry)) que tem como objetivo a prática e exemplificação da integração do Spring Boot 3 com o Kafka. 

Também há uma aplicação simples em Java que exemplifica o gerenciamento programático do Kafka ([kafka-manager](https://gitlab.com/mndutra/java-kafka-exemplo-simples-de-gerenciamento-programatico-do-kafka-kafka-manager))  usando a biblioteca ["kafka-clients"](https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients/3.5.1).
