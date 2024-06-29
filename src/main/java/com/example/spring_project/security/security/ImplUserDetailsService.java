package com.example.spring_project.security.security;

import com.example.spring_project.security.repository.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplUserDetailsService implements UserDetailsService {

    private final SecurityUserRepository securityUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securityUserRepository.findByUsername(username)
                .map(AuthUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден!"));
    }

}
