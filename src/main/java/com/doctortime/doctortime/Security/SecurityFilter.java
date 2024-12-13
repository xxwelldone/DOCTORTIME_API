package com.doctortime.doctortime.Security;

import com.doctortime.doctortime.Entities.dto.UserImpl;
import com.doctortime.doctortime.Repository.UserRepository;
import com.doctortime.doctortime.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = getTokenJWT(request);

        if (tokenJWT != null) {
            String subject = tokenService.getSubject(tokenJWT);
            UserImpl user = new UserImpl(userRepository.findByEmail(subject));
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
}
