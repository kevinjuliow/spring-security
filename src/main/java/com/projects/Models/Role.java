package com.projects.Models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Integer authId ;

    @Override
    public String getAuthority() {
        return null;
    }
}
