package ru.itgirl.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.libraryproject.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    <Optional>Users findUsersByUsername(String userName);
}
