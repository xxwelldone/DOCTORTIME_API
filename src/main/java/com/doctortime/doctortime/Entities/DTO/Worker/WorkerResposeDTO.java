package com.doctortime.doctortime.Entities.DTO.Worker;

import com.doctortime.doctortime.Entities.Worker;
import com.doctortime.doctortime.Entities.enums.Role;
import org.hibernate.validator.constraints.Email;

public class WorkerResposeDTO {
    public Long id;
    public String name;
    @Email(message = "Dado inválido")
    public String email;
    public String setor;
    public Role role;

    public WorkerResposeDTO(Worker worker) {
        this.id = worker.getId();
        this.name = worker.getName();
        this.email = worker.getEmail();
        this.setor = worker.getSetor();
        this.role = worker.getRole();
    }
}
