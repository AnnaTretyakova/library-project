package ru.itgirl.libraryproject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.libraryproject.dto.AuthorCreateDto;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.AuthorUpdateDto;
import ru.itgirl.libraryproject.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class AuthorController {

    private final AuthorService authorService;


    @GetMapping("/author/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/author/v1")
    List<AuthorDto> getByNameV1(@RequestParam("name") String name){
        return authorService.getByNameV1(name);
    }

    @GetMapping("/author/v2")
    List<AuthorDto> getByNameV2(@RequestParam("name") String name){
        return authorService.getByNameV2(name);
    }

    @GetMapping("/author/v3")
    List<AuthorDto> getByNameV3(@RequestParam("name") String name){
        return authorService.getByNameV3(name);
    }

    @GetMapping("/author/v4") // http://localhost:8080/author?name=Лев&surname=Толстой
    AuthorDto getByNameAndSurnameV1(@RequestParam("name") String name, @RequestParam ("surname") String surname){
        return authorService.getByNameAndSurname(name, surname);
    }

    @GetMapping("/author/v5") // http://localhost:8080/author?name=Лев&surname=Толстой
    AuthorDto getByNameAndSurnameV2(@RequestParam("name") String name, @RequestParam ("surname") String surname){
        return authorService.getByNameAndSurnameSQL(name, surname);
    }

    //ToDo: написать v6 со спецификацией, где указаны и имя и фамилия автора

    @PostMapping("/author/create")
    AuthorDto createAuthor(@RequestBody AuthorCreateDto authorCreateDto) {
        return authorService.createAuthor(authorCreateDto);
    }
    @PutMapping("/author/update")
    AuthorDto updateAuthor(@RequestBody AuthorUpdateDto authorUpdateDto) {
        return authorService.updateAuthor(authorUpdateDto);
    }

    @DeleteMapping("/author/delete/{id}")
    void deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
    }
}
