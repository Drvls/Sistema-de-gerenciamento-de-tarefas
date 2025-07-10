package org.alexvsi;

import org.alexvsi.enums.Prioridade;
import org.alexvsi.enums.StatusTarefa;
import org.alexvsi.model.Tarefa;
import org.alexvsi.dao.TarefaDAO;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main (String[] args){
        iniciarAplicacao();
    }

    public static void iniciarAplicacao(){
        Scanner sc = new Scanner(System.in);
        List<Tarefa> tarefas = new ArrayList<>();
        //TarefaDAO tarefaDAO = new TarefaDAO();
        boolean rodando = true;

        while(rodando) {
            System.out.println("Escolha umas das opções abaixo");
            System.out.println("[1] Adicionar uma nova tarefa");
            System.out.println("[2] Listar tarefas existentes");
            System.out.println("[3] Marcar tarefa como concluída");
            System.out.println("[4] Remover tarefa");
            System.out.println("[5] Estatísticas");
            System.out.println("[6] Sair do programa");

            int opcao = validarEntradaNumero(sc,"Você deve digitar um número válido");
            switch (opcao) {
                case 1:
                    adicionarTarefa(sc, tarefas);
                    break;
                case 2:
                    listarTarefas(tarefas);
                    break;
                case 3:
                    concluirTarefa(sc, tarefas);
                    break;
                case 4:
                    removerTarefa(sc, tarefas);
                    break;
                case 5:
                    mostrarEstatisticas(tarefas);
                    break;
                case 6:
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

    public static void adicionarTarefa(Scanner sc, List<Tarefa> tarefas){
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

    public static void listarTarefas(List<Tarefa> tarefas){
        DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Tarefa> task = TarefaDAO.listarTarefas();

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

    public static void concluirTarefa(Scanner sc, List<Tarefa> tarefas){
        boolean achou = false;
        System.out.println("Informe o ID da tarefa que deseja concluir");
        int id = validarEntradaNumero(sc,"Você deve digitar um ID válido");
        for (Tarefa i : tarefas) {
            if (i.getId() == id) {
                i.concluirTarefa();
                achou = true;
                System.out.println("O status da tarefa mudou para concluido");
            }
        }
        if(!achou){
            System.out.println("O ID informado é inválido");
        }
    }

    public static void removerTarefa(Scanner sc, List<Tarefa> tarefas){
        System.out.println("Informe o ID da tarefa que deseja remover");
        int id = validarEntradaNumero(sc, "Você deve digitar um ID válido");
        TarefaDAO.removerTarefa(id);
    }

    public static void mostrarEstatisticas(List<Tarefa> tarefas){
        int tarefasPendentes = 0;
        int tarefasConcluidas = 0;
        int tarefasAltaPrioridade = 0;

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getStatus().equals(StatusTarefa.PENDENTE)) {
                tarefasPendentes++;
            } else {
                tarefasConcluidas++;
            }
            if (tarefa.getPrioridade().equals(Prioridade.ALTA)) {
                tarefasAltaPrioridade++;
            }
        }

        System.out.println("Quantidade de tarefas: " + tarefas.size());
        System.out.println("Tarefas pendentes: " + tarefasPendentes);
        System.out.println("Tarefas concluidas: " + tarefasConcluidas);
        System.out.println("Tarefas de alta prioridade: " + tarefasAltaPrioridade);
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
}
