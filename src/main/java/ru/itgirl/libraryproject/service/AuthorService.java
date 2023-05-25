package ru.itgirl.libraryproject.service;

import ru.itgirl.libraryproject.dto.AuthorCreateDto;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.AuthorUpdateDto;

import java.util.List;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    List<AuthorDto> getByNameV1(String name);

    List<AuthorDto> getByNameV2(String name);

    List<AuthorDto> getByNameV3(String name);

    AuthorDto getByNameAndSurname(String name, String surname);

    AuthorDto getByNameAndSurnameSQL(String name, String surname);

    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto);

    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);

    public void deleteAuthor(Long id);

}
