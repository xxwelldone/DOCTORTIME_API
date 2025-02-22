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
        try{
            return ResponseEntity.ok().body(this.appointmentService.getAll());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);

        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(this.appointmentService.getById(id));
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body(null);

        }
    }

    @GetMapping("/AppointmentOfUser")
    public ResponseEntity<List<AppointmentResponseDTO>> getByUser(@PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
        try {
        return ResponseEntity.ok().body(this.appointmentService.getAllByUser());
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/AppointmentOfDoctor")
    public ResponseEntity<List<AppointmentResponseDTO>> getByDoctor(@PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
        try {
        return ResponseEntity.ok().body(this.appointmentService.getAllByDoctor());
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/doctorappointments/{doctorId}")
    public ResponseEntity<List<DoctorAppointmentDTO>> getByDoctorId(@PageableDefault(size = 10, sort = {"date"}) Pageable pageable,
                                                                    @PathVariable Long doctorId){
        try {
        return  ResponseEntity.ok().body(this.appointmentService.getDoctorAppointments(doctorId));
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponseDTO> postAppointment(@RequestBody AppointmentRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        try {
        AppointmentResponseDTO created = this.appointmentService.postAppointment(requestDTO);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(created.id).toUri();
        return ResponseEntity.created(uri).body(created);
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentUpdateDTO updateDTO) {
        try {
        return ResponseEntity.accepted().body(this.appointmentService.updateAppointment(id, updateDTO));
        }  catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

}
