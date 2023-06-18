package ru.itgirl.libraryproject;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.libraryproject.dto.AuthorCreateDto;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.AuthorUpdateDto;
import ru.itgirl.libraryproject.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "library-users")

public class AuthorController {

    private final AuthorService authorService;


    @SneakyThrows
    @GetMapping("/author/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @SneakyThrows
    @GetMapping("/author/v1")
    List<AuthorDto> getByNameV1(@RequestParam("name") String name) {
        return authorService.getByNameV1(name);
    }

    @SneakyThrows
    @GetMapping("/author/v2")
    List<AuthorDto> getByNameV2(@RequestParam("name") String name) {
        return authorService.getByNameV2(name);
    }

    @SneakyThrows
    @GetMapping("/author/v3")
    List<AuthorDto> getByNameV3(@RequestParam("name") String name) {
        return authorService.getByNameV3(name);
    }

    @SneakyThrows
    @GetMapping("/author/v4")
        // http://localhost:8080/author?name=Лев&surname=Толстой
    AuthorDto getByNameAndSurnameV1(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return authorService.getByNameAndSurname(name, surname);
    }

    @SneakyThrows
    @GetMapping("/author/v5")
        // http://localhost:8080/author?name=Лев&surname=Толстой
    AuthorDto getByNameAndSurnameV2(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return authorService.getByNameAndSurnameSQL(name, surname);
    }

    //ToDo: написать v6 со спецификацией, где указаны и имя и фамилия автора

    @PostMapping("/author/create")
    AuthorDto createAuthor(@RequestBody @Valid AuthorCreateDto authorCreateDto) {
        return authorService.createAuthor(authorCreateDto);
    }

    @PutMapping("/author/update")
    AuthorDto updateAuthor(@RequestBody @Valid AuthorUpdateDto authorUpdateDto) {
        return authorService.updateAuthor(authorUpdateDto);
    }

    @DeleteMapping("/author/delete/{id}")
    void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }
}
