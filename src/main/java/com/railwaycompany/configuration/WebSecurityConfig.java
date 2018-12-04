package com.railwaycompany.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/admin-all-passengers",
                        "/admin-all-routes",
                        "/admin-all-stations",
                        "/admin-all-trains")
                .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")

                .antMatchers("/account",
                        "/buy-ticket-page")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_CLIENT')")

                .and().formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .successForwardUrl("/account")
                .failureUrl("/login?error=true")

                .usernameParameter("email")
                .passwordParameter("password")

                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
    }

}