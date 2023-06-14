package ru.itgirl.libraryproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.RoleDto;
import ru.itgirl.libraryproject.dto.UserDto;
import ru.itgirl.libraryproject.dto.UserTestDto;
import ru.itgirl.libraryproject.model.Users;
import ru.itgirl.libraryproject.repository.UserRepository;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getByName(String username) {
        Users user = userRepository.findUsersByUsername(username).orElseThrow();
        return convertEntityToDto(user);
    }

    public UserTestDto getById(Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        return convertEntityToTestDto(user);
    }

    public HashMap<String, String> getInformationAboutUserAsHashMap(Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        HashMap<String, String> userData = new HashMap<>();
        userData.put("username", user.getUsername());
        userData.put("password", user.getPassword());
        userData.put("roles", getRolesAsString(user));
        return userData;
    }

    private List<String> getRolesAsStringList(Users user) {
        UserDto userDto = convertEntityToDto(user);
        return userDto.getRoles().stream().map(RoleDto::getRolename).toList();

    }
    private String getRolesAsString(Users user) {
        UserDto userDto = convertEntityToDto(user);
        List<String> roles = userDto.getRoles().stream().map(RoleDto::getRolename).toList();
        return roles.toString();
    }

    private UserDto convertEntityToDto(Users user) {
        List<RoleDto> rolesDto = user.getRoles().stream().
                map(role -> RoleDto.builder().
                        id(role.getId()).
                        rolename(role.getRolename()).
                        build()).
                toList();

        return UserDto.builder().
                id(user.getId()).
                username(user.getUsername()).
                password(user.getPassword()).
                roles(rolesDto).
                build();
    }

    private UserTestDto convertEntityToTestDto(Users user) {
        List<RoleDto> rolesDto = user.getRoles().stream().
                map(role -> RoleDto.builder().
                        id(role.getId()).
                        rolename(role.getRolename()).
                        build()).
                toList();

        return UserTestDto.builder().
                id(user.getId()).
                username(user.getUsername()).
                password(user.getPassword()).
                roles(rolesDto.stream().map(RoleDto::getRolename).toList()).
                build();
    }
}
