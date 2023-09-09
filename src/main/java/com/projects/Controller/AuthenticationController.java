package com.projects.Controller;

import com.projects.CustomMessage.CustomException;
import com.projects.Models.LoginDto;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser (@RequestBody RegistrationDTO body) {
        try{
            authenticationService.registerUser(body.getUsername(), body.getPassword());
            return new ResponseEntity<>("Success Created User \n username : "+body.getUsername()+
                    "\n password : "+body.getPassword()
                    , HttpStatus.OK);
        }catch (CustomException err){
            return new ResponseEntity<>(err.getMessage() , HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping("/login")
    public LoginDto loginUser (@RequestBody RegistrationDTO body){
//        try{
//            LoginDto auth  =  authenticationService.loginUser(body.getUsername(), body.getPassword());
//            return new ResponseEntity<>(auth.getUser().getUsername()+ "\njwt : "+auth.getToken()
//            , HttpStatus.OK);
//        }catch (CustomException err){
//            return new ResponseEntity<>(err.getMessage() , HttpStatus.FORBIDDEN);
//        }
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }


}
