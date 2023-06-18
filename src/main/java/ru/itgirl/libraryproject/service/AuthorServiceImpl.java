package ru.itgirl.libraryproject.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.AuthorCreateDto;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.AuthorUpdateDto;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.model.Author;
import ru.itgirl.libraryproject.model.Book;
import ru.itgirl.libraryproject.repository.AuthorRepository;
import ru.itgirl.libraryproject.service.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getAuthorById(Long id) throws NoSuchFieldException {
        log.info("Try to find author by id {}", id);
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            AuthorDto authorDto = convertToDto(author.get());
            log.info("Author: {}", authorDto.toString());
            return authorDto;
        } else {
            log.error("Author with id {} not found", id);
            throw new NoSuchFieldException("No value present");
        }

    }

    @Override
    public List<AuthorDto> getByNameV1(String name) throws NoSuchFieldException {
        //Author author = authorRepository.findAuthorByName(name).orElseThrow();
        log.info("Try to find author by name {}", name);
        List<Author> authors = authorRepository.findAuthorByName(name);
        return getAuthorDtos(name, authors);

    }

    @Override
    public List<AuthorDto> getByNameV2(String name) throws NoSuchFieldException {
        //Author author = authorRepository.findAuthorByNameBySQL(name).orElseThrow();
        //return convertToDto(author);
        log.info("Try to find author by name {}", name);
        List<Author> authors = authorRepository.findAuthorByNameBySQL(name);
        return getAuthorDtos(name, authors);
    }

    @Override
    public List<AuthorDto> getByNameV3(String name) throws NoSuchFieldException {
        Specification<Author> specification = Specification.where(
                new Specification<Author>() {
                    @Override
                    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        return cb.equal(root.get("name"), name);
                    }
                }
        );
        log.info("Try to find author by name {}", name);
        List<Author> authors = authorRepository.findAll(specification);
        return getAuthorDtos(name, authors);
    }

    @Override
    public AuthorDto getByNameAndSurname(String name, String surname) throws NoSuchFieldException {
        log.info("Try to find author by name {} and surname {}", name, surname);
        Author author = authorRepository.findAuthorByNameAndSurname(name, surname);
        return getAuthorDto(name, surname, author);
    }

    @Override
    public AuthorDto getByNameAndSurnameSQL(String name, String surname) throws NoSuchFieldException {
        log.info("Try to find author by name {} and surname {}", name, surname);
        Author author = authorRepository.findAuthorByNameAndSurnameBySQL(name, surname);
        return getAuthorDto(name, surname, author);
    }

    @Override
    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto) {
        log.info("Try to create new author");
        Author initialAuthor = convertDtoToEntity(authorCreateDto);
        try {
            Author author = authorRepository.save(initialAuthor);
            AuthorDto authorDto = convertEntityToDto(author);
            log.info("Author {} was created", authorDto);
            return authorDto;
        } catch (Exception e) {
            log.error("Exception {} happened", e.getMessage());
            return null;
        }

    }

    @Override
    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto) {
        Long id = authorUpdateDto.getId();
        log.info("Try to update author with id {}", id);
        try {
            Author author = authorRepository.findById(id).orElseThrow();
            author.setName(authorUpdateDto.getName());
            author.setSurname(authorUpdateDto.getSurname());
            Author savedAuthor = authorRepository.save(author);
            AuthorDto authorDto = convertEntityToDto(savedAuthor);
            log.info("Author {} was updated", authorDto);
            return authorDto;
        } catch (NoSuchElementException e) {
            log.error("Author with id {} was not found", id);
        }
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {
        log.info("Try to delete author with id {}", id);
        try {
            authorRepository.deleteById(id);
            log.info("Author with id {} doesn't exist or was successfully deleted", id);
        } catch (Exception e) {
            log.error("During deletion error {} occurred", e.getMessage());
        }
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        log.info("Try to find all authors");
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> listAuthorDto = authors.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        if (listAuthorDto.isEmpty()) {
            log.warn("There is no authors");
        } else {
            log.info("All authors successfully were found");
        }
        return listAuthorDto;
    }

    private AuthorDto getAuthorDto(String name, String surname, Author author) throws NoSuchFieldException {
        if (author != null) {
            AuthorDto authorDto = convertToDto(author);
            log.info("Author: {}", authorDto.toString());
            return authorDto;
        } else {
            log.error("Author with name {} and surname {} not found", name, surname);
            throw new NoSuchFieldException("No value present");
        }
    }

    private List<AuthorDto> getAuthorDtos(String name, List<Author> authors) throws NoSuchFieldException {
        if (!authors.isEmpty()) {
            List<AuthorDto> authorDtoList = authors.stream().map(author -> convertToDto(author)).toList();
            log.info("List of authots: {}", authorDtoList.toString());
            return authorDtoList;
        } else {
            log.error("Author with name {} not found", name);
            throw new NoSuchFieldException("No value present");
        }
    }

    private Author convertDtoToEntity(AuthorCreateDto authorCreateDto) {
        return Author.builder()
                .name(authorCreateDto.getName())
                .surname(authorCreateDto.getSurname())
                .build();
    }

    private AuthorDto convertEntityToDto(Author author) {
        List<BookDto> bookDtoList = null;
        if (author.getBooks() != null) {
            bookDtoList = author.getBooks()
                    .stream()
                    .map(book -> BookDto.builder()
                            .genre(book.getGenre().getName())
                            .name(book.getName())
                            .id(book.getId())
                            .build())
                    .toList();
        }

        AuthorDto authorDto = AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .books(bookDtoList)
                .build();
        return authorDto;
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
            BookDto builtBook = BookDto.builder()
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