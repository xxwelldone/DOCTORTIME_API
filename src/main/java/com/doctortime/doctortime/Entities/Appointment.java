package com.doctortime.doctortime.Entities;

import com.doctortime.doctortime.Entities.DTO.Appointment.AppointmentRequestDTO;
import com.doctortime.doctortime.Entities.DTO.Appointment.AppointmentUpdateDTO;
import com.doctortime.doctortime.Entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity(name = "tb_appointments")
@AllArgsConstructor
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JsonIgnoreProperties("appointments")
    public User user;
    @ManyToOne
    @JsonIgnoreProperties("appointments")
    public Doctor doctor;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date date;
    public String modality;
    public Status status = Status.AGENDADO;


    public Appointment(User user, Doctor doctor, Date date, String modality) {
        this.user = user;
        this.doctor = doctor;
        this.date = date;
        this.modality = modality;

    }

    public Appointment(AppointmentRequestDTO appointmentRequestDTO, Doctor doctor, User user) {
        this.user = user;
        this.doctor = doctor;
        this.date = appointmentRequestDTO.date();
        this.modality = appointmentRequestDTO.modality();
    }

    public void updateAppointment(AppointmentUpdateDTO appointmentUpdateDTO) {
        if (appointmentUpdateDTO.date() != null) {
            this.date = appointmentUpdateDTO.date();
        }
        if (appointmentUpdateDTO.modality() != null) {
            this.modality = appointmentUpdateDTO.modality();
        }
        if (appointmentUpdateDTO.status() != null) {
            this.status = appointmentUpdateDTO.status();
        }

    }
}
