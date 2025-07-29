package org.alexvsi.view;

import org.alexvsi.model.Tarefa;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TarefaView {
    private final Scanner sc = new Scanner(System.in);
    public final String numeroInvalido = "Você deve digitar um número válido"; // controller ou manter main?

    public void mostrarMensagem(String msg){
        System.out.println(msg);
    }

    public void listarTarefas(List<Tarefa> tarefas){
        for(Tarefa taref : tarefas){
            DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("\nID: " + taref.getId()
                    + "\nTitulo: " + taref.getTitulo()
                    + "\nDescrição: " + taref.getDescricao()
                    + "\nPrioridade: " + taref.getPrioridade()
                    + "\nData limite: " + taref.getDataLimite().format(dataFormato)
                    + "\nStatus: " + taref.getStatus() + "\n"
            );
        }
    }

    public void mostrarMenu(){
        System.out.println("\nEscolha umas das opções abaixo");
        System.out.println("[1] Adicionar uma nova tarefa");
        System.out.println("[2] Listar tarefas existentes");
        System.out.println("[3] Editar tarefa existente");
        System.out.println("[4] Marcar tarefa como concluída");
        System.out.println("[5] Remover tarefa");
        System.out.println("[6] Estatísticas");
        System.out.println("[0] Sair do programa");

    }

    public String mostrarMenuEditar(){
        System.out.println("\nEscolha uma das opções");
        System.out.println("[1] Editar título");
        System.out.println("[2] Editar descrição");
        System.out.println("[3] Editar prioridade");
        System.out.println("[4] Editar data");
        System.out.println("[5] Editar toda a tarefa");
        System.out.println("\n[0] voltar");

        return sc.nextLine();
    }

    public String solicitarTitulo(){
        System.out.println("Informe o título da tarefa");
        return sc.nextLine();
    }

    public String solicitarDescricao(){
        System.out.println("Informe a descrição da tarefa");
        return sc.nextLine();
    }

    public String solicitarPrioridade(){
        System.out.println("Informe a prioridade da tarefa");
        System.out.println("[1] Alta prioridade");
        System.out.println("[2] Média prioridade");
        System.out.println("[3] Baixa prioridade");
        return sc.nextLine();
    }

    public String solicitarData(){
        System.out.println("Informe a data limite da tarefa (ex: 25/06/2025)");
        return sc.nextLine();
    }

    public String solicitarId(){
        System.out.println("\nDigite o ID da tarefa");
        return sc.nextLine();
    }

    public char solicitarConfirmacao(int id){
        System.out.println("Tem certeza que deseja remover a tarefa " + id + "? (s/n)");
        return sc.next().charAt(0);
    }

    public void mostrarEstatisticas(int[] dados){
        System.out.println("\nQuantidade de tarefas: " + dados[0]);
        System.out.println("Tarefas pendentes: " + dados[1]);
        System.out.println("Tarefas concluidas: " + dados[2]);
        System.out.println("Tarefas de alta prioridade: " + dados[3] + "\n");
    }
}
