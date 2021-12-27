package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurtiyConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurtiyConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index" , "/css/*" , "/js/*")
                .permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails ahtishamAltaf = User.builder()
                .username("AHTISHAM ALTAF")
                .password(passwordEncoder.encode("1231"))
                .roles(ADMIN.name())
                .build();
        UserDetails aliAkbar = User.builder()
                .username("ALI AKBAR")
                .password(passwordEncoder.encode("122333"))
                .roles(STUDENT.name())
                .build();
        UserDetails tom = User.builder()
                .username("TOM")
                .password(passwordEncoder.encode("122333"))
                .roles(ADMINTRAINEE.name())
                .build();
        return new InMemoryUserDetailsManager(
                ahtishamAltaf,
                aliAkbar,
                tom
        );
    }
}
