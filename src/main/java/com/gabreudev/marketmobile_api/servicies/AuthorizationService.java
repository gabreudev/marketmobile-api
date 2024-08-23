package com.gabreudev.marketmobile_api.servicies;
import com.gabreudev.marketmobile_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUSerByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow( ()-> new UsernameNotFoundException(String.format("usuario com e emial %s não foi encontrado", email)));
    }
}