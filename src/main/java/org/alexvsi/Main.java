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
        boolean rodando = true;

        while(rodando) {
            int opcao = controller.validarEntradaNumero(view.mostrarMenu());
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
                    boolean loop = true;
                    int id = controller.validarEntradaNumero(view.solicitarId());
                    int opcaoEdit = controller.editarTarefa(id);
                    do{
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
                                loop = false;
                                break;
                            default:
                                view.mostrarMensagem("Deve escolher uma das opções acima");
                                break;
                        }
                    }
                    while(loop);
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
                    rodando = false;
                    break;
                default:
                    System.out.println("Você deve escolher uma opção de 1 a 6");
                    break;
            }
        }
        sc.close();
    }
}
