package com.assessment.sunbase.security;

import com.assessment.sunbase.model.Customer;
import com.assessment.sunbase.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private CustomerRepository userRepository;

    public UserDetailsServiceImpl(CustomerRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Customer user = null;
        try {
            user = userRepository.getUserByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("Could not find user");
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return new UserAuth(user);
    }
}
