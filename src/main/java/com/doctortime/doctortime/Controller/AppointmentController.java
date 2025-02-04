package com.doctortime.doctortime.Controller;

import com.doctortime.doctortime.Entities.DTO.Appointment.AppointmentRequestDTO;
import com.doctortime.doctortime.Entities.DTO.Appointment.AppointmentResponseDTO;
import com.doctortime.doctortime.Entities.DTO.Appointment.AppointmentUpdateDTO;
import com.doctortime.doctortime.Entities.DTO.Appointment.DoctorAppointmentDTO;
import com.doctortime.doctortime.Service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping()
    public ResponseEntity<List<AppointmentResponseDTO>> getAll(@PageableDefault(size = 10, sort = {"doctor"}) Pageable pageable) {
        return ResponseEntity.ok().body(this.appointmentService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.appointmentService.getById(id));
    }

    @GetMapping("/AppointmentOfUser")
    public ResponseEntity<List<AppointmentResponseDTO>> getByUser(@PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
        return ResponseEntity.ok().body(this.appointmentService.getAllByUser());
    }

    @GetMapping("/AppointmentOfDoctor")
    public ResponseEntity<List<AppointmentResponseDTO>> getByDoctor(@PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
        return ResponseEntity.ok().body(this.appointmentService.getAllByDoctor());
    }
    @GetMapping("/doctorappointments/{doctorId}")
    public ResponseEntity<List<DoctorAppointmentDTO>> getByDoctorId(@PageableDefault(size = 10, sort = {"date"}) Pageable pageable,
                                                                    @PathVariable Long doctorId){
        return  ResponseEntity.ok().body(this.appointmentService.getDoctorAppointments(doctorId));
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponseDTO> postAppointment(@RequestBody AppointmentRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        AppointmentResponseDTO created = this.appointmentService.postAppointment(requestDTO);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(created.id).toUri();
        return ResponseEntity.created(uri).body(created);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentUpdateDTO updateDTO) {
        return ResponseEntity.accepted().body(this.appointmentService.updateAppointment(id, updateDTO));
    }

}
