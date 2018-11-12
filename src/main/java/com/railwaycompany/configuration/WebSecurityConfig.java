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
//        http.authorizeRequests()
//                .antMatchers("/admin-all-passengers").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/admin-all-routes").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/admin-all-stations").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/admin-all-trains").access("hasRole('ROLE_ADMIN')")
//
//                .and()
//                .formLogin().loginPage("/login").loginProcessingUrl("/loginAction")
//                .successForwardUrl("/account")
//                .failureUrl("/?error=true")//
//                .and()
//                .logout().logoutSuccessUrl("/login")
//                .and()
//                .csrf().disable();
//
//    }
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/admin-all-passengers", "/admin-all-routes", "/admin-all-stations", "/admin-all-trains")
                .access("hasRole('ROLE_ADMIN')")

                .and().formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .successForwardUrl("/account")
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
// Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
    }


}
