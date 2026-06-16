# 🍽️ Sistema de Reservas para Restaurante

## 📖 Sobre o Projeto

Este projeto consiste no desenvolvimento de um **Sistema de Reservas para Restaurante**, capaz de gerenciar reservas de mesas e espaços do estabelecimento, evitando conflitos de horários e organizando as informações dos clientes.

O sistema foi desenvolvido como parte do **Projeto Semestral da disciplina de Programação**, do curso de **Engenharia de Software do Centro Universitário Tiradentes de Pernambuco (UNIT-PE)**.

A aplicação foi implementada em **Java**, utilizando **Programação Orientada a Objetos (POO)** e integração com banco de dados **MySQL**, permitindo o armazenamento persistente das reservas e dos espaços disponíveis no restaurante.

---

# 🎯 Objetivo

O principal objetivo do sistema é automatizar o processo de gerenciamento de reservas de um restaurante, garantindo:

* Controle eficiente dos espaços disponíveis;
* Organização das reservas realizadas pelos clientes;
* Evitar conflitos de horários;
* Controle da capacidade de mesas e salas;
* Registro de requerimentos especiais;
* Diferentes níveis de acesso para cada tipo de usuário.

---

# 👥 Atores do Sistema

O sistema foi desenvolvido considerando quatro tipos de usuários:

## Cliente
## Recepcionista
## Administrador
## Equipe da Cozinha

- Usuários diferentes de clientes são considerados funcionários e é solicitada uma senha para a realização do login. Esta sendo _123_
Apenas para fins mais interativos.

---

# ⚙️ Funcionalidades Implementadas

## Gestão de Reservas

O sistema permite:

* Criar reservas;
* Alterar reservas existentes;
* Cancelar reservas;
* Consultar reservas cadastradas.

---

## Controle de Conflitos

Para evitar problemas de agendamento, o sistema verifica automaticamente:

* Data da reserva;
* Horário da reserva;
* Espaço reservado.

Cada reserva possui uma duração padrão de:

**4 horas**

Caso exista outra reserva no mesmo espaço durante esse período, o sistema impede o cadastro.

**Exemplo:**

Reserva existente:

Data: 25/06/2026, Horário: 19:00 às 23:00

Nova solicitação:

Data: 25/06/2026, Horário: 20:00

**Resultado:**

Reserva negada por conflito de horário.

---

## Controle de Capacidade

Cada mesa ou sala possui uma capacidade máxima cadastrada.

Caso a quantidade de pessoas informada ultrapasse a capacidade do espaço, a reserva não é permitida.

---

## Atribuição Automática de Espaços

O sistema realiza a atribuição automática do espaço mais adequado para a reserva.

Critérios:

* Mesmo tipo de espaço solicitado;
* Capacidade suficiente;
* Menor capacidade possível para evitar desperdício de lugares.

**Exemplo:**

Reserva para 5 pessoas.

**Espaços disponíveis:**

Mesa 01 → 2 lugares

Mesa 02 → 4 lugares

Mesa 03 → 6 lugares

Mesa 04 → 8 lugares

**Resultado:**

*Mesa 03 atribuída automaticamente.*

---

## Requerimentos Especiais

Durante a criação da reserva, é possível registrar observações especiais, como:

* Alergias alimentares;
* Necessidades de acessibilidade;
* Decorações especiais;
* Cardápios personalizados;
* Restrições alimentares.

Essas informações ficam disponíveis para a equipe da cozinha.

---

## Relatórios

O sistema permite gerar relatórios por período.

Exemplo:

Data Inicial: 01/06/2026

Data Final: 30/06/2026

Resultado:

* Quantidade total de reservas;
* Dados armazenados no banco de dados;
* Histórico de ocupação do restaurante.

---

# 🗄️ Banco de Dados

O projeto utiliza o banco de dados **MySQL**.

## Tabela: espacos

Responsável por armazenar as mesas e salas disponíveis.

| Campo      | Tipo    |
| ---------- | ------- |
| id         | INT     |
| nome       | VARCHAR |
| tipo       | VARCHAR |
| capacidade | INT     |

---

## Tabela: reservas

Responsável por armazenar as reservas realizadas.

| Campo              | Tipo    |
| ------------------ | ------- |
| id                 | INT     |
| cliente            | VARCHAR |
| quantidade_pessoas | INT     |
| tipo_espaco        | VARCHAR |
| espaco_id          | INT     |
| data_reserva       | DATE    |
| horario_inicio     | TIME    |
| horario_fim        | TIME    |
| requerimentos      | TEXT    |

---

# 💻 Tecnologias Utilizadas

* Java
* MySQL
* JDBC
* IntelliJ IDEA
* Programação Orientada a Objetos (POO)

---

# 🚀 Como Executar

### 1. Criar o banco de dados

```sql
CREATE DATABASE restaurante;
```

### 2. Executar o script das tabelas

Criar as tabelas:

* espacos
* reservas

---

### 3. Configurar a conexão

No arquivo:

```java
Conexao.java
```

Informar:

```java
URL do banco
Usuário
Senha
```

---

### 4. Executar o projeto

Abrir o projeto na IDE e executar:

```java
Main.java
```

---

# 📚 Conceitos Aplicados

Durante o desenvolvimento foram utilizados conceitos importantes da Engenharia de Software e Programação:

* Programação Orientada a Objetos;
* Encapsulamento;
* Classes e Objetos;
* Persistência de Dados;
* JDBC;
* Tratamento de Exceções;
* Estruturas Condicionais;
* Estruturas de Repetição;
* Banco de Dados Relacional;
* CRUD (Create, Read, Update e Delete).

---

# 📌 Considerações Finais

O Sistema de Reservas para Restaurante foi desenvolvido com o objetivo de simular um ambiente real de gerenciamento de reservas, permitindo o controle eficiente de mesas e salas, a prevenção de conflitos de horários e o armazenamento permanente das informações em banco de dados.

O projeto demonstra a aplicação prática dos conceitos estudados ao longo da disciplina, integrando programação orientada a objetos, persistência de dados e modelagem de sistemas em uma solução funcional e escalável.
