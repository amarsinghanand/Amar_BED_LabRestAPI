package com.gl.lab.restapi.studentmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gl.lab.restapi.studentmgmt.entity.User;
import com.gl.lab.restapi.studentmgmt.repository.UserRepository;

public class StudentUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User details not found for the user " + userName);
		}
		return new StudentUserDetails(user);
	}

}
