# Projeto API BDD Tests

Este projeto tem o objetivo de demonstrar a utilização do **Cucumber** para testes BDD (Behavior-Driven Development) em um projeto **Spring Boot**. Ele apresenta diversas formas de uso da ferramenta, integrando-a com o ecossistema Spring para criar testes automatizados baseados em cenários descritos em linguagem natural.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.5**
- **Cucumber 7.14.0**
- **JUnit 5**
- **Maven**

## Estrutura do Projeto

- **`src/test/resources/features`**: Contém os arquivos `.feature` com os cenários de teste descritos em Gherkin.
- **`src/test/java/br/com/api/bdd/steps`**: Contém as classes de step definitions que implementam os passos dos cenários.
- **`src/test/java/br/com/api/bdd/CucumberSpringConfiguration.java`**: Configuração necessária para integrar o Cucumber com o Spring Boot.
- **`src/test/java/br/com/api/bdd/steps/RunCucumberTest.java`**: Classe responsável por executar os testes do Cucumber.

## Como Executar

1. Certifique-se de ter o **Java 21** e o **Maven** instalados.
2. Clone o repositório e navegue até o diretório do projeto.
3. Execute o comando abaixo para rodar os testes:

   ```bash
   mvn test
   ```

## Exemplos de Cenários

Os cenários de teste são escritos em arquivos `.feature` utilizando a linguagem Gherkin. Exemplo:

```gherkin
Feature: Exemplo de Funcionalidade
  Scenario: Exemplo de Cenário
    Given the application is running
    When I send a request
    Then I receive a successful response
```

## Dependências Principais

As dependências do projeto incluem:

- **Cucumber**: Para testes BDD.
- **Spring Boot Starter Test**: Para suporte a testes no Spring Boot.
- **JUnit 5**: Para execução dos testes.
- **Mockito**: Para criação de mocks em testes unitários.

## Objetivo

Este projeto serve como um guia prático para desenvolvedores que desejam aprender ou implementar testes BDD utilizando o Cucumber em projetos Spring Boot. Ele cobre desde a configuração inicial até exemplos de uso em diferentes cenários.