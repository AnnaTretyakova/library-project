package ru.itgirl.libraryproject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.libraryproject.dto.UserDto;
import ru.itgirl.libraryproject.model.Users;
import ru.itgirl.libraryproject.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/user")
    UserDto getByUserName(@RequestParam("username") String username){
        return userService.getByName(username);
    }

    @GetMapping("/roles")
    String getRolesByUserName(@RequestParam("username") String username){
        return userService.getRolesAsString(username);
    }
}
