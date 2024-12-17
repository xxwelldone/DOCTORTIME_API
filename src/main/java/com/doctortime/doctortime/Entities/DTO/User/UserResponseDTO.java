package com.doctortime.doctortime.Entities.DTO.User;

import com.doctortime.doctortime.Entities.Appointment;
import com.doctortime.doctortime.Entities.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class UserResponseDTO {
    public Long id;
    public String name;
    public String address;
    public String cpf;
    public String email;
    List<Appointment> appointments = new ArrayList<>();

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.appointments = user.getAppointments();
    }
}
