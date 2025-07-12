package org.alexvsi.model;

import org.alexvsi.enums.Prioridade;
import org.alexvsi.enums.StatusTarefa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDate dataLimite;
    private StatusTarefa statusTarefa;

    // Constructor app > db
    public Tarefa(String titulo, String descricao, Prioridade prioridade, LocalDate dataLimite) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.statusTarefa = StatusTarefa.PENDENTE;
    }

    // Constructor db > app
    public Tarefa(int tarefaId, String titulo, String descricao, String prioridade, LocalDate data, String status){
        this.id = tarefaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = Prioridade.valueOf(prioridade);
        this.dataLimite = data;
        this.statusTarefa = status.equals("Pendente") ? StatusTarefa.PENDENTE : StatusTarefa.CONCLUIDO;
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

    public StatusTarefa getStatus(){
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
