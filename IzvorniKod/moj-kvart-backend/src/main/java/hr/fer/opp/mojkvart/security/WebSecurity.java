package hr.fer.opp.mojkvart.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.authorizeRequests().antMatchers("/").permitAll();
        http.formLogin().successForwardUrl("/login/success")
                .failureForwardUrl("/login/failure");
        http.logout().logoutUrl("/logout").clearAuthentication(true);
        http.headers().frameOptions().sameOrigin(); // fixes h2-console problem
        http.csrf().disable();
    }
}
