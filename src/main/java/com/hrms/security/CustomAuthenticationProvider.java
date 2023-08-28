package com.hrms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.hrms.entities.Employee;
import com.hrms.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private EmployeeRepository userRepository;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @PostConstruct
    public void init() {
        setUserDetailsService(userDetailsService);
    }

    @Override
    @SuppressWarnings("unused")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
		String password = authentication.getCredentials().toString();

        // Check if the user exists in the database
  //      Employee user = userRepository.findByEmail(email).orElse(null);
		Employee user = userRepository.findByEmail(email);

        if (user == null) {
            throw new BadCredentialsException("User not found");
        }

        // Perform additional checks on the user

        // Use the parent class's authenticate method to perform password validation
        return super.authenticate(authentication);
    }
}