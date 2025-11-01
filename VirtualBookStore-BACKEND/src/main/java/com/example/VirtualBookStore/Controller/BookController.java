package com.example.VirtualBookStore.Controller;

import com.example.VirtualBookStore.Entity.Book;
import com.example.VirtualBookStore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> find()
    {
        return bookService.findAll();
    }
    @GetMapping("/books/{booksId}")
    public Optional<Book> findById(@PathVariable Long booksId)
    {
        return bookService.findById(booksId);
    }
    @PostMapping("/books")
    public Book save(@RequestBody Book book)
    {
        return bookService.save(book);
    }
    @PutMapping("/books/{booksId}")
    public Optional<Book> updateById(@PathVariable Long booksId,@RequestBody Book book)
    {
        return bookService.updateById(booksId,book);
    }
    @DeleteMapping("/books/{booksId}")
    public Optional<Book> deleteById(@PathVariable Long booksId)
    {
        Optional<Book> db=bookService.findById(booksId);
        if(db==null)
        {
            throw new RuntimeException("Book not found");
        }
        else {
            bookService.deleteById(booksId);
        }
        return db;
    }
}
