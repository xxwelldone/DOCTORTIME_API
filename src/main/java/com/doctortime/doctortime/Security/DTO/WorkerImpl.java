package com.doctortime.doctortime.Security.DTO;

import com.doctortime.doctortime.Entities.Worker;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Data
public class WorkerImpl implements UserDetails {
    private Worker worker;

    public WorkerImpl(Worker worker) {
        this.worker = worker;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.worker.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return this.worker.getPassword();
    }

    @Override
    public String getUsername() {
        return this.worker.getEmail();
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
