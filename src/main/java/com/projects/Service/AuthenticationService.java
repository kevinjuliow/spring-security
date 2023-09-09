package com.projects.Service;

import com.projects.Models.ApplicationUser;
import com.projects.Models.LoginDto;
import com.projects.Models.Role;
import com.projects.Repository.RoleRepository;
import com.projects.Repository.UserRepository;
import com.projects.CustomMessage.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    @Autowired
    private RoleRepository roleRepository ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private AuthenticationManager authenticationManager ;
    @Autowired
    private TokenService tokenService ;

    public ApplicationUser registerUser (String username , String password) {
        if (userRepository.findByUsername(username).isPresent()) throw new CustomException("Username already Exist");

        String encodePassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        return userRepository.save(new ApplicationUser(username , encodePassword , authorities));
    }



    public LoginDto loginUser (String username, String password) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username , password)
            ) ;
            String token = tokenService.generateJwt(auth);
            return new LoginDto(userRepository.findByUsername(username).get().getUsername(), token);
        }catch (AuthenticationException err){
            return new LoginDto(null , "");
        }
    }
}