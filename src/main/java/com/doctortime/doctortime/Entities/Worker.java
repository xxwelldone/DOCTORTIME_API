package com.doctortime.doctortime.Entities;

import com.doctortime.doctortime.Entities.DTO.Worker.WorkerRequestDTO;
import com.doctortime.doctortime.Entities.DTO.Worker.WorkerUpdateDTO;
import com.doctortime.doctortime.Entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    public Role role = Role.WORKER;

    public Worker(String name, String email, String setor, String password) {

        this.name = name;
        this.email = email;
        this.setor = setor;
        this.password = password;
    }

    public Worker(WorkerRequestDTO workerRequestDTO, String encrypt) {

        this.name = workerRequestDTO.name();
        this.email = workerRequestDTO.email();
        this.setor = workerRequestDTO.setor();
        this.password = encrypt;
    }

    public void updateWorker(WorkerUpdateDTO workerUpdateDTO, String encryptedPassword) {
        if (workerUpdateDTO.name() != null) {
            this.name = workerUpdateDTO.name();
        }
        if (encryptedPassword != null) {
            this.password = encryptedPassword;
        }
        if (workerUpdateDTO.email() != null && workerUpdateDTO.email().contains("@")) {
            this.email = workerUpdateDTO.email();
        }
        if (workerUpdateDTO.setor() != null) {
            this.setor = workerUpdateDTO.setor();
        }

    }
}
