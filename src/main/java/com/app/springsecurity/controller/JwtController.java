package com.app.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.springsecurity.entity.JwtTokenResponse;
import com.app.springsecurity.entity.Login;
import com.app.springsecurity.helper.JwtUtil;
import com.app.springsecurity.service.LoginDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class JwtController {
	@Autowired
	private LoginDetailsService loginDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public ResponseEntity<Object> generateToken(@RequestBody Login login) throws Exception {
		System.out.println("get value of login object here"+" "+login.getLoginUser()+" "+login.getPassword());

		try {

			this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getLoginUser(), login.getPassword()));
		} catch (UsernameNotFoundException | BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		UserDetails userdetails = this.loginDetailsService.loadUserByUsername(login.getLoginUser());
		String token = this.jwtUtil.generateToken(userdetails);

		return ResponseEntity.ok(new JwtTokenResponse(token));
	}
}
