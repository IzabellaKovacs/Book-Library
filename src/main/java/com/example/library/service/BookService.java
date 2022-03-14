package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public void addNewBook(Book book){
        Optional<Book> bookOptional = bookRepository.findBookByAuthorAndName(book.getAuthor(),book.getName());

        if(bookOptional.isPresent()){
            throw new IllegalStateException("The book with the given author exists");
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long id){
        boolean exists = bookRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("book with id " + id + "does not exists");
        }
        bookRepository.deleteById(id);
    }

    @Transactional
    public void updateAuthorBook(Long id, String author){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("book with id " + id + "does not exists"));

        if (author != null && author.length() > 0 )
            book.setAuthor(author);

    }

    public List<Book> searchBookByName(String name) {
        Optional<Book> bookOptional = bookRepository.findBookByName(name);
        if(bookOptional.isEmpty()){
            throw new IllegalStateException("book does not exists");
        }
        return bookOptional.stream().collect(Collectors.toList());
    }

    public List<Book> getAllBooksByAuthor(String author) {
        Optional<Book> authorOptional = bookRepository.findBookByAuthor(author);
        if(authorOptional.isEmpty()){
            throw new IllegalStateException("books does not exists with the given author");
        }

        return authorOptional.stream().collect(Collectors.toList());
    }
}
