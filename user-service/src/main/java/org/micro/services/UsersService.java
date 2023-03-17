package org.micro.services;

import org.micro.web.dto.UserDTO;


public interface UsersService {
    public UserDTO createUser(UserDTO userDTO);

    UserDTO getById(Long id);
}
