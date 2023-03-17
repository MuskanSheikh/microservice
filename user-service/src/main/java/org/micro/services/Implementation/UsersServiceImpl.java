package org.micro.services.Implementation;

import lombok.RequiredArgsConstructor;
import org.micro.common.EnumUtils;
import org.micro.services.UsersService;
import org.micro.web.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.micro.entity.Users;
import org.micro.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {


    private final BCryptPasswordEncoder encoder;

    private final UserRepository userRepository;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Optional<Users> userEntity = userRepository.findByEmail(userDTO.getEmail());
        if (!userEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already exist");
        Users users = Users.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .password(encoder.encode(userDTO.getPassword()))
                .role(String.valueOf(EnumUtils.USER)).build();
        userRepository.save(users);
        return getUserModel(users);
    }

    @Override
    public UserDTO getById(Long id) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return getUserModel(userOptional.get());
        }
        return null;
    }

    private UserDTO getUserModel(Users entity) {
        if (entity == null) throw new UsernameNotFoundException("User does not exist");
        return new UserDTO(entity);
    }
}
