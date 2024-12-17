package com.doctortime.doctortime.Entities.enums;

public enum Specialty {
    CARDIOLOGIA("Cardiologia"),
    DERMATOLOGIA("Dermatologia"),
    GINECOLOGIA("Ginecologia"),
    PEDIATRIA("Pediatria"),
    ORTOPEDIA("Ortopedia"),
    NEUROLOGIA("Neurologia"),
    PSIQUIATRIA("Psiquiatria"),
    UROLOGIA("Urologia"),
    GASTROENTEROLOGIA("Gastroenterologia"),
    ONCOLOGIA("Oncologia"),
    OFTALMOLOGIA("Oftalmologia"),
    OTORRINOLARINGOLOGIA("Otorrinolaringologia"),
    REUMATOLOGIA("Reumatologia"),
    ENDOCRINOLOGIA("Endocrinologia"),
    HEMATOLOGIA("Hematologia");
    private final String description;

    Specialty(String description) {
        this.description = description;
    }

    public String getDescricao() {
        return description;
    }
}
