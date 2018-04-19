package com.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.model.User;

@Service
public class UserSecuityServiceImpl implements UserDetailsService {

	/**
	 * The application logger
	 */
	private static final Logger LOG=LoggerFactory.getLogger(UserSecuityServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userDao.findByUsername(username);
			if (null == user) {
				LOG.warn("username {} not found",username);
				throw new UsernameNotFoundException("Username "+ username +" not found" );
			}
		
		
		return user;
	}

}
