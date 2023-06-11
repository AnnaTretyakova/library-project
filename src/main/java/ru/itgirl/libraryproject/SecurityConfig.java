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
import ru.itgirl.libraryproject.dto.UserTestDto;
import ru.itgirl.libraryproject.service.UserService;

import java.util.HashMap;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
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
    public UserDetailsService users() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserTestDto userFromDb1 = userService.getById(1L);
        UserTestDto userFromDb2 = userService.getById(2L);

        List<String> userRoles1 = userFromDb1.getRoles();
        String[] roles1 = new String[userRoles1.size()];

        UserDetails user1 = users
                .username(userFromDb1.getUsername())
                .password(userFromDb1.getPassword())
                .roles(userRoles1.toArray(roles1))
                .build();

        List<String> userRoles2 = userFromDb2.getRoles();
        String[] roles2 = new String[userRoles2.size()];

        UserDetails user2 = users
                .username(userFromDb2.getUsername())
                .password(userFromDb2.getPassword())
                .roles(userRoles2.toArray(roles2))
                .build();


        return new InMemoryUserDetailsManager(user1, user2);
    }
}
