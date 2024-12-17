package com.doctortime.doctortime.Repository;

import com.doctortime.doctortime.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByUserEmail(String userEmail);

    List<Appointment> findAllByDoctorEmail(String doctorEmail);

}
