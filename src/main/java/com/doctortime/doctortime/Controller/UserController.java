package com.doctortime.doctortime.Controller;

import com.doctortime.doctortime.Entities.DTO.User.UserRequestDTO;
import com.doctortime.doctortime.Entities.DTO.User.UserResponseDTO;
import com.doctortime.doctortime.Entities.DTO.User.UserUpdateDTO;
import com.doctortime.doctortime.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getByEmail(@Email @Valid @PathVariable String email) {

        return ResponseEntity.ok(this.userService.getByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody UserRequestDTO userRequestDTO, UriComponentsBuilder uriBuilder) {
        UserResponseDTO saved = this.userService.postUser(userRequestDTO);
        URI uri = uriBuilder.path("user/{id}").buildAndExpand(saved.id).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> putUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserResponseDTO userUpdated = this.userService.putUser(id, userUpdateDTO);
        return ResponseEntity.accepted().body(userUpdated);
    }

}
