package com.doctortime.doctortime.Entities;

import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorRequestDTO;
import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorUpdateDTO;
import com.doctortime.doctortime.Entities.enums.Role;
import com.doctortime.doctortime.Entities.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "tb_doctor")
@Data
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String photoUrl;
    public String CRM;
    public String address;
    @Email(message = "Email inválido")
    public String email;
    public String password;
    @Enumerated(EnumType.STRING)
    public Role role = Role.DOCTOR;
    public Specialty specialty;
    @OneToMany(mappedBy = "doctor")
    @JsonIgnoreProperties("doctor")
    public List<Appointment> appointments = new ArrayList<>();


    public Doctor(String name, String photoUrl, String CRM, Specialty specialty, String address, String email, String password) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.CRM = CRM;
        this.specialty = specialty;
        this.address = address;
        this.email = email;
        this.password = password;

    }

    public Doctor(DoctorRequestDTO doctorRequestDTO, String password) {
        this.name = doctorRequestDTO.name();
        this.photoUrl = doctorRequestDTO.photoUrl();
        this.CRM = doctorRequestDTO.CRM();
        this.specialty = doctorRequestDTO.specialty();
        this.address = doctorRequestDTO.address();
        this.email = doctorRequestDTO.email();
        this.password = password;

    }

    public void Updatedoctor(DoctorUpdateDTO doctorUpdateDTO, String encryptedPassword) {
        if (doctorUpdateDTO.name() != null) {
            this.name = doctorUpdateDTO.name();
        }
        if (encryptedPassword != null) {
            this.password = encryptedPassword;
        }
        if (doctorUpdateDTO.email() != null && doctorUpdateDTO.email().contains("@")) {
            this.email = doctorUpdateDTO.email();
        }
        if (doctorUpdateDTO.address() != null) {
            this.address = doctorUpdateDTO.address();
        }
        if (doctorUpdateDTO.photoUrl() != null) {
            this.photoUrl = doctorUpdateDTO.photoUrl();
        }
    }
}
