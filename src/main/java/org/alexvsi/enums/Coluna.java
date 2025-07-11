package org.alexvsi.enums;

public enum Coluna {
    TITULO("Titulo"),
    DESCRICAO("Descrição"),
    PRIORIDADE("Prioridade"),
    DATA("DataLimite");

    private final String descricao;

    Coluna(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
