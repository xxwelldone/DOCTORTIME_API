package com.doctortime.doctortime.Entities.DTO.Appointment;

import com.doctortime.doctortime.Entities.Appointment;
import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorResposeDTO;
import com.doctortime.doctortime.Entities.DTO.User.UserResponseDTO;
import com.doctortime.doctortime.Entities.User;
import com.doctortime.doctortime.Entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AppointmentResponseDTO {
    public Long id;
    public UserResponseDTO user;
    public DoctorResposeDTO doctor;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date date;
    public String modality;
    public Status status;

    public AppointmentResponseDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.user = new UserResponseDTO(appointment.getUser());
        this.doctor = new DoctorResposeDTO(appointment.getDoctor());
        this.date = appointment.getDate();
        this.modality = appointment.getModality();
        this.status = appointment.getStatus();
    }
}
