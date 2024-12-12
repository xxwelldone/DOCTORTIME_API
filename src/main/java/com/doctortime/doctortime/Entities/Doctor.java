package com.doctortime.doctortime.Entities;

import com.doctortime.doctortime.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "tb_doctor")
@Data
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
    public Role role;
    @OneToMany(mappedBy = "doctor")
    @JsonIgnoreProperties("doctor")
    public List<Appointment> appointments = new ArrayList<>();


    public Doctor(String name, String photoUrl, String CRM, String address, String email, String password) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.CRM = CRM;
        this.address = address;
        this.email = email;
        this.password = password;
        this.role = Role.DOCTOR;
    }
}
