package com.doctortime.doctortime.Service;

import com.doctortime.doctortime.Entities.DTO.User.UserRequestDTO;
import com.doctortime.doctortime.Entities.DTO.User.UserResponseDTO;
import com.doctortime.doctortime.Entities.DTO.User.UserUpdateDTO;
import com.doctortime.doctortime.Entities.User;
import com.doctortime.doctortime.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAll() {
        List<User> userList = this.userRepository.findAll();
        List<UserResponseDTO> userResponseDTOList = userList.stream().map(UserResponseDTO::new).toList();
        return userResponseDTOList;
    }

    public UserResponseDTO getByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        return new UserResponseDTO(user);
    }


    public UserResponseDTO postUser(UserRequestDTO userRequestDTO) {
        if (this.userRepository.existsByEmail(userRequestDTO.email())) {
            throw new IllegalArgumentException("E-mail já existe");
        }
        System.out.println(userRequestDTO.password());
        String encription = passwordEncoder.encode(userRequestDTO.password());
        User user = new User(userRequestDTO, encription);

        return new UserResponseDTO(this.userRepository.save(user));


    }

    public UserResponseDTO putUser(Long id, UserUpdateDTO userUpdateDTO) {
        String encrypted = null;
        if (userUpdateDTO.password() != null) {
            encrypted = passwordEncoder.encode(userUpdateDTO.password());
        }
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.UpdateUser(userUpdateDTO, encrypted);
        return new UserResponseDTO(this.userRepository.save(user));

    }
}
