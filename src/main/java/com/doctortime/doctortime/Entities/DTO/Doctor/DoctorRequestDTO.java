package com.doctortime.doctortime.Entities.DTO.Doctor;

import com.doctortime.doctortime.Entities.enums.Specialty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public record DoctorRequestDTO(
        @NotNull(message = "Nome deve ser preechido") String name,
        @NotNull(message = "Foto deve ser preechida") String photoUrl,
        @NotNull(message = "CRM deve ser preechido") String CRM,
        @NotNull(message = "Especialidade deve ser preechido") Specialty specialty,
        @NotNull(message = "Endereço deve ser preechido") String address,
        @Email(message = "Email inválido")
        String email,
        @NotNull(message = "Senha deve ser preechida") String password
) {
}
