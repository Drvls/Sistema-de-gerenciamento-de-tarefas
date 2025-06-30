package org.alexvsi.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private static int proximoId = 1;

    private int id;
    private String titulo;
    private String descricao;
    private String prioridade;
    private LocalDate dataLimite;
    private Boolean status = false;

    public Tarefa(String titulo, String descricao, String prioridade, LocalDate dataLimite) {
        this.id = proximoId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
    }

    public int getId(){
        return id;
    }

    public String getPrioridade(){
        return prioridade;
    }

    public boolean getStatus(){
        return status;
    }

    @Override
    public String toString() {
        DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String msg = status ? "Concluido" : "Pendente";
        return "\nID: " + id
                + "\nTítulo: " + titulo
                + "\nDescrição: " + descricao
                + "\nPrioridade: " + prioridade
                + "\nStatus: " + msg
                + "\nData limite: " + dataLimite.format(dataFormato);
    }

    public void concluirTarefa(){
        status = true;
    }
}
