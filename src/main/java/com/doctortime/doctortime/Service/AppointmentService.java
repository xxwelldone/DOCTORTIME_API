package com.doctortime.doctortime.Service;

import com.doctortime.doctortime.Entities.Appointment;
import com.doctortime.doctortime.Entities.DTO.Appointment.AppointmentRequestDTO;
import com.doctortime.doctortime.Entities.DTO.Appointment.AppointmentResponseDTO;
import com.doctortime.doctortime.Entities.DTO.Appointment.AppointmentUpdateDTO;
import com.doctortime.doctortime.Entities.DTO.Appointment.DoctorAppointmentDTO;
import com.doctortime.doctortime.Entities.Doctor;
import com.doctortime.doctortime.Entities.User;
import com.doctortime.doctortime.Repository.AppointmentRepository;
import com.doctortime.doctortime.Repository.DoctorRepository;
import com.doctortime.doctortime.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;


    public AppointmentResponseDTO postAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Doctor doctor = this.doctorRepository.findById(appointmentRequestDTO.DoctorID()).orElseThrow();
        User user = this.userRepository.findByEmail(userDetails.getUsername());
        Appointment appointment = new Appointment(appointmentRequestDTO, doctor, user);
        return new AppointmentResponseDTO(this.appointmentRepository.save(appointment));
    }

    public List<AppointmentResponseDTO> getAllByUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Appointment> appointmentList = this.appointmentRepository.findAllByUserEmail(userDetails.getUsername());
        List<AppointmentResponseDTO> appointmentResponseDTOList = appointmentList.stream().map(AppointmentResponseDTO::new).toList();
        return appointmentResponseDTOList;
    }

    public List<AppointmentResponseDTO> getAllByDoctor() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Appointment> appointmentList = this.appointmentRepository.findAllByDoctorEmail(userDetails.getUsername());
        List<AppointmentResponseDTO> appointmentResponseDTOList = appointmentList.stream().map(AppointmentResponseDTO::new).toList();
        return appointmentResponseDTOList;
    }

    public List<AppointmentResponseDTO> getAll() {
        List<Appointment> appointmentList = this.appointmentRepository.findAll();
        List<AppointmentResponseDTO> appointmentResponseDTOList = appointmentList.stream().map(AppointmentResponseDTO::new).toList();
        return appointmentResponseDTOList;
    }
    public AppointmentResponseDTO getById(Long id){
       List<AppointmentResponseDTO> appointmentList= this.getAllByUser();

       AppointmentResponseDTO appointmentResponseDTO= appointmentList.stream().filter(findAppointment ->
                       Objects.equals(findAppointment.id, id)).findFirst()
               .orElseThrow(()->new RuntimeException("Not found"));

        ;
        return appointmentResponseDTO;

    }

    public AppointmentResponseDTO updateAppointment(Long id, AppointmentUpdateDTO appointmentUpdateDTO) {
        Appointment appointment = this.appointmentRepository.findById(id).orElseThrow();
        appointment.updateAppointment(appointmentUpdateDTO);
        return new AppointmentResponseDTO(this.appointmentRepository.save(appointment));
    }
public List<DoctorAppointmentDTO> getDoctorAppointments(Long id){
      List<Appointment> appointments=  this.appointmentRepository.findAllByDoctorId(id);
      List<DoctorAppointmentDTO> doctorAppointmentDTOS = appointments.stream().map(DoctorAppointmentDTO::new).toList();
    return doctorAppointmentDTOS;
}

}
