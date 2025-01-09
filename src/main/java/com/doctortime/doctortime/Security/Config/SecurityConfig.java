package com.doctortime.doctortime.Security.Config;

import com.doctortime.doctortime.Security.Filter.SecurityFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(
                        sm ->
                                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
                    req.requestMatchers("/auth/login").permitAll();
                    req.requestMatchers("/user/create", "/worker/create").permitAll();
                    req.requestMatchers(HttpMethod.OPTIONS).permitAll();

                    req.requestMatchers("/worker/**").hasRole("WORKER");
                    req.requestMatchers("/doctor/create").hasRole("WORKER");
                    req.requestMatchers(HttpMethod.GET, "/appointments").hasRole("WORKER");

                    req.requestMatchers(HttpMethod.PUT, "/doctor/{id}").hasRole("DOCTOR");
                    req.requestMatchers("/appointments/AppointmentOfDoctor").hasRole("DOCTOR");

                    req.requestMatchers(HttpMethod.PUT, "/appointments/{id}").hasAnyRole("USER", "DOCTOR");

                    req.requestMatchers(HttpMethod.PUT, "/user/{id}").hasRole("USER");
                    req.requestMatchers("/doctor/crm/{crm}").hasRole("USER");
                    req.requestMatchers("/doctor/specialty/{specialty}").hasRole("USER");
                    req.requestMatchers("/appointments/AppointmentOfUser").hasRole("USER");
                    req.requestMatchers("/appointments/create").hasRole("USER");
                    req.requestMatchers("/appointments/doctorappointments/{id}").hasRole("USER");


                    req.anyRequest().authenticated();

                }).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("WORKER").implies("DOCTOR", "USER")

                .build();
    }
}
