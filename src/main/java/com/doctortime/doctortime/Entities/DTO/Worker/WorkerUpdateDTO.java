package com.doctortime.doctortime.Entities.DTO.Worker;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record WorkerUpdateDTO(
        String name,
        String email,
        String setor,
        String password) {
}
