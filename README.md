# API de Usuários com Spring Boot e JWT

API REST para gerenciamento de usuários com autenticação e autorização via JWT (Bearer Token), desenvolvida utilizando Java e Spring Boot.

## 🚀 Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (Bearer Token)
- PostgreSQL
- Maven

---

## 📌 Funcionalidades

- Cadastro de usuários
- Listagem de usuários
- Atualização de usuários
- Exclusão de usuários
- Autenticação com login por e-mail
- Geração e validação de token JWT
- Proteção de rotas com Spring Security
- Tratamento de exceções customizadas

---

## 🏗 Arquitetura do Projeto

O projeto está organizado em camadas seguindo boas práticas:

- **Controller** – Responsável pelos endpoints da API
- **Service (Business)** – Regras de negócio
- **Repository** – Acesso ao banco de dados com Spring Data
- **Entity** – Mapeamento das entidades
- **DTO e Converter** – Transferência e transformação de dados
- **Security** – Configuração de autenticação JWT
- **Exceptions** – Tratamento personalizado de erros

---

## 🔐 Autenticação

A autenticação é feita via JWT.

Após realizar login com e-mail e senha, o sistema retorna um token JWT.

---

## 📬 Principais Endpoints

### 🔑 Autenticação
- `POST /auth/login`

### 👤 Usuários
- `GET /usuarios`
- `POST /usuarios`
- `PUT /usuarios/{id}`
- `DELETE /usuarios/{id}`

---

## 🗄 Banco de Dados

O projeto utiliza PostgreSQL para persistência de dados.
## 🎯 Objetivo do Projeto

Projeto desenvolvido para prática de desenvolvimento Back-End com foco em:

- Arquitetura em camadas
- Segurança com JWT
- Persistência com Spring Data
- Boas práticas de organização de código
