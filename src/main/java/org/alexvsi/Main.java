package org.alexvsi;

import org.alexvsi.controller.TarefaController;
import org.alexvsi.db.DbSetup;
import org.alexvsi.enums.Prioridade;
import org.alexvsi.view.TarefaView;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        DbSetup.criarDb();
        DbSetup.criarTb();
        iniciarAplicacao();
    }

    public static void iniciarAplicacao(){
        Scanner sc = new Scanner(System.in);
        TarefaController controller = new TarefaController();
        TarefaView view = new TarefaView();

        int opcao;
        do{
            view.mostrarMenu();
            opcao = controller.validarEntradaNumero(sc.nextLine());

            switch (opcao) {
                case 1: // Adicionar tarefa
                    String titulo = view.solicitarTitulo();
                    String descricao = view.solicitarDescricao();
                    Prioridade prioridade = controller.validarPrioridade();
                    LocalDate data = controller.validarData();

                    controller.adicionarTarefa(titulo, descricao, prioridade, data);
                    break;
                case 2: // Listar tarefas
                    controller.listarTarefas();
                    break;
                case 3: // Editar tarefa existente
                    int opcaoEdit;

                    do{
                        int id = -1;
                        do {
                            id = controller.validarEntradaNumero(view.solicitarId());
                        }
                        while(id <= -1);

                        opcaoEdit = controller.editarTarefa(id);
                        switch(opcaoEdit){
                            case 1: // Titulo
                                controller.editarTitulo(id);
                                break;
                            case 2: // Descrição
                                controller.editarDescricao(id);
                                break;
                            case 3: // Prioridade
                                controller.editarPrioridade(id);
                                break;
                            case 4: // Data
                                controller.editarData(id);
                                break;
                            case 5: // EditarTudo
                                controller.editarTudo(id);
                                break;
                            case 0: // Saida
                                break;
                            default:
                                view.mostrarMensagem("Deve escolher uma das opções acima");
                                break;
                        }
                    }
                    while(opcaoEdit != 0);
                    break;
                case 4: // Marcar tarefa como concluida
                    controller.concluirTarefa();
                    break;
                case 5: // Remover tarefa
                    controller.removerTarefa();
                    break;
                case 6: // Estatisticas
                    controller.mostrarEstatisticas();
                    break;
                case 0: // Saida
                    System.out.println("Obrigado por utilizar nosso programa.");
                    break;
                default:
                    System.out.println("\nVocê deve escolher uma opção de 1 a 6");
                    break;
            }
        }
        while(opcao != 0);
        sc.close();
    }
}
