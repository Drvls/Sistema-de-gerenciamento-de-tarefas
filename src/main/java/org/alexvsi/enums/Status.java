package org.alexvsi.enums;

public enum Status {
    PENDENTE("Pendente"),
    CONCLUIDO("Concluido");

    private final String descricao;

    Status(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
