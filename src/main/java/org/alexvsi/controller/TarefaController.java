package org.alexvsi.controller;

import org.alexvsi.dao.TarefaDAO;
import org.alexvsi.enums.Coluna;
import org.alexvsi.enums.Prioridade;
import org.alexvsi.model.Tarefa;
import org.alexvsi.view.TarefaView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TarefaController {
    private final TarefaView view = new TarefaView();

    public int validarEntradaNumero(String entradaNumero){
        if(entradaNumero.matches("\\d+")){
            return Integer.parseInt(entradaNumero);
        }
        else{
            view.mostrarMensagem(view.numeroInvalido);
            return -1;
        }
    }

    public LocalDate validarData(){
        DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data;
        LocalDate hoje = LocalDate.now();

        do {
            String dataEntrada = view.solicitarData();
            if(dataEntrada.matches("\\d{2}/\\d{2}/\\d{4}")) {
                data = LocalDate.parse(dataEntrada, dataFormato);
                if(data.isAfter(hoje) || data.isEqual(hoje)){
                    return data;
                }
                else{
                    view.mostrarMensagem("\nA data deve ser a partir de hoje");
                }
            }
            else{
                view.mostrarMensagem("\nDeve inserir uma data válida");
            }
        }
        while(true);
    }

    public Prioridade validarPrioridade(){
        int opcao;
        Prioridade prioridade = null;
        do{
            opcao = validarEntradaNumero(view.solicitarPrioridade());
            switch(opcao){
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
                    view.mostrarMensagem("\nDeve escolher entre 1 a 3");
                    break;
            }
        }
        while(prioridade == null);
        return prioridade;
    }

    public void adicionarTarefa(String titulo, String descricao, Prioridade prioridade, LocalDate data){
        TarefaDAO.adicionarTarefa(new Tarefa(titulo, descricao, prioridade, data));
        view.mostrarMensagem("\nTarefa adicionada\n");
    }

    public void listarTarefas(){
        List<Tarefa> task = TarefaDAO.listarTarefas();
        if(!task.isEmpty()){
            view.listarTarefas(task);
        }
        else{
            view.mostrarMensagem("A lista de tarefas está vazia");
        }
    }

    public int editarTarefa(int id){
        if(TarefaDAO.constaNoBD(id)){
            return validarEntradaNumero(view.mostrarMenuEditar());
        }
        else{
            view.mostrarMensagem("Nenhuma tarefa encontrada com ID " + id + "\n");
            return -1;
        }
    }

    public void concluirTarefa(){
        int id = validarEntradaNumero(view.solicitarId());
        if(TarefaDAO.constaNoBD(id)){
            TarefaDAO.concluirTarefa(id);
        }
        else{
            view.mostrarMensagem("Nenhuma tarefa encontrada com ID " + id + "\n");
        }
    }

    public void removerTarefa(){
        int id = validarEntradaNumero(view.solicitarId());

        if(TarefaDAO.constaNoBD(id)){
            char confirmacao = view.solicitarConfirmacao(id);
            if(confirmacao == 's' || confirmacao == 'S'){
                TarefaDAO.removerTarefa(id);
            }
        }
        else{
            view.mostrarMensagem("Nenhuma tarefa encontrada com ID " + id + "\n");
        }
    }

    public void mostrarEstatisticas(){
        view.mostrarEstatisticas(TarefaDAO.mostrarEstatisticas());
    }

    public void editarTitulo(int id){
        String titulo = view.solicitarTitulo();

        TarefaDAO.atualizar(Coluna.TITULO, titulo, id);
        view.mostrarMensagem("\nTítulo de tarefa atualizado\n");
    }

    public void editarDescricao(int id){
        String descricao = view.solicitarDescricao();

        TarefaDAO.atualizar(Coluna.DESCRICAO, descricao, id);
        view.mostrarMensagem("\nDescrição de tarefa atualizada\n");
    }

    public void editarPrioridade(int id){
        Prioridade prioridade = null;
        do{
            int opcaoPrioridade = validarEntradaNumero(view.solicitarPrioridade());
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
        }
        while(prioridade == null);

        TarefaDAO.atualizar(Coluna.PRIORIDADE, prioridade, id);
        view.mostrarMensagem("\nPrioridade de tarefa atualizada\n");
    }

    public void editarData(int id) {
        LocalDate data = validarData();

        TarefaDAO.atualizar(Coluna.DATA, data, id);
        System.out.println("\nData de tarefa atualizada\n");
    }

    public void editarTudo(int id){
        editarTitulo(id);
        editarDescricao(id);
        editarPrioridade(id);
        editarData(id);
    }
}
