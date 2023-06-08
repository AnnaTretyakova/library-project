package ru.itgirl.libraryproject;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.Transactional;
import ru.itgirl.libraryproject.model.Users;
import ru.itgirl.libraryproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository usersRepository;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/book").hasRole("USER")
                                .requestMatchers("/book/v2").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic();

        return http.build();
    }

    /*@Bean
    public UserDetailsService users() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/

    @Bean
    //@Transactional
    public UserDetailsService users() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        Users user1 = usersRepository.findUsersByUsername("manager");
        List<String> roles = usersRepository.findRolesByUsernameBySQL("manager");
        //Collection<String> roles = user1.getRoles();

        Users user2 = usersRepository.findUsersByUsername("admin");
        ArrayList<String> roles2 = usersRepository.findRolesByUsernameBySQL("admin");

        UserDetails user = users
                .username(usersRepository.findUsersByUsername("user").getUsername())
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


}
