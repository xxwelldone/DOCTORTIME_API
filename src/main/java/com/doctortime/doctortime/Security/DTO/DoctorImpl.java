package com.doctortime.doctortime.Security.DTO;

import com.doctortime.doctortime.Entities.Doctor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Data
public class DoctorImpl implements UserDetails {
    private Doctor doctor;

    public DoctorImpl(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.doctor.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return this.doctor.getPassword();
    }

    @Override
    public String getUsername() {
        return this.doctor.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
