package ru.itgirl.libraryproject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.model.Author;
import ru.itgirl.libraryproject.model.Book;
import ru.itgirl.libraryproject.repository.AuthorRepository;
import ru.itgirl.libraryproject.service.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return convertToDto(author);
    }

    private AuthorDto convertToDto(Author author) {

        //get list of the books for the author
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : author.getBooks()) {
            //get genre of the book
            String nameOfGenre = book.getGenre().getName();

            //get name of the book
            String nameOfBook = book.getName();

            //get id of the book
            Long idOfBook = book.getId();

            //build book
            BookDto builtBook= BookDto.builder()
                    .genre(nameOfGenre)
                    .name(nameOfBook)
                    .id(idOfBook)
                    .build();

            bookDtoList.add(builtBook);
        }


        List<BookDto> bookDtoList_1 = author.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();


        return AuthorDto.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

}