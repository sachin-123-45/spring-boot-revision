package com.sachin.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sachin.entity.AppUser;
import com.sachin.repository.AppUserRepository;

@Service
public class UserService {

	
	
	
	private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    
    
    public UserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,JwtService jwtService) {
             this.appUserRepository = appUserRepository;
             this.passwordEncoder = passwordEncoder;
             this.authenticationManager = authenticationManager;
             this.jwtService = jwtService;
    }
    
    
    
    
    public AppUser register(AppUser user)
    {
    	
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
      
      return appUserRepository.save(user);
    }
    

     public String login(AppUser user) {

	   Authentication authentication    =authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            user.getPassword()
        )
    );
    
    String username = authentication.getName();
    
    AppUser user1 =appUserRepository.findByUsername(username)
    
    .orElseThrow(() -> new RuntimeException("User not found: " + username));
    
    return jwtService.generateToken(user1);
    

    
}

    
    
  
    
}
