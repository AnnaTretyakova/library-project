package ru.itgirl.libraryproject.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public List<AuthorDto> getByNameV1(String name){
        //Author author = authorRepository.findAuthorByName(name).orElseThrow();
        List<Author> authors = authorRepository.findAuthorByName(name);
        return authors.stream().map(author -> convertToDto(author)).toList();
    }

    @Override
    public List<AuthorDto> getByNameV2(String name){
        //Author author = authorRepository.findAuthorByNameBySQL(name).orElseThrow();
        //return convertToDto(author);
        List<Author> authors = authorRepository.findAuthorByNameBySQL(name);
        return authors.stream().map(author -> convertToDto(author)).toList();

    }

    @Override
    public List<AuthorDto> getByNameV3(String name){
        Specification<Author> specification = Specification.where(
                new Specification<Author>() {
                    @Override
                    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        return cb.equal(root.get("name"),name);
                    }
                }
        );
        List<Author> authors = authorRepository.findAll(specification);
        return authors.stream().map(author -> convertToDto(author)).toList();
    }

    @Override
    public AuthorDto getByNameAndSurname(String name, String surname){
        Author author = authorRepository.findAuthorByNameAndSurname(name, surname);
        return convertToDto(author);
    }

    @Override
    public AuthorDto getByNameAndSurnameSQL(String name, String surname){
        Author author = authorRepository.findAuthorByNameAndSurnameBySQL(name, surname);
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