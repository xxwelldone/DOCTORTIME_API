package com.doctortime.doctortime.Controller;

import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorRequestDTO;
import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorResposeDTO;
import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorUpdateDTO;
import com.doctortime.doctortime.Entities.Doctor;
import com.doctortime.doctortime.Repository.DoctorRepository;
import com.doctortime.doctortime.Service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping()
    public ResponseEntity<List<DoctorResposeDTO>> getAll(@PageableDefault(size = 10, sort = {"specialty"}) Pageable pageable) {
        return ResponseEntity.ok().body(this.doctorService.getAll());
    }

    @GetMapping("crm/{crm}")
    public ResponseEntity<DoctorResposeDTO> getByCRM(@PathVariable String CRM) {

        return ResponseEntity.ok().body(this.doctorService.getByCRM(CRM));
    }

    @GetMapping("specialty/{specialty}")
    public ResponseEntity<List<DoctorResposeDTO>> getBySpecialty(@PathVariable String specialty, @PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok().body(this.doctorService.getBySpecialty(specialty));
    }

    @PostMapping("/create")
    public ResponseEntity<DoctorResposeDTO> postDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO, UriComponentsBuilder uriBuilder) {
        DoctorResposeDTO saved = this.doctorService.postDoctor(doctorRequestDTO);
        URI uri = uriBuilder.path("doctor/{id}").buildAndExpand(saved.id).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResposeDTO> putDoctor(@PathVariable Long id, DoctorUpdateDTO doctorUpdateDTO) {
        DoctorResposeDTO doctorResposeDTO = this.doctorService.putDoctor(id, doctorUpdateDTO);
        return ResponseEntity.accepted().body(doctorResposeDTO);
    }

}
