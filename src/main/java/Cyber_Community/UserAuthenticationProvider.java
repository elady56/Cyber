package Cyber_Community;

import Cyber_Community.services.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name=authentication.getName();
        String password=authentication.getCredentials().toString();

        //User user =this.userService.getUser(this.userService.getID(authentication.getName()));

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
