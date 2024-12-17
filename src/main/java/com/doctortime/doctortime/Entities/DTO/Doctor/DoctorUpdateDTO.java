package com.doctortime.doctortime.Entities.DTO.Doctor;

public record DoctorUpdateDTO(
        String name,
        String photoUrl,
        String address,
        String email,
        String password
) {
}
