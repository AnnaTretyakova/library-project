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
import ru.itgirl.libraryproject.service.UserService;

import java.util.HashMap;


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
    public UserDetailsService users(){
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        HashMap<String, String> userData1 = userService.getInformationAboutUserAsHashMap(1L);
        HashMap<String, String> userData2 = userService.getInformationAboutUserAsHashMap(2L);
        UserDetails user1 = buildUserDetails(users, userData1);
        UserDetails user2 = buildUserDetails(users, userData2);
        return new InMemoryUserDetailsManager(user1, user2);

    }

    private static UserDetails buildUserDetails(User.UserBuilder users, HashMap<String, String> userData) {
        return users
                .username(userData.get("username"))
                .password(userData.get("passwords"))
                .roles(userData.get("roles"))
                .build();
    }
}
