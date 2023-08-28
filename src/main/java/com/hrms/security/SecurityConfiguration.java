package com.hrms.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter(JwtUtil jwtUtil, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, UserDetailsServiceImpl userDetailsService) {
        return new JwtRequestFilter(jwtUtil, jwtAuthenticationEntryPoint, userDetailsService);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        return new JwtAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                        .anyRequest().permitAll()
//                        		.requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
//                                .requestMatchers("/authenticate").permitAll()
//                                .requestMatchers("/v3/api-docs/**").permitAll()
//        						.requestMatchers("/swagger-ui/**").permitAll()
//        						.requestMatchers("/swagger-resources/**").permitAll()
//        						.requestMatchers("/swagger-ui.html").permitAll()
//        						.requestMatchers("/webjars/**").permitAll()
//                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                                .anyRequest().authenticated()
    	http.csrf(AbstractHttpConfigurer::disable)
    	.cors(cors -> cors
                .configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:4200"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setAllowCredentials(true);
                    return config;
                })
            )
		.authorizeHttpRequests(authorizeHttpRequests ->
			authorizeHttpRequests
			.requestMatchers(HttpMethod.POST, "/api/v1/credentials/**").permitAll()
			.requestMatchers(HttpMethod.OPTIONS, "/api/v1/employees/**").permitAll()
			.requestMatchers(HttpMethod.GET, "/api/v1/insights/**").hasAuthority("ROLE_ADMIN")
//			.requestMatchers(HttpMethod.POST, "/**").authenticated()
			.requestMatchers("/**").permitAll()
		)
		.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint(objectMapper()))

                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter(jwtUtil(), jwtAuthenticationEntryPoint(objectMapper()), userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @SuppressWarnings("removal")
    @Bean
    public AuthenticationManager authenticationManager(ObjectPostProcessor<Object> objectPostProcessor) throws Exception {
        final AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
                .and().authenticationProvider(daoAuthenticationProvider());
        return builder.build();
    }


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}