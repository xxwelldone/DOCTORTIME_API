package com.doctortime.doctortime.Security.Controller;

import com.doctortime.doctortime.Security.DTO.AuthenticatedUser;
import com.doctortime.doctortime.Security.DTO.Login;
import com.doctortime.doctortime.Security.Service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity <AuthenticatedUser> userLogin(@RequestBody @Valid Login login){
        var authToken = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        Authentication authentication = manager.authenticate(authToken);

        String tokenJWT = tokenService
                .tokenGenerator((UserDetails) authentication
                        .getPrincipal());
        String role = ((UserDetails) authentication.getPrincipal()).getAuthorities().toString();
      return  ResponseEntity.ok( new AuthenticatedUser(login.email(), tokenJWT, role) );
    }
}
