package com.doctortime.doctortime.Security.Filter;

import com.doctortime.doctortime.Entities.Doctor;
import com.doctortime.doctortime.Entities.User;
import com.doctortime.doctortime.Entities.Worker;
import com.doctortime.doctortime.Security.DTO.DoctorImpl;
import com.doctortime.doctortime.Security.DTO.UserImpl;
import com.doctortime.doctortime.Security.DTO.WorkerImpl;
import com.doctortime.doctortime.Repository.DoctorRepository;
import com.doctortime.doctortime.Repository.UserRepository;
import com.doctortime.doctortime.Repository.WorkerRepository;
import com.doctortime.doctortime.Security.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    private final DoctorRepository doctorRepository;
    private final WorkerRepository workerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = getTokenJWT(request);

        if (tokenJWT != null) {
            String subject = tokenService.getSubject(tokenJWT);
            UserDetails user = getPrincipal(subject);
            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenJWT(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null) {
            return header.replace("Bearer", "").trim();
        }
        return null;

    }

    private UserDetails getPrincipal(String subject) {
        UserDetails userDetail;
        User user = userRepository.findByEmail(subject);
        Doctor doctor = doctorRepository.findByEmail(subject);
        Worker worker = workerRepository.findByEmail(subject);
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
