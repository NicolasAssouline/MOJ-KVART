package hr.fer.opp.mojkvart.security;

import hr.fer.opp.mojkvart.domain.Administrator;
import hr.fer.opp.mojkvart.domain.Councilor;
import hr.fer.opp.mojkvart.domain.Moderator;
import hr.fer.opp.mojkvart.domain.User;
import hr.fer.opp.mojkvart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username + " does not exist.")
        );

        return new org.springframework.security.core.userdetails.User(
                username, password(user), authorities(user)
        );
    }

    private String password(User user) {
        return user.getPasswordHash();
    }

    private Collection<? extends GrantedAuthority> authorities(User user) {
        if (user instanceof Administrator) {
            return commaSeparatedStringToAuthorityList(
                    "ROLE_ADMIN"
            );
        } else if (user instanceof Councilor) {
            return commaSeparatedStringToAuthorityList(
                    "ROLE_RESIDENT, ROLE_COUNCILOR"
            );
        } else if (user instanceof Moderator) {
            return commaSeparatedStringToAuthorityList(
                    "ROLE_RESIDENT, ROLE_MODERATOR"
            );
        } else {
            return commaSeparatedStringToAuthorityList(
                    "ROLE_RESIDENT"
            );
        }
    }
}
