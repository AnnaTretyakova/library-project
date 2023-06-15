package ru.itgirl.libraryproject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.libraryproject.dto.UserDto;
import ru.itgirl.libraryproject.dto.UserTestDto;
import ru.itgirl.libraryproject.service.UserService;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{id}")
    UserTestDto getByUserId(@PathVariable("id") Long id){
        return userService.getById(id);
    }

    @GetMapping("/user")
    UserDto getByUserName(@RequestParam("username") String username){
        return userService.getByName(username);
    }

    @GetMapping("/allUserInformation/{id}")
    HashMap<String, String> getInformationByUserId(@PathVariable("id") Long id){
        return userService.getInformationAboutUserAsHashMap(id);
    }
}
