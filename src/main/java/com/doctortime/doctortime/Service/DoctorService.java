package com.doctortime.doctortime.Service;

import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorRequestDTO;
import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorResposeDTO;
import com.doctortime.doctortime.Entities.DTO.Doctor.DoctorUpdateDTO;
import com.doctortime.doctortime.Entities.DTO.User.UserResponseDTO;
import com.doctortime.doctortime.Entities.Doctor;
import com.doctortime.doctortime.Repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public final PasswordEncoder passwordEncoder;

    public List<DoctorResposeDTO> getAll() {
        List<Doctor> doctorList = this.doctorRepository.findAll();
        List<DoctorResposeDTO> doctorResposeDTOList = doctorList.stream().map(DoctorResposeDTO::new).toList();
        return doctorResposeDTOList;
    }

    public DoctorResposeDTO getByCRM(String CRM) {
        Doctor doctor = this.doctorRepository.findByCRM(CRM);
        return new DoctorResposeDTO(doctor);
    }

    public DoctorResposeDTO postDoctor(DoctorRequestDTO doctorRequestDTO) {
        String encription = passwordEncoder.encode(doctorRequestDTO.password());
        Doctor doctor = new Doctor(doctorRequestDTO, encription);
        return new DoctorResposeDTO(this.doctorRepository.save(doctor));
    }

    public DoctorResposeDTO putDoctor(Long id, DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = this.doctorRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Doutor não encontrado"));
        if (doctorUpdateDTO.password() != null) {
            String encryptedPassword = passwordEncoder.encode(doctorUpdateDTO.password());
            doctor.Updatedoctor(doctorUpdateDTO, encryptedPassword);
        } else {
            doctor.Updatedoctor(doctorUpdateDTO, null);
        }
        return new DoctorResposeDTO(this.doctorRepository.save(doctor));
    }

}
