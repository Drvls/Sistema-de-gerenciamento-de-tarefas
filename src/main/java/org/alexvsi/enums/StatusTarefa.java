package org.alexvsi.enums;

public enum StatusTarefa {
    PENDENTE("Pendente"),
    CONCLUIDO("Concluído");

    private final String descricao;

    StatusTarefa(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
