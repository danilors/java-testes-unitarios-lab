# Projeto API Todo Tarefas

Este projeto é uma API REST desenvolvida com **Spring Boot** para gerenciar tarefas. O foco principal está na realização de **testes de integração**, que validam todos os endpoints da aplicação. Os dados são armazenados em um banco de dados em memória (**H2**) durante a execução dos testes.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **H2 Database**
- **JUnit 5**
- **MockMvc**

## Como Rodar o Build e os Testes

1. Certifique-se de ter o **Java 21** e o **Maven** instalados.
2. Clone o repositório e navegue até o diretório do projeto.
3. Para rodar o build e os testes, execute o comando:

   ```bash
   mvn clean install
   ```

### Como Executar a Aplicação

A aplicação estará disponível na seguinte URL:

```bash
mvn spring-boot:run
```

#### A aplicação estará disponível na seguinte URL:

http://localhost:8080/api/tarefas

Testando os Endpoints
Você pode testar os endpoints utilizando ferramentas como Postman ou Insomnia. Abaixo estão alguns exemplos de endpoints disponíveis:

- **GET** /api/tarefas - Lista todas as tarefas.
- **POST** /api/tarefas - Cria uma nova tarefa.
- **PUT** /api/tarefas/{id} - Atualiza uma tarefa existente.
- **DELETE** /api/tarefas/{id} - Remove uma tarefa.

Certifique-se de ajustar o corpo das requisições conforme necessário para cada endpoint.

#### Arquivo de Collection

No diretório **collections**, você encontrará um arquivo .**yml** contendo uma coleção de requisições prontas para serem importadas no Postman ou Insomnia. Esse arquivo facilita o teste dos endpoints da API, permitindo que você valide rapidamente as funcionalidades implementadas. ```
