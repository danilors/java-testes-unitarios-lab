## java-testes-unitarios-lab

Este repositório reúne múltiplos projetos com o objetivo de explorar e experimentar diferentes features do Java, com um foco especial em testes unitários. A proposta é identificar as melhores abordagens para cenários variados de testes, como APIs REST, conexões com bancos de dados via JPA ou JDBC, entre outros.

### Projetos

- **api-pedidos**  
  Uma API REST que simula um sistema de delivery. O projeto inclui as seguintes entidades: Cliente, Carrinho, Produto e Pedido, permitindo a realização de operações relacionadas ao fluxo de pedidos.

- **api-produtos**  
  Uma API REST simples que implementa operações CRUD para a entidade Produto. O foco está na simplicidade e na cobertura de testes unitários para operações básicas.

- **classes-simples**  
  Um projeto Maven minimalista em Java, utilizando apenas JUnit e Mockito como dependências. O objetivo é explorar testes unitários em Java Beans sem o uso de frameworks como Spring ou outras bibliotecas adicionais.

- **api-integrations-test**  
  Uma API REST desenvolvida com Spring Boot e banco de dados H2. Este projeto tem como foco a realização de testes integrados, cobrindo todo o fluxo de cada endpoint. Os testes validam tanto o código de status HTTP quanto os valores do corpo da resposta em cada requisição.

- **api-bdd-tests**  
  Um projeto que demonstra a utilização do **Cucumber** para testes BDD (Behavior-Driven Development) em um ambiente Spring Boot. Ele integra cenários descritos em linguagem natural com testes automatizados, utilizando ferramentas como JUnit e Mockito.
