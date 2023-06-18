package ru.itgirl.libraryproject.service;

import ru.itgirl.libraryproject.dto.AuthorCreateDto;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.AuthorUpdateDto;
import ru.itgirl.libraryproject.dto.BookDto;

import java.util.List;

public interface AuthorService {
    AuthorDto getAuthorById(Long id) throws NoSuchFieldException;

    List<AuthorDto> getByNameV1(String name) throws NoSuchFieldException;

    List<AuthorDto> getByNameV2(String name) throws NoSuchFieldException;

    List<AuthorDto> getByNameV3(String name) throws NoSuchFieldException;

    AuthorDto getByNameAndSurname(String name, String surname) throws NoSuchFieldException;

    AuthorDto getByNameAndSurnameSQL(String name, String surname) throws NoSuchFieldException;

    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto);

    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);

    public void deleteAuthor(Long id);

    List<AuthorDto> getAllAuthors();

}
