package com.doctortime.doctortime.Entities;

import com.doctortime.doctortime.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;


import java.util.ArrayList;

import java.util.List;
@Entity(name = "tb_user")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    public Long id;
    public String name;
    public String address;
    @CPF(message = "CPF inválido")
    public String cpf;
    @Email(message = "E-mail inválido")
    public String email;
    public String password;
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    List<Appointment> appointments = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    public Role role;

    public User(Long id, String name, String address, String cpf, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }

}
