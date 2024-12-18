package com.doctortime.doctortime.Repository;

import com.doctortime.doctortime.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByUserEmail(String userEmail);

    List<Appointment> findAllByDoctorEmail(String doctorEmail);

}
