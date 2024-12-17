package com.doctortime.doctortime.Controller;

import com.doctortime.doctortime.Entities.DTO.Worker.WorkerRequestDTO;
import com.doctortime.doctortime.Entities.DTO.Worker.WorkerResposeDTO;
import com.doctortime.doctortime.Entities.DTO.Worker.WorkerUpdateDTO;
import com.doctortime.doctortime.Entities.Worker;
import com.doctortime.doctortime.Repository.WorkerRepository;
import com.doctortime.doctortime.Service.WorkerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/worker")
@AllArgsConstructor
public class WorkerController {
    private final WorkerService workerService;


    @GetMapping()
    public ResponseEntity<List<WorkerResposeDTO>> getAll() {
        return ResponseEntity.ok().body(this.workerService.getAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<WorkerResposeDTO> getByEmail(@PathVariable @Valid @Email String email) {
        return ResponseEntity.ok().body(this.workerService.getByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<WorkerResposeDTO> postWorker(@RequestBody @Valid WorkerRequestDTO workerRequestDTO, UriComponentsBuilder uriBuilder) {
        WorkerResposeDTO workerResposeDTO = this.workerService.postWorker(workerRequestDTO);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(workerResposeDTO.id).toUri();
        return ResponseEntity.created(uri).body(workerResposeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkerResposeDTO> putWorker(@PathVariable Long id, @RequestBody WorkerUpdateDTO workerUpdateDTO) {
        WorkerResposeDTO workerResposeDTO = this.workerService.putWorker(id, workerUpdateDTO);
        return ResponseEntity.accepted().body(workerResposeDTO);
    }
}
