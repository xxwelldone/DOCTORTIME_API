package com.doctortime.doctortime.Entities.DTO.Doctor;

import com.doctortime.doctortime.Entities.Appointment;
import com.doctortime.doctortime.Entities.Doctor;
import com.doctortime.doctortime.Entities.enums.Specialty;

import java.util.ArrayList;
import java.util.List;

public class DoctorResposeDTO {
    public Long id;
    public String name;
    public String photoUrl;
    public String CRM;
    public Specialty specialty;
    public String address;
    public String email;
//    public List<Appointment> appointments = new ArrayList<>();

    public DoctorResposeDTO(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.photoUrl = doctor.getPhotoUrl();
        this.CRM = doctor.getCRM();
        this.specialty = doctor.getSpecialty();
        this.address = doctor.getAddress();
        this.email = doctor.getEmail();
//        this.appointments = doctor.getAppointments();
    }
}
