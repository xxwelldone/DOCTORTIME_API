package com.doctortime.doctortime.Entities.DTO.Appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record AppointmentRequestDTO(@NotNull
                                    Long DoctorID,
                                    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
                                    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                                    Date date,
                                    @NotNull
                                    String modality
) {
}
