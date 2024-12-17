package com.doctortime.doctortime.Entities.DTO.User;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record UserRequestDTO(@Length(min = 3, message = "Informe uma quantidade acima de 3 caracteres") String name,
                             @NotNull(message = "Endereço deve ser preechido") String address,
                             @CPF(message = "CPF inválido") String cpf,
                             @Email(message = "E-mail inválido") String email,
                             @NotNull String password) {
}
