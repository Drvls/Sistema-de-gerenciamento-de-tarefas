# 📝 Sistema de Gerenciamento de Tarefas (CLI)

> Gerencie suas tarefas direto no terminal com Java + MySQL.
---

## 📌 Sobre o Projeto

Sistema em Java que permite o gerenciamento de tarefas diretamente pelo terminal, com persistência em banco de dados MySQL (criado automaticamente ao rodar o programa). O foco do projeto é o aprendizado de JDBC, SQL, organização de código e boas práticas.

---

## 📚 Tabela de Conteúdos

- [Instalação](#-instalação)
- [Como Usar](#-como-usar)
- [Atualizações Futuras](#-atualizações-futuras)
- [Tecnologias Usadas](#-tecnologias-usadas)
- [Contribuição](#-contribuição)
- [Autor](#-autor)

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

3. Execute o Main.java via sua IDE ou terminal com suporte a Java 21 e Maven.
   O banco de dados e a tabela são criados automaticamente na primeira execução, via JDBC.
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
- Adicionar tarefas: título, descrição, prioridade e data limite.
- Listar tarefas: visualização completa, com status.
- Editar tarefas: alteração individual ou completa por ID.
- Concluir tarefas: muda status de Pendente para Concluído.
- Remover tarefas: deletar por ID com confirmação dupla.
- Estatísticas: quantidade total, pendentes, concluídas, alta prioridade.

---

## ⏳ Atualizações futuras

- ✅ Corrigir saída de datas
- ✅ Validação de entradas
- ✅ Banco de dados com criação automática
- ❌ Importação/exportação JSON e TXT
- ❌ Definir caminho para exportação
- ❌ Verificar duplicidade de arquivo exportado

---

## 🛠 Tecnologias Usadas

- Java 21 LTS ☕
- JDBC 
- MySQL 8.0 🐬
- Intellij IDEA
- Git & GitHub

---

## 🤝 Contribuição

Projeto pessoal voltado para aprendizado.
No futuro, será evoluído para API com endpoints REST e suporte a contribuições externas.

---

## 👤 Autor

Alexsandro Vinicius
