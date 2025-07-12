package org.alexvsi.model;

import org.alexvsi.enums.Prioridade;
import org.alexvsi.enums.Status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDate dataLimite;
    private Status statusTarefa;

    // Constructor app > db
    public Tarefa(String titulo, String descricao, Prioridade prioridade, LocalDate dataLimite) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.statusTarefa = Status.PENDENTE;
    }

    // Constructor db > app
    public Tarefa(int tarefaId, String titulo, String descricao, String prioridade, LocalDate data, String status){
        this.id = tarefaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = Prioridade.valueOf(prioridade.toUpperCase());
        this.dataLimite = data;
        this.statusTarefa = Status.valueOf(status.toUpperCase());
    }

    public int getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    public Prioridade getPrioridade(){
        return prioridade;
    }

    public LocalDate getDataLimite(){
        return dataLimite;
    }

    public Status getStatus(){
        return statusTarefa;
    }

    @Override
    public String toString() {
        DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "\nID: " + id
                + "\nTítulo: " + titulo
                + "\nDescrição: " + descricao
                + "\nPrioridade: " + prioridade
                + "\nStatus: " + statusTarefa
                + "\nData limite: " + dataLimite.format(dataFormato);
    }
}
