package com.doctortime.doctortime.Repository;

import com.doctortime.doctortime.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

   Doctor findByEmail(String email);
   Doctor findByCRM(String CRM);
   boolean existsByEmail(String email);
   boolean existsByCRM(String CRM);
}
