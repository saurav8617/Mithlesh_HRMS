package com.hrms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationManagerConfiguration {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    
    @SuppressWarnings("unchecked")
	@Bean
    public AuthenticationManager authenticationManager() throws Exception {
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder((ObjectPostProcessor<Object>) new DefaultAuthenticationEventPublisher());
        builder.authenticationProvider(customAuthenticationProvider)
               .userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder);
        return builder.build();
    }
    
}
