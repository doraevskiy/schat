package ru.security.schat.pass.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecurityService extends UserDetailsService {

    UserDetails getUserByApiKey(String apiKey);
}
