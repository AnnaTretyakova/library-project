package ru.itgirl.libraryproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itgirl.libraryproject.model.Author;
import ru.itgirl.libraryproject.model.Users;

import java.util.ArrayList;
import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Author> {
    <Optional> Users findUsersByUsername(String userName);

    @Query(nativeQuery = true, value = "SELECT u.roles FROM users u WHERE u.username = ?")
    ArrayList<String> findRolesByUsernameBySQL(String username);

    /*

    @PersistenceContext
    EntityManager entityManager;

    public default String[] getArrayFromColumn(String username) {
        TypedQuery<String[]> query = entityManager.createQuery("SELECT u.roles FROM Users u WHERE u.username = :usermane", String[].class);
        query.setParameter("username", username );
        return query.getSingleResult();
    }*/

}
