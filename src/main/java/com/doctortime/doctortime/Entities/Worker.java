package com.doctortime.doctortime.Entities;

import com.doctortime.doctortime.Entities.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

@Entity(name = "tb_worker")
@Data
@NoArgsConstructor
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    @Email(message = "Dado inválido")
    public String email;
    public String setor;
    public String password;
    @Enumerated(EnumType.STRING)
    public Role role;

    public Worker( String name, String email, String setor, String password) {

        this.name = name;
        this.email = email;
        this.setor = setor;
        this.password = password;
        this.role = Role.WORKER;
    }
}
