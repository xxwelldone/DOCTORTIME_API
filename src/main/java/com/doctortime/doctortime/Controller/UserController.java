package com.doctortime.doctortime.Controller;

import com.doctortime.doctortime.Entities.User;
import com.doctortime.doctortime.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repo;
    @GetMapping()
    public ResponseEntity<User> getAll(){
        return ResponseEntity.ok().body(new User(
                1L,
                "teste",
                "Rua teste",
                "42652878832",
                "teste@gmail.com",
                "1234"

                ));
    }

}
