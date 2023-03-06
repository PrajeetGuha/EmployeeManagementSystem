package org.antwalk.ems.security;

import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.User;
import org.antwalk.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
    @Autowired
    private UserRepository userRepository;
	
	public boolean hasUserId(Authentication authentication, Long userId) throws UserNotFoundException {
        System.out.println(userId);
		User user = userRepository.findByUsername(authentication.getName()).orElseThrow(
            () -> new UserNotFoundException("The user is not found")
        );
        Long userID = user.getUserId();
		System.out.println(userId+"  "+userID);
            if(userID==userId)
            	return true;
            
            return false;
    }
       
}