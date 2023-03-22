package org.micro.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.micro.services.UsersService;
import org.micro.web.dto.UserDTO;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("user-api/")
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;

    private final WebClient webClient;
    @PostMapping("create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN_USER')")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        UserDTO userResponse = usersService.createUser(userDTO);
        if(userResponse != null){
            return ResponseEntity.ok("user created");
        }
        return ResponseEntity.badRequest().body("fail to create user");
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        UserDTO response = usersService.getById(id);
        if(response != null){
            response.setPassword("");

            Boolean result = webClient.get()
                    .uri("http://localhost:9094/job-api/get/{userId}",id)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            
        return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(" user not found");
    }
}
