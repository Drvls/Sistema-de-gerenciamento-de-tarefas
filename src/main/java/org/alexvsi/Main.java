package org.alexvsi;

import org.alexvsi.enums.Coluna;
import org.alexvsi.enums.Prioridade;
import org.alexvsi.model.Tarefa;
import org.alexvsi.dao.TarefaDAO;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main (String[] args){
        iniciarAplicacao();
    }

    public static void iniciarAplicacao(){
        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while(rodando) {
            System.out.println("\nEscolha umas das opções abaixo");
            System.out.println("[1] Adicionar uma nova tarefa");
            System.out.println("[2] Listar tarefas existentes");
            System.out.println("[3] Editar tarefa existente");
            System.out.println("[4] Marcar tarefa como concluída");
            System.out.println("[5] Remover tarefa");
            System.out.println("[6] Estatísticas");
            System.out.println("[0] Sair do programa");

            int opcao = validarEntradaNumero(sc,"Você deve digitar um número válido");
            switch (opcao) {
                case 1:
                    adicionarTarefa(sc);
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    editarTarefa(sc);
                    break;
                case 4:
                    concluirTarefa(sc);
                    break;
                case 5:
                    removerTarefa(sc);
                    break;
                case 6:
                    mostrarEstatisticas();
                    break;
                case 0:
                    System.out.println("Obrigado por utilizar nosso programa.");
                    rodando = false;
                    break;
                default:
                    System.out.println("Você deve escolher uma opção de 1 a 6");
                    break;
            }
        }
        sc.close();
    }

    public static void adicionarTarefa(Scanner sc){
        DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Informe o título da tarefa");
        String titulo = sc.nextLine();
        System.out.println("Informe a descrição da tarefa");
        String descricao = sc.nextLine();

        Prioridade prioridade = null;
        System.out.println("Informe a prioridade da tarefa");
        System.out.println("[1] Alta prioridade");
        System.out.println("[2] Média prioridade");
        System.out.println("[3] Baixa prioridade");

        int opcaoPrioridade = validarEntradaNumero(sc, "Você deve digitar um número válido");
        switch(opcaoPrioridade){
            case 1:
                prioridade = Prioridade.ALTA;
                break;
            case 2:
                prioridade = Prioridade.MEDIA;
                break;
            case 3:
                prioridade = Prioridade.BAIXA;
                break;
            default:
                System.out.println("Deve escolher entre 1 a 3\n");
                break;
        }

        LocalDate data;
        System.out.println("Informe a data limite da tarefa (ex: 25/06/2025)");

        while(true){
            String dataEntrada = sc.nextLine();
            LocalDate hoje = LocalDate.now();
            if(dataEntrada.matches("\\d{2}/\\d{2}/\\d{4}")) {
                data = LocalDate.parse(dataEntrada, dataFormato);
                if(data.isBefore(hoje)){
                    System.out.println("\nA data deve ser a partir de hoje");
                }
                else{
                    break;
                }
            }
            else{
                System.out.println("\nDeve inserir uma data válida");
            }
        }

        TarefaDAO.adicionarTarefa(new Tarefa(titulo, descricao, prioridade, data));
        System.out.println("\nTarefa adicionada\n");
    }

    public static void listarTarefas(){
        DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Tarefa> task = TarefaDAO.listarTarefas();
        if(!task.isEmpty()){
            for (Tarefa taref : task){
                System.out.println("\nID: " + taref.getId()
                        + "\nTitulo: " + taref.getTitulo()
                        + "\nDescrição: " + taref.getDescricao()
                        + "\nPrioridade: " + taref.getPrioridade()
                        + "\nData limite: " + taref.getDataLimite().format(dataFormato)
                        + "\nStatus: " + taref.getStatus() + "\n"
                );
            }
        }
        else{
            System.out.println("A lista de tarefas está vazia");
        }
    }

    public static void editarTarefa(Scanner sc){
        System.out.println("\nDigite o ID da tarefa que deseja editar");
        int id = validarEntradaNumero(sc, "Deve digitar um número válido");
        if(TarefaDAO.constaNoBD(id)){
            boolean loop = true;
            while(loop){
                System.out.println("\nEscolha uma das opções");
                System.out.println("[1] Editar título");
                System.out.println("[2] Editar descrição");
                System.out.println("[3] Editar prioridade");
                System.out.println("[4] Editar data");
                System.out.println("[5] Editar toda a tarefa");
                System.out.println("\n[0] voltar");

                int opcao = validarEntradaNumero(sc, "Deve digitar um número válido");
                switch (opcao){
                    case 1:
                        editarTitulo(sc, id);
                        break;
                    case 2:
                        editarDescricao(sc, id);
                        break;
                    case 3:
                        editarPrioridade(sc, id);
                        break;
                    case 4:
                        editarData(sc, id);
                        break;
                    case 5:
                        editarTudo(sc, id);
                        break;
                    case 0:
                        loop = false;
                        break;
                    default:
                        System.out.println("Deve escolher uma das opções acima");
                }
            }
        }
        else{
            System.out.println("O ID informado não pertence a nenhuma tarefa registrada");
        }
    }

    public static void concluirTarefa(Scanner sc){
        System.out.println("Informe o ID da tarefa que deseja concluir");
        int id = validarEntradaNumero(sc,"Você deve digitar um ID válido");
        TarefaDAO.concluirTarefa(id);
    }

    public static void removerTarefa(Scanner sc){
        System.out.println("Informe o ID da tarefa que deseja remover");
        int id = validarEntradaNumero(sc, "Você deve digitar um ID válido");
        TarefaDAO.removerTarefa(id);
    }

    public static void mostrarEstatisticas(){
        int[] dados = TarefaDAO.mostrarEstatisticas();
        int quantidadeDeTarefas = dados[0];
        int tarefasConcluidas = dados[1];
        int tarefasPendentes = dados[2];
        int tarefasAltaPrioridade = dados[3];

        System.out.println("\nQuantidade de tarefas: " + quantidadeDeTarefas);
        System.out.println("Tarefas pendentes: " + tarefasPendentes);
        System.out.println("Tarefas concluidas: " + tarefasConcluidas);
        System.out.println("Tarefas de alta prioridade: " + tarefasAltaPrioridade + "\n");
    }

    public static int validarEntradaNumero(Scanner sc, String msg){
        while(true){
            String entradaNumero = sc.nextLine();
            if(entradaNumero.matches("\\d+")){
                return Integer.parseInt(entradaNumero);
            }
            else{
                System.out.println(msg);
            }
        }
    }

    public static void editarTitulo(Scanner sc, int id){
        System.out.println("\nDigite o novo título da tarefa");
        String titulo = sc.nextLine();
        
        TarefaDAO.atualizar(Coluna.TITULO, titulo, id);
        System.out.println("\nTítulo de tarefa atualizado\n");
    }

    public static void editarDescricao(Scanner sc, int id){
        System.out.println("\nDigite a nova descrição da tarefa");
        String descricao = sc.nextLine();

        TarefaDAO.atualizar(Coluna.DESCRICAO, descricao, id);
        System.out.println("\nDescrição de tarefa atualizada\n");
    }

    public static void editarPrioridade(Scanner sc, int id){
        Prioridade prioridade = null;
        System.out.println("\nEscolha a nova prioridade para a tarefa");
        System.out.println("[1] Alta prioridade");
        System.out.println("[2] Média prioridade");
        System.out.println("[3] Baixa prioridade");

        int opcaoPrioridade = validarEntradaNumero(sc, "Você deve digitar um número válido");
        switch(opcaoPrioridade){
            case 1:
                prioridade = Prioridade.ALTA;
                break;
            case 2:
                prioridade = Prioridade.MEDIA;
                break;
            case 3:
                prioridade = Prioridade.BAIXA;
                break;
            default:
                System.out.println("Deve escolher entre 1 a 3\n");
                break;
        }

        assert prioridade != null;
        TarefaDAO.atualizar(Coluna.PRIORIDADE, prioridade, id);
        System.out.println("\nPrioridade de tarefa atualizada\n");
    }

    public static void editarData(Scanner sc, int id) {
        LocalDate data;
        DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Informe a data limite da tarefa (ex: 25/06/2025)");

        while(true){
            String dataEntrada = sc.nextLine();
            LocalDate hoje = LocalDate.now();
            if(dataEntrada.matches("\\d{2}/\\d{2}/\\d{4}")) {
                data = LocalDate.parse(dataEntrada, dataFormato);
                if(data.isBefore(hoje)){
                    System.out.println("\nA data deve ser a partir de hoje");
                }
                else{
                    break;
                }
            }
            else{
                System.out.println("\nDeve inserir uma data válida");
            }
        }

        TarefaDAO.atualizar(Coluna.DATA, data, id);
        System.out.println("\nData de tarefa atualizada\n");
    }

    private static void editarTudo(Scanner sc, int id){
        editarTitulo(sc, id);
        editarDescricao(sc, id);
        editarPrioridade(sc, id);
        editarData(sc, id);
    }
}
