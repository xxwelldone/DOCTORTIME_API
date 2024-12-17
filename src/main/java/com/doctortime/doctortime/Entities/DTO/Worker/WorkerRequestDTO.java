package com.doctortime.doctortime.Entities.DTO.Worker;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public record WorkerRequestDTO(
        @NotNull
        String name,
        @Email(message = "Dado inválido")
        String email,
        @NotNull
        String setor,
        @NotNull
        String password
) {
}
