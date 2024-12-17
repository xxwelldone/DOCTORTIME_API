package com.doctortime.doctortime.Entities.DTO.User;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record UserUpdateDTO(String name,
                            String address,
                            String password) {
}
