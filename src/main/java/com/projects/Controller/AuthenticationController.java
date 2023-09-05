package com.projects.Controller;

import com.projects.Models.ApplicationUser;
import com.projects.Models.RegistrationDTO;
import com.projects.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService ;

    @PostMapping()
    public ApplicationUser registerUser (@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
}
