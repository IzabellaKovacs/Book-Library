package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/allBooks")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/allBooksByAuthor/{author}")
    public List<Book> getAllBooksByAuthor(@PathVariable("author") String author){
       return bookService.getAllBooksByAuthor(author);
    }

    @GetMapping(path = "/book/{name}")
    public List<Book> searchBookByName(@PathVariable("name") String name){
        return bookService.searchBookByName(name);
    }

    @PostMapping
    public void addNewBook(@RequestBody Book book){
        bookService.addNewBook(book);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
    }

    @PutMapping(path = "/{id}")
    public void updateAuthorBook(@PathVariable("id") Long id,
                                 @RequestParam(required = true) String author){
        bookService.updateAuthorBook(id, author);
    }
}
