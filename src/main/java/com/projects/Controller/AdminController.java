package com.projects.Controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping()
    public ResponseEntity<String> getAdmin (){
        return new ResponseEntity<>("Admin Access" , HttpStatus.OK);
    }
}
