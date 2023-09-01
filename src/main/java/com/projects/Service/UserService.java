package com.projects.Service;

import com.projects.Models.ApplicationUser;
import com.projects.Models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in User Detail Service");
        if (!username.equals("kevin")) throw new UsernameNotFoundException("Wrong Username");
        Set<Role> roles= new HashSet<>();
        roles.add(new Role(1 , "USER"));
        return new ApplicationUser(1 , "kevin " , encoder.encode("password"), roles);
    }
}
