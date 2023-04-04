package org.micro.services;

import org.micro.entity.Menu;
import org.micro.web.dto.MenuItalianDTO;
import org.micro.web.dto.UserDTO;

import java.util.List;


public interface UsersService {
    public UserDTO createUser(UserDTO userDTO);

    UserDTO getById(Long id);

    List<MenuItalianDTO> getMenuTypeAndItems();

    Menu createMenu(Menu entity);
}
