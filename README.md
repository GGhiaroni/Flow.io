# Flow.io | AI-Powered Study Roadmap Generator

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Google Gemini](https://img.shields.io/badge/Google_Gemini-8E75B2?style=for-the-badge&logo=google-bard&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2_Database-4A154B?style=for-the-badge&logo=h2&logoColor=white)

## Sobre o Projeto

O **Flow.io** é uma API RESTful inteligente projetada para gerar planos de estudo altamente personalizados e dinâmicos. A aplicação coleta os tópicos de interesse do usuário (como categorias e linguagens), processa esses dados e se comunica de forma assíncrona com a inteligência artificial do Google (Gemini 2.5 Flash) para construir roadmaps detalhados, indo do nível básico ao domínio técnico especializado.

Este projeto foi construído com foco em **clean code**, arquitetura em camadas e comunicação com API externa.

## Arquitetura e Decisões Técnicas

* **Integração Assíncrona com IA (Spring WebFlux / WebClient):** Em vez do tradicional e bloqueante `RestTemplate`, a aplicação utiliza o `WebClient` para realizar chamadas não-bloqueantes à API do Google Gemini. Isso garante que a thread principal da aplicação não fique travada enquanto aguarda o processamento da IA, escalando melhor sob alta demanda.
* **Engenharia de Prompt Dinâmica:** O payload enviado para a inteligência artificial não é estático. A aplicação utiliza a API de `Streams` do Java para iterar sobre as entidades do banco de dados (Tópicos e Categorias) e construir instruções de contexto sob medida em tempo de execução.
* **Migrations com Flyway:** versionamento do banco de dados rigorosamente controlado pelo Flyway.
* **Padrão DTO (Data Transfer Object):** implementação de DTOs e Mappers para isolar o modelo de domínio (Entities) da camada de apresentação, evitando vazamento de dados sensíveis e melhorando a segurança da API.

## Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** H2 Database (In-memory, focado em desenvolvimento e testes ágeis)
* **Gerenciamento de Banco:** Flyway Migrations
* **Comunicação de Rede:** WebClient (Reactor Core)
* **Inteligência Artificial:** Google Gemini API (`gemini-2.5-flash`)
* **Parsing de Dados:** Jackson (ObjectMapper / JsonNode)

## Como executar o projeto localmente

### Pré-requisitos
* Java JDK 17 ou superior.
* Maven.
* Uma chave de API válida do [Google AI Studio](https://aistudio.google.com/app/apikey).

### Passo a Passo

1. **Clone o repositório:**
```bash
git clone [https://github.com/SeuUsuario/Flow.io.git](https://github.com/SeuUsuario/Flow.io.git)
cd Flow.io
```

2. **Configure a Variável de Ambiente:**
Crie um arquivo `.env` na raiz do projeto e adicione a sua chave de API do Google Gemini:
```env
API_KEY=sua_chave_secreta_aqui
```
*Nota: O arquivo `.env` está mapeado no `.gitignore` por razões de segurança.*

3. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

4. **Teste a API:**
A aplicação estará rodando na porta `8080`.
* **Cadastre tópicos** enviando um POST com a entidade esperada para a rota de criação.
* **Gere o Roadmap:** Faça uma requisição GET para `http://localhost:8080/generate`. O tempo de resposta pode variar (em média 15-20 segundos) devido ao processamento profundo da IA.

