package ru.itgirl.libraryproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class URepository{
    @PersistenceContext
    private EntityManager entityManager;

    public String[] getArrayFromColumn(String username) {
        TypedQuery<String[]> query = entityManager.createQuery("SELECT u.roles FROM Users u WHERE u.username = :usermane", String[].class);
        query.setParameter("username", username );
        return query.getSingleResult();
    }

}