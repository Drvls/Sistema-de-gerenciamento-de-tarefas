package org.alexvsi.enums;

public enum Prioridade {
    ALTA("Alta"),
    MEDIA("Media"),
    BAIXA("Baixa");

    private final String descricao;

    Prioridade(String descricao){
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
