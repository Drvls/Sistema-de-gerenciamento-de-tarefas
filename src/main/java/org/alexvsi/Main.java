package org.alexvsi;

import org.alexvsi.Model.Prioridade;
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
            System.out.println("\nEscolha umas das opções abaixo");
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
                    System.out.println("\nObrigado por utilizar nosso programa.");
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

        Prioridade prioridade = null;
        System.out.println("\nInforme a prioridade da tarefa");
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
            if(dataEntrada.matches("\\d{2}/\\d{2}/\\d{4}/}")){
                data = LocalDate.parse(dataEntrada, dataFormato);
                break;
            }
            else{
                System.out.println("Deve inserir uma data válida");
            }
        }

        tarefas.add(new Tarefa(titulo, descricao, prioridade, data));
        System.out.println("\nTarefa adicionada\n");
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
        boolean achou = false;

        System.out.println("Informe o ID da tarefa que deseja remover");
        int id = validarEntradaNumero(sc, "Você deve digitar um ID válido");

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
