package com.doctortime.doctortime.Entities.DTO.Appointment;

import com.doctortime.doctortime.Entities.Appointment;
import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorResposeDTO;
import com.doctortime.doctortime.Entities.DTO.User.UserResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DoctorAppointmentDTO {
    public Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date date;
    public DoctorAppointmentDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.date = appointment.getDate();
    }
}
