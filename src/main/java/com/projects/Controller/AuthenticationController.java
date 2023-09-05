package com.projects.Controller;

import com.projects.ExceptionMessage.UsernameExist;
import com.projects.Models.ApplicationUser;
import com.projects.Models.RegistrationDTO;
import com.projects.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService ;

    @PostMapping()
    public ResponseEntity<?> registerUser (@RequestBody RegistrationDTO body) {
        try{
            authenticationService.registerUser(body.getUsername(), body.getPassword());
            return new ResponseEntity<>("Success Created User \n username : "+body.getUsername()+
                    "\n password : "+body.getPassword()
                    , HttpStatus.OK);
        }catch (UsernameExist err){
            return new ResponseEntity<>(err.getMessage() , HttpStatus.FORBIDDEN);
        }
    }
}
