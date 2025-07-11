package org.alexvsi.enums;

public enum Prioridade {
    ALTA("Alta"),
    MEDIA("Média"),
    BAIXA("Baixa");

    private final String descricao;

    Prioridade(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
