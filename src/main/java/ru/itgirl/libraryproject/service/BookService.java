package ru.itgirl.libraryproject.service;

import ru.itgirl.libraryproject.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto getByNameV1(String name);
    BookDto getByNameV2(String name);
    BookDto getByNameV3(String name);
    List<BookDto> getAllBooks();
}
