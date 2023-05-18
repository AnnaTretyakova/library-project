package ru.itgirl.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.itgirl.libraryproject.model.Author;
import ru.itgirl.libraryproject.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
    //Optional<Author> findAuthorByName(String name);
    List<Author> findAuthorByName(String name);

    Author findAuthorByNameAndSurname(String name, String surname);

    @Query(nativeQuery = true, value = "SELECT * FROM author WHERE name = ?")
    //Optional<Author> findAuthorByNameBySQL(String name);
    List<Author> findAuthorByNameBySQL(String name);


    @Query(nativeQuery = true, value = "SELECT * FROM author WHERE name = ? and surname = ?")
    Author findAuthorByNameAndSurnameBySQL(String name, String surname);
}
