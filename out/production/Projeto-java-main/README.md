README - Sistema de Reservas para Restaurante
📌 Sobre o Projeto

Este projeto consiste no desenvolvimento de um Sistema de Reservas para Restaurante, capaz de gerenciar reservas de mesas e espaços do estabelecimento, evitando conflitos de horários e organizando as informações dos clientes.

O sistema foi desenvolvido como parte do Projeto Semestral da disciplina de Programação, do curso de Engenharia de Software do Centro Universitário Tiradentes de Pernambuco (UNIT-PE).

🎯 Objetivo

Desenvolver uma aplicação que permita:

Criar reservas de clientes;
Consultar reservas cadastradas;
Cancelar reservas;
Controlar horários disponíveis;
Evitar reservas duplicadas para o mesmo horário;
Organizar informações dos clientes e espaços reservados.
📖 Contexto

O restaurante disponibiliza diferentes espaços para seus clientes, como:

Mesas comuns;
Salas privadas;
Espaços para eventos.

O sistema auxilia o recepcionista no gerenciamento dessas reservas, garantindo melhor organização e atendimento aos clientes.

👥 Atores do Sistema
Cliente

Responsável por solicitar uma reserva.

Recepcionista

Responsável por cadastrar, consultar e cancelar reservas.

Administrador

Responsável por acompanhar a utilização dos espaços e consultar relatórios.

Equipe da Cozinha

Pode consultar informações especiais associadas às reservas.

⚙️ Funcionalidades Implementadas
1. Criar Reserva

Permite cadastrar uma nova reserva informando:

Nome do cliente;
Horário;
Data;
Espaço reservado.

O sistema verifica se já existe uma reserva para o mesmo horário e data antes de efetuar o cadastro.

2. Consultar Reservas

Exibe todas as reservas cadastradas contendo:

Nome do cliente;
Data;
Horário;
Espaço reservado.
3. Cancelar Reserva

Permite remover uma reserva cadastrada através do nome do cliente.

4. Encerrar Sistema

Finaliza a execução do programa.

🏗️ Estrutura do Projeto
Classe Main

Responsável pelo menu principal e interação com o usuário.

Funções:

Criar reservas;
Exibir reservas;
Cancelar reservas;
Encerrar o sistema.
Classe Reserva

Responsável por armazenar os dados de uma reserva.

Atributos
String cliente;
int horario;
int data;
int espaco;
Construtor
public Reserva(String cliente, int horario, int data, int espaco)
Classe Cliente (Planejada)

Responsável por armazenar informações dos clientes.

Atributos
String nome;
int telefone;
Classe Mesa (Planejada)

Responsável por armazenar informações das mesas.

Atributos
int numero;
int capacidade;
String disponibilidade;
🛠️ Tecnologias Utilizadas
Java
Programação Orientada a Objetos (POO)
ArrayList
Scanner
📋 Regras de Negócio
Não é permitido cadastrar duas reservas no mesmo horário e data.
Toda reserva deve possuir um cliente associado.
Apenas reservas existentes podem ser canceladas.
O sistema deve exibir todas as reservas cadastradas quando solicitado.
🚀 Possíveis Melhorias Futuras
Cadastro completo de clientes;
Cadastro de mesas e salas;
Relatórios de ocupação;
Busca de disponibilidade por data;
Controle de capacidade das mesas;
Registro de necessidades especiais (alergias, acessibilidade etc.);
Interface gráfica;
Persistência de dados em banco de dados.
📚 Conceitos Aplicados

Durante o desenvolvimento deste projeto foram utilizados os seguintes conceitos:

Classes e Objetos;
Encapsulamento;
Construtores;
Coleções (ArrayList);
Estruturas de decisão (switch);
Estruturas de repetição (while);
Tratamento de exceções;
Organização de código orientada a objetos.
📄 Autor

Projeto desenvolvido para fins acadêmicos na disciplina de Programação do curso de Engenharia de Software – UNIT Pernambuco.

Tema: Sistema de Reservas para Restaurante.
Semestre: 2026.1
Professor: Dr. David Barrientos. 
