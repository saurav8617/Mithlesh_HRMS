package com.hrms.jwtconroller;

public class AuthenticationResponse {

    private final String jwt;
    private final String email;
    private final Integer userid;
    private final String roles;

    public AuthenticationResponse(String jwt, String email, String roles, Integer userid) {
        this.jwt = jwt;
        this.email = email;
        this.roles = roles;
        this.userid = userid;
    }

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @return the userid
	 */
	public Integer getUserid() {
		return userid;
	}
}
