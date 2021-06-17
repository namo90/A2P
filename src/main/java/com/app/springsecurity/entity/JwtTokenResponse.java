package com.app.springsecurity.entity;

public class JwtTokenResponse {
	String token;

	public JwtTokenResponse(String token) {
		super();
		this.token = token;
	}

	public JwtTokenResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
