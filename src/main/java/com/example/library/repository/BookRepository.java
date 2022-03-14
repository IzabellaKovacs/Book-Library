package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByAuthorAndName(String author, String name);
    Optional<Book> findBookByName(String name);
    Optional<Book> findBookByAuthor(String author);
}
