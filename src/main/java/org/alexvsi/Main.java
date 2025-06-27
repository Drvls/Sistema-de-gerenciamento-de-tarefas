package org.alexvsi;

import org.alexvsi.Model.Tarefa;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        List<Tarefa> tarefas = new ArrayList<>();

        boolean rodando = true;

        while(rodando) {
            System.out.println();
            System.out.println("Escolha umas das opções abaixo");
            System.out.println("[1] Adicionar uma nova tarefa");
            System.out.println("[2] Listar tarefas existentes");
            System.out.println("[3] Marcar tarefa como concluída");
            System.out.println("[4] Remover tarefa");
            System.out.println("[5] Estatísticas");
            System.out.println("[6] Sair do programa");

            int opcao = sc.nextInt();
            sc.nextLine();
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
    }

    public static void adicionarTarefa(Scanner sc, List<Tarefa> tarefas){
        DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Informe o título da tarefa");
        String titulo = sc.nextLine();
        System.out.println("Informe a descrição da tarefa");
        String descricao = sc.nextLine();

        boolean valido = true;
        String prioridade = "null";
        do{
            System.out.println("Informe a prioridade da tarefa");
            System.out.println("[1] Alta prioridade");
            System.out.println("[2] Média prioridade");
            System.out.println("[3] Baixa prioridade");
            int opcaoPrioridade = sc.nextInt();
            sc.nextLine();
            switch(opcaoPrioridade){
                case 1:
                    prioridade = "alta";
                    break;
                case 2:
                    prioridade = "media";
                    break;
                case 3:
                    prioridade = "baixa";
                    break;
                default:
                    System.out.println("Deve escolher entre 1 a 3");
                    valido = false;
                    break;
            }
        }
        while(!valido);

        System.out.println("Informe a data limite da tarefa (ex: 25/06/2025)");
        String dataEntrada = sc.nextLine();
        LocalDate data = LocalDate.parse(dataEntrada, dataFormato);

        tarefas.add(new Tarefa(titulo, descricao, prioridade, data));

        System.out.println();
        System.out.println("Tarefa adicionada");

    }

    public static void listarTarefas(List<Tarefa> tarefas){
        if(!tarefas.isEmpty()){
            for(Tarefa i : tarefas){
                System.out.println(i);
            }
        }
        else{
            System.out.println("A lista está vazia");
        }
    }

    public static void concluirTarefa(Scanner sc, List<Tarefa> tarefas){
        boolean achou = false;

        do {
            System.out.println("Informe o ID da tarefa que deseja concluir");
            int id = sc.nextInt();
            sc.nextLine();

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
        while(!achou);
    }

    public static void removerTarefa(Scanner sc, List<Tarefa> tarefas){
        boolean achou = false;

        do {
            System.out.println("Informe o ID da tarefa que deseja remover");
            int id = sc.nextInt();

            for(int i = 0; i < tarefas.size(); i++){
                if(tarefas.get(i).getId() == id){
                    tarefas.remove(i);
                    achou = true;
                    break;
                }
            }
            if(!achou){
                System.out.println("O ID informado é inválido");
            }
        }
        while(!achou);
    }

    public static void mostrarEstatisticas(List<Tarefa> tarefas){
        int tarefasPendentes = 0;
        int tarefasConcluidas = 0;
        int tarefasAltaPrioridade = 0;

        for (Tarefa tarefa : tarefas) {
            if (!tarefa.getStatus()) {
                tarefasPendentes++;
            } else {
                tarefasConcluidas++;
            }
            if (tarefa.getPrioridade().equals("alta")) {
                tarefasAltaPrioridade++;
            }
        }

        System.out.println("Quantidade de tarefas: " + tarefas.size());
        System.out.println("Tarefas pendentes: " + tarefasPendentes);
        System.out.println("Tarefas concluidas: " + tarefasConcluidas);
        System.out.println("Tarefas de alta prioridade: " + tarefasAltaPrioridade);
    }
}
