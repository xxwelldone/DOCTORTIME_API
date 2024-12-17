package com.doctortime.doctortime.Entities.DTO.Appointment;

import com.doctortime.doctortime.Entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record AppointmentUpdateDTO(@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
                                   @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                                    Date date,
                                    String modality,
                                   Status status
                                   ) {
}
