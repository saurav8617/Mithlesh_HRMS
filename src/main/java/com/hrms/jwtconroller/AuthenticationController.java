package com.hrms.jwtconroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entities.Employee;
import com.hrms.repository.EmployeeRepository;
import com.hrms.security.JwtUtil;
import com.hrms.security.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "http://localhost:4200/*", allowCredentials = "true")
//@CrossOrigin
@RestController
@RequestMapping("/api/v1/credentials")
@Tag(name = "Authentication API", description = "API endpoints related to authentication")
@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Not found") })
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

//	@Autowired
//	private PatientRepository patientRepository;
	@Autowired
	private EmployeeRepository userRepository;


	@PostMapping("/signin")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		final String token = jwtUtil.generateToken(userDetails);
		final String responseEmail = userDetails.getUsername();
		Employee foundUser = userRepository.findByEmail(responseEmail);
		Integer userid = null;
		if (foundUser != null) {
			userid = foundUser.getEmpNo();
		}

		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.toList();
		String currentUserRole = roles.get(0).split("_")[1];
		return ResponseEntity.ok(new AuthenticationResponse(token, responseEmail, currentUserRole,userid));
	}
	
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostMapping("/signup")
	public ResponseEntity<Employee> signup(@RequestBody Employee request) {
		Employee newUser = new Employee();
		//newUser.setCreatedOn(request.getCreatedOn());
		newUser.setEmpNo(request.getEmpNo());
		newUser.setEmail(request.getEmail());
		newUser.setFirstName(request.getFirstName());
		newUser.setLastName(request.getLastName());
		//newUser.setIsActive(true);
		newUser.setBirthDate(request.getBirthDate());
		newUser.setGender(request.getGender());
		newUser.setHireDate(request.getHireDate());
		newUser.setPassword(passwordEncoder().encode(request.getPassword()));
		newUser.setRole(request.getRole());
		newUser = userRepository.save(newUser);
//		Patient p = new Patient();
//		p.setSsn(request.getSsn());
//		p.setName(String.join(" ", request.getFirstName(), request.getLastName()));
//		p.setAddress("DEFAULT VALUE");
//		p.setInsuranceid(121212);
//		p.setPcp(1);
//		p.setPhone("0000000000");
//		p.setUserid(newUser.getUserId());
//		try {
//			patientRepository.save(p);
//		} catch (IllegalArgumentException ex) {
//			return null;
//		}
		return ResponseEntity.ok(newUser);
}
}