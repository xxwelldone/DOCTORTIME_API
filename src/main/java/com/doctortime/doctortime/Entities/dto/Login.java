package com.doctortime.doctortime.Entities.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public record Login(@Email String email, @NotNull String password) {
}
