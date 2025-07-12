# 📝 Sistema de Gerenciamento de Tarefas

> Sistema de gerenciamento de tarefas em cli/terminal.
---

## 📚 Tabela de Conteúdos

- [Descrição](#-descrição)
- [Instalação](#-instalação)
- [Como Usar](#-como-usar)
- [Atualizações Futuras](#-atualizações-futuras)
- [Tecnologias Usadas](#-tecnologias-usadas)
- [Contribuição](#-contribuição)
- [Autor](#-autor)

---

## 🧾 Descrição

Este projeto é um programa feito em Java e banco de dados com MySQL, com o objetivo de servir como um sistema de gerenciamento de tarefas, sendo utilizado via cli/terminal.

---

## 🚀 Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/Drvls/Sistema-de-gerenciamento-de-tarefas-cli.git
   ```

2. Entre na pasta do projeto:
   ```bash
   cd Sistema-de-Gerenciamento-de-Tarefas/src/main/java/
   ```

3. Execute o projeto o arquivo Main.java utilizando uma IDE com suporte a Java 21, JDBC e Maven.
---

## 💡 Como Usar

Após executar, siga as instruções no terminal:  

```cli
1. Adicionar tarefa
2. Listar tarefas
3. Editar tarefa
4. Concluir tarefa
5. Remover tarefa
6. Mostrar estatísticas
0. Sair do programa

```
- 1. Para adicionar uma tarefa é solicitado alguns dados como título, descrição, nível de prioridade e data limite para a entrega/feito da tarefa.
- 2. Lista todas as tarefas existentes no banco de dados, mesmo aquelas que foram adicionadas a muito tempo.
- 3. Ao escolher a opção "3- Editar tarefa" será solicitado o ID da tarefa que poderá conferir na listagem (opção 2). Ao inserir um ID válido irá aparecer algumas opções como:

```cli
1. Editar título
2. Editar descrição
3. Editar prioridade
4. Editar data
5. Editar toda a tarefa

0. voltar
````
A opção "0 - Voltar" retorna para o menu principal.

- 4. Será solicitado uma ID válida que irá alterar o status da tarefa de "Pendente" para "Concluído".
- 5. Solicita um ID para remover a tarefa do banco de dados, a mesma possui uma confirmação dupla pois ao remover não será possível recuperar.
- 6. Mostra as estatísticas das tarefas, tais como:

```cli
Quantidade de tarefas
Tarefas pendentes
Tarefas concluidas
Tarefas de alta prioridade
```

---

## ⏳ Atualizações futuras

- ✅ Corrigir saída de data limite ao listar
- ✅ Adicionar validações para evitar erros de entrada e execução.
- ✅ Data mínima para tarefa (verificar com a data atual)
- ✅ Editar e excluir tarefas
- ✅ Armazenamento de tarefas em banco de dados SQL
- ✅ Banco de dados auto criado após a execução do programa
- 🚧 Uso do projeto no cmd/terminal
- ❌ Importação e exportação de tarefas via json e txt
- ❌ Salvar caminho de exportação
- ❌ Verificar arquivo existente com o mesmo nome
---

## 🛠 Tecnologias Usadas

- Java 21 LTS ☕ 
- MySQL 8.0 🐬
- Intellij
- Git

---

## 🤝 Contribuição

Até o momento não aceitarei contribuições por ser um projeto de autodesenvolvimento e aprendizado, mas futuramente espero melhorar o projeto e transformar em uma API, aceitando contribuição de todos que possuem interesse em ajudar a melhorar.

---

## 👤 Autor

- Nome: Alexsandro Vinicius
- LinkedIn: [https://www.linkedin.com/in/sam-vinicius/](https://www.linkedin.com/in/sam-vinicius/)
- GitHub: [https://github.com/Drvls](https://github.com/Drvls)