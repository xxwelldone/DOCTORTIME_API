package com.doctortime.doctortime.Repository;

import com.doctortime.doctortime.Entities.Doctor;
import com.doctortime.doctortime.Entities.enums.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByEmail(String email);

    Doctor findByCRM(String CRM);

    boolean existsByEmail(String email);

    boolean existsByCRM(String CRM);

    List<Doctor> findBySpecialty(Specialty specialty);
}
