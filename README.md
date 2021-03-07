# Demonstração de aplicação JAVA com JSF

## Sobre

Este app demonstra uma simples implementação de um gerenciador de tarefas utilizando JSF + Hibernate JPA e PostgreSQL

Com ele você conseguirá cadastrar suas tarefas e atribuí-las a responsáveis, consultá-las utilizando filtros de Título/Descrição, Responsável e Situação

Também disponível no [Heroku](https://salty-savannah-75897.herokuapp.com/ "Sample-java-todo on Heroku")

### Packages utilizadas (Maven)
Package | Versão
:--------: | :--------:
jsf-api | 2.1.13
jsf-impl | 2.1.13
javax.servlet-api | 3.0.1
primefaces | 8.0
hibernate-entity-manager | 5.4.28.Final
hibernate-core | 5.4.28.Final
postgresql | 42.2.19 

## Instalação

### Requisitos

- Será necessário possuir um banco de dados PostgreSQL com a tabela "tarefa".

    OBS: Script abaixo para criação de tabela
- Necessário configurar os seguintes parâmetros no arquivo "src/java/com/appjava/util/HibernateUtil.java"
    - > settings.put(Environment.URL, "jdbc:postgresql://localhost/\<nome-do-banco>")
	- > settings.put(Environment.USER, "\<nome-do-usuario>");
	- > settings.put(Environment.PASS, "\<senha-do-usuario>");

### Script SQL para criação da tabela "tarefa"
    CREATE TABLE Tarefa(
    id SERIAL PRIMARY KEY NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(300),
    responsavel VARCHAR(100) NOT NULL,
    prioridade VARCHAR(10) NOT NULL,
    deadline DATE NOT NULL,
    situacao VARCHAR(20) NOT NULL);

### No Eclipse

Realize o download do repositório:

> git clone https://github.com/carlosbda99/sample-java-todo

Faça o import na IDE como "Projeto Maven existente"

Execute o projeto e acesse através do endereço:

> http://localhost:<porta_do_server>/todo/
