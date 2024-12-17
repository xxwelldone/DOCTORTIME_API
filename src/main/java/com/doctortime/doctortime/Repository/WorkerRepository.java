package com.doctortime.doctortime.Repository;

import com.doctortime.doctortime.Entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Worker findByEmail(String email);
    boolean existsByEmail(String email);

}
