package com.doctortime.doctortime.Security.Service;

import com.doctortime.doctortime.Entities.Doctor;
import com.doctortime.doctortime.Entities.User;
import com.doctortime.doctortime.Entities.Worker;
import com.doctortime.doctortime.Security.DTO.DoctorImpl;
import com.doctortime.doctortime.Security.DTO.UserImpl;
import com.doctortime.doctortime.Security.DTO.WorkerImpl;
import com.doctortime.doctortime.Repository.DoctorRepository;
import com.doctortime.doctortime.Repository.UserRepository;
import com.doctortime.doctortime.Repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final WorkerRepository workerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       UserDetails user = new UserImpl(userRepository.findByEmail(username));

        UserDetails userDetail;
        Doctor doctor = doctorRepository.findByEmail(username);
        User user = userRepository.findByEmail(username);

        Worker worker = workerRepository.findByEmail(username);
        if (user != null) {
            userDetail = new UserImpl(user);
            return userDetail;
        }

        if (doctor != null) {
            userDetail = new DoctorImpl(doctor);
            return userDetail;
        }

        return userDetail = new WorkerImpl(worker);
    }

}

