# 📝 Gerenciador de Tarefas (Java + PostgreSQL)

Um sistema simples e eficiente para gerenciamento de tarefas (To-Do List), desenvolvido para praticar a integração entre Java e Banco de Dados Relacional utilizando JDBC padrão.

## 🚀 Sobre o Projeto

Este projeto consiste em uma aplicação backend (ou console) que permite realizar operações CRUD (Criar, Ler, Atualizar e Deletar) em tarefas armazenadas em um banco de dados PostgreSQL. O objetivo principal é demonstrar o uso de **JDBC** puro sem frameworks ORM (como Hibernate), garantindo o entendimento profundo da comunicação com o banco de dados.

## 🛠️ Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias:

* **[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html):** Linguagem de programação principal.
* **[Maven](https://maven.apache.org/):** Gerenciador de dependências e build.
* **[JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/):** API padrão para conexão com banco de dados.
* **[PostgreSQL](https://www.postgresql.org/):** Sistema gerenciador de banco de dados relacional.

## ⚙️ Funcionalidades

- [x] **Criar Tarefa:** Adicionar novas tarefas com título, descrição e status.
- [x] **Listar Tarefas:** Visualizar todas as tarefas cadastradas.
- [x] **Atualizar Tarefa:** Editar detalhes de uma tarefa ou marcar como concluída.
- [x] **Deletar Tarefa:** Remover tarefas do banco de dados.

## 🗄️ Configuração do Banco de Dados

Antes de executar a aplicação, é necessário criar o banco de dados e a tabela. Execute os comandos abaixo no seu cliente PostgreSQL (pgAdmin, DBeaver ou terminal):

```sql
create table if not exists tarefas (
	id SERIAL primary key,
	descricao VARCHAR(255) not null,
	concluida BOOLEAN default false
);
