package ru.itgirl.libraryproject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.*;
import ru.itgirl.libraryproject.model.Book;
import ru.itgirl.libraryproject.model.Genre;
import ru.itgirl.libraryproject.repository.GenreRepository;
import ru.itgirl.libraryproject.service.GenreService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
    }

    GenreDto convertToDto(Genre genre) {
        //get id of the genre
        Long idOfTheGenre = genre.getId();

        //get name of the genre
        String nameOfTheGenre = genre.getName();

        //get books with this genre
        List<BookWithoutGenreDto> bookDtoList = genre.getBooks()
                .stream()
                .map(book -> {
                    //get name of the book
                    String nameOfBook = book.getName();

                    //get id of the book
                    Long idOfBook = book.getId();

                    //get authors of the book
                    List<AuthorWithoutBooksDto> authorDtoList = book.getAuthors()
                            .stream()
                            .map(author -> AuthorWithoutBooksDto.builder()
                                    .id(author.getId())
                                    .name(author.getName())
                                    .surname(author.getSurname())
                                    .build()
                            ).toList();

                    //build book
                    return BookWithoutGenreDto.builder()
                            .id(idOfBook)
                            .name(nameOfBook)
                            .authors(authorDtoList)
                            .build();
                })
                .toList();

        return GenreDto.builder()
                .id(idOfTheGenre)
                .name(nameOfTheGenre)
                .books(bookDtoList)
                .build();


    }
}
