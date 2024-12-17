package com.doctortime.doctortime.Service;

import com.doctortime.doctortime.Entities.DTO.Worker.WorkerRequestDTO;
import com.doctortime.doctortime.Entities.DTO.Worker.WorkerResposeDTO;
import com.doctortime.doctortime.Entities.DTO.Worker.WorkerUpdateDTO;
import com.doctortime.doctortime.Entities.Worker;
import com.doctortime.doctortime.Repository.WorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final PasswordEncoder passwordEncoder;

    public WorkerResposeDTO postWorker(WorkerRequestDTO workerRequestDTO) {
        if (this.workerRepository.existsByEmail(workerRequestDTO.email())) {
            throw new IllegalArgumentException("E-mail já existe");
        }
        String encrypt = passwordEncoder.encode(workerRequestDTO.password());
        Worker worker = new Worker(workerRequestDTO, encrypt);
        return new WorkerResposeDTO(this.workerRepository.save(worker));
    }

    public List<WorkerResposeDTO> getAll() {
        List<Worker> workerList = this.workerRepository.findAll();
        List<WorkerResposeDTO> workerResposeDTOList = workerList.stream()
                .map(WorkerResposeDTO::new).toList();
        return workerResposeDTOList;
    }

    public WorkerResposeDTO putWorker(Long id, WorkerUpdateDTO workerUpdateDTO) {
        if (this.workerRepository.existsByEmail(workerUpdateDTO.email())) {
            throw new IllegalArgumentException("E-mail já existe");
        }
        Worker worker = this.workerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        if (workerUpdateDTO.password() != null) {
            String encrypt = passwordEncoder.encode(workerUpdateDTO.password());
            worker.updateWorker(workerUpdateDTO, encrypt);

        } else {

            worker.updateWorker(workerUpdateDTO, null);
        }
        return new WorkerResposeDTO(this.workerRepository.save(worker));

    }

    public WorkerResposeDTO getByEmail(String email) {
        Worker worker = this.workerRepository.findByEmail(email);
        return new WorkerResposeDTO(worker);
    }
}
