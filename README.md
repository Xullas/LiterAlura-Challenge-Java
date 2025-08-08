# Challenge LiterAlura 📚

![Status do Projeto](https://img.shields.io/badge/Status-Concluído-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13-blue)
![Docker](https://img.shields.io/badge/Docker-gray?logo=docker)

## 📖 Índice

- [1. Descrição do Projeto](#-1-descrição-do-projeto)
- [2. Funcionalidades](#-2-funcionalidades)
- [3. Tecnologias Utilizadas](#-3-tecnologias-utilizadas)
- [4. Decisões de Arquitetura e Design](#-4-decisões-de-arquitetura-e-design)
    - [4.1. Uso de `JdbcTemplate` em vez de Spring Data JPA](#-41-uso-de-jdbctemplate-em-vez-de-spring-data-jpa)
    - [4.2. Ambiente de Desenvolvimento com Docker](#-42-ambiente-de-desenvolvimento-com-docker)
- [5. Acesso ao Projeto](#-5-acesso-ao-projeto)
- [6. Abrindo e Executando o Projeto](#-6-abrindo-e-executando-o-projeto)
- [7. Estrutura do Projeto](#-7-estrutura-do-projeto)
- [8. Contribuição](#-8-contribuição)

## 📌 1. Descrição do Projeto

O **LiterAlura** é uma aplicação desenvolvida como parte do primeiro Challenge proposto na trilha **Java e Spring Framework G8** do **ONE - Oracle Next Education** em parceria com a **Alura**. O objetivo é construir uma aplicação de console que consome a API pública [Gutendex](https://gutendex.com/) para buscar informações sobre livros e autores, permitindo ao usuário interagir com esses dados e persisti-los em um banco de dados PostgreSQL.

A aplicação permite buscar livros por título, listar todos os livros e autores já registrados, consultar autores vivos em um determinado ano e listar livros por idioma.

## 🎯 2. Funcionalidades

A aplicação oferece um menu interativo com as seguintes opções:

* ✅ **1. Buscar livro pelo título:** Realiza uma busca na API Gutendex, exibe os dados do primeiro livro encontrado e o armazena no banco de dados para consultas futuras.
* ✅ **2. Listar livros registrados:** Exibe todos os livros que foram salvos no banco de dados.
* ✅ **3. Listar autores registrados:** Mostra todos os autores salvos, junto com seus dados (ano de nascimento e falecimento) e a lista de livros de sua autoria registrados no sistema.
* ✅ **4. Listar autores vivos em um determinado ano:** Solicita um ano ao usuário e exibe uma lista de autores que estavam vivos nesse período.
* ✅ **5. Listar livros em um determinado idioma:** Permite ao usuário escolher um idioma e exibe todos os livros registrados nesse idioma.

## 🚀 3. Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework:** Spring Boot 3
- **IDE:** IntelliJ IDEA 
- **Persistência:** Spring JDBC (`JdbcTemplate`)
- **Banco de Dados:** PostgreSQL
- **Migrações:** Flyway
- **Ambiente:** Docker e Docker Compose
- **Build Tool:** Maven
- **Utilitários:** Lombok, Jackson Databind

## 💡 4. Decisões de Arquitetura e Design

Este projeto foi uma oportunidade para aprofundar conhecimentos em tecnologias fundamentais do ecossistema Java. Por isso, algumas decisões foram tomadas para maximizar o aprendizado.

### 💾 4.1. Uso de `JdbcTemplate` em vez de Spring Data JPA

Embora Spring Data JPA seja a abordagem mais comum para persistência em aplicações Spring, optei conscientemente por utilizar **`JdbcTemplate`**. A motivação para essa escolha foi:

- **Aprofundar o conhecimento em SQL:** Escrever as consultas SQL manualmente me permitiu praticar e reforçar conceitos de modelagem de dados, chaves primárias compostas, chaves estrangeiras e, principalmente, utilizar recursos específicos do PostgreSQL.
- **Consultas Otimizadas:** Pude criar consultas nativas e performáticas, como o uso das funções `json_agg` e `json_build_object` do PostgreSQL. Essa abordagem evita o clássico problema de "N+1 selects" ao buscar entidades relacionadas, agregando os dados diretamente no banco e retornando um JSON, que é então desserializado pela aplicação.
- **Controle Total sobre a Persistência:** O uso de `JdbcTemplate` oferece um controle granular sobre cada operação de banco de dados, desde a inserção com `ON CONFLICT DO NOTHING` (UPSERT) até a estruturação exata dos dados retornados.

### 🐳 4.2. Ambiente de Desenvolvimento com Docker

Diferente da recomendação do curso de instalar o PostgreSQL diretamente na máquina local, escolhi utilizar **Docker e Docker Compose** para gerenciar o ambiente de banco de dados. Os benefícios dessa abordagem são:

- **Portabilidade e Consistência:** Qualquer desenvolvedor pode clonar o projeto e subir o ambiente com um único comando (`docker-compose up`), garantindo que todos usem a mesma versão e configuração do banco de dados, eliminando o "funciona na minha máquina".
- **Isolamento:** O banco de dados roda em um contêiner isolado, sem interferir com outros serviços ou instalações na máquina host.
- **Facilidade de Setup:** A configuração do banco, incluindo usuário, senha e banco de dados inicial, é definida no arquivo `compose.yaml`, tornando o processo de inicialização rápido e automatizado.

## 🔑 5. Acesso ao Projeto

**Pré-requisitos:**

-   Java Development Kit (JDK) 17 ou superior
-   Maven 3.8 ou superior
-   Docker e Docker Compose

## ▶️ 6. Abrindo e Executando o Projeto

Siga os passos abaixo para executar a aplicação:

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/Xullas/LiterAlura-Challenge-Java.git
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd ChallengeLiterAlura
    ```

3.  **Inicie o banco de dados com Docker Compose:**
    Este comando irá baixar a imagem do PostgreSQL (se não existir localmente) e iniciar o contêiner em segundo plano (`-d`).
    ```bash
    docker-compose up -d
    ```

4.  **Execute a aplicação Spring Boot:**
    Utilize o Maven Wrapper para compilar e executar o projeto. O Spring Boot irá se conectar automaticamente ao banco de dados rodando no Docker.
    ```bash
    ./mvnw spring-boot:run
    ```

Após a inicialização, o menu interativo será exibido no seu terminal.

## 📁 7. Estrutura do Projeto
A estrutura de pacotes do projeto foi organizada para separar as responsabilidades de forma clara:

* `src/main/java/br/com/alura/ChallengeLiterAlura/`
    * `ChallengeLiterAluraApplication.java`: Classe principal que inicia a aplicação Spring Boot.
    * **domain**: Contém as classes de modelo que representam as entidades da aplicação (ex: `Livro`, `Autor`).
    * **principal**: Responsável pela interface com o usuário no console, exibindo o menu e processando as entradas.
    * **repositories**: Camada de acesso a dados (Data Access Layer), responsável pela comunicação com o banco de dados.
    * **service**: Camada de regras de negócio, que orquestra as operações entre a interface e os repositórios, além de consumir a API externa.
* `src/main/resources/`
    * `application.properties`: Arquivo principal de configuração do Spring, incluindo as configurações do Flyway.
    * **db/migration**: Contém os scripts SQL para versionamento do banco de dados com o Flyway.
  
## 🏆 8. Contribuição
Se quiser sugerir melhorias ou relatar bugs, fique à vontade para abrir uma **issue** ou fazer um **pull request**.<br>
Toda contribuição é bem-vinda! 🚀
---

Feito com ❤️ por [Xullas](https://github.com/Xullas) 😊