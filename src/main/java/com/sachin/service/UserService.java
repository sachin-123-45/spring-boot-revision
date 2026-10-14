package com.sachin.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sachin.entity.AppUser;
import com.sachin.repository.AppUserRepository;

@Service
public class UserService {

	
	
	
	private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    
    
    public UserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
             this.appUserRepository = appUserRepository;
             this.passwordEncoder = passwordEncoder;
    }
    
    
    
    
    public AppUser register(AppUser user)
    {
    	
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
      
      return appUserRepository.save(user);
    }
    
    
    
  
    
}
