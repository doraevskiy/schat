package ru.security.schat.pass.dao.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import ru.security.schat.pass.dto.security.tokens.RESTAuthenticationToken;
import ru.security.schat.pass.dto.security.tokens.RESTCredentials;
import ru.security.schat.pass.service.security.UserSecurityService;

import java.text.MessageFormat;

public class RESTDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(RESTDaoAuthenticationProvider.class);

    private UserSecurityService userSecurityService;
    private PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        // Get credentials from token
        RESTAuthenticationToken token = (RESTAuthenticationToken) usernamePasswordAuthenticationToken;
        if (token != null) {
            if (usernamePasswordAuthenticationToken.getCredentials() == null) {
                LOGGER.debug("Authentication failed: no credentials provided");

                throw new BadCredentialsException(
                        messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",
                                "Bad credentials"));
            }

            RESTCredentials credentials = (RESTCredentials) usernamePasswordAuthenticationToken.getCredentials();

            LOGGER.info("PASSWORD: {}", userDetails.getPassword());

            if (!passwordEncoder.matches(credentials.getRawPassword(), userDetails.getPassword())) {
                LOGGER.debug("Authentication failed: password does not match stored value");

                throw new BadCredentialsException(
                        messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",
                                "Bad credentials"));
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException(MessageFormat.format("Expected Authentication Token object of type {0}, but instead received {1}",
                    RESTAuthenticationToken.class.getSimpleName(), usernamePasswordAuthenticationToken.getClass().getSimpleName()));
        }
    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return ;
    }

    @Override
    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userSecurityService, "A UserSecurityServiceImpl must be set");
        Assert.notNull(this.passwordEncoder, "A PasswordEncoder must be set");
    }

    public UserSecurityService getUserSecurityService() {
        return userSecurityService;
    }

    public void setUserSecurityService(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
