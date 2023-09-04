package com.projects.Service;

import com.projects.Models.ApplicationUser;
import com.projects.Models.Role;
import com.projects.Repository.RoleRepository;
import com.projects.Repository.UserRepository;
import com.projects.ExceptionMessage.UsernameExist;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ApplicationUser registerUser (String username , String password) {
        if (userRepository.findByUsername(username).isPresent()) throw new UsernameExist("Username already Exist");

        String encodePassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        return userRepository.save(new ApplicationUser(username , encodePassword , authorities));
    }
}