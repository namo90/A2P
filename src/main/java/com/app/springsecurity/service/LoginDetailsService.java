package com.app.springsecurity.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.springsecurity.entity.Login;
import com.app.springsecurity.repository.LoginRepository;

@Service
public class LoginDetailsService implements UserDetailsService {

	@Autowired
	LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		// we can write repositry for fathcing data from database

		Login getuserDetails = getUserDetails(username);

		if (username.equalsIgnoreCase(getuserDetails.getLoginUser())) {
			// return (UserDetails) new Login("namo","namo123",new ArrayList<>());
			return new org.springframework.security.core.userdetails.User(getuserDetails.getLoginUser(),
					getuserDetails.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("user not found");

		}
	}

	public Login getUserDetails(String username) {
		Login returnLoginValues = null;
		if(username==null) {
		username="namo";	
		}
		Optional<String> usernameOptional = Optional.of(username);
		if (usernameOptional.isPresent()) {
			Login loginDetails = loginRepository.findByLoginUser(username);
			returnLoginValues = loginDetails;
			return returnLoginValues;
		} else {
			return returnLoginValues;
		}

	}

}
