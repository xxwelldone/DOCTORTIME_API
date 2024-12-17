package com.doctortime.doctortime.Entities.enums;

public enum Status {
    AGENDADO("Agendado"),
    CANCELADO("Cancelado"),
    Atendido("Atendido"),
    ;
    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
