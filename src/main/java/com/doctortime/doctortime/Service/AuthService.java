package com.doctortime.doctortime.Service;

import com.doctortime.doctortime.Entities.dto.UserImpl;
import com.doctortime.doctortime.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserImpl user = new UserImpl(userRepository.findByEmail(username));

        return user;
    }
}
