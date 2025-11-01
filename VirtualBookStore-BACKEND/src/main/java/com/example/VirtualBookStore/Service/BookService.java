package com.example.VirtualBookStore.Service;

import com.example.VirtualBookStore.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    Optional<Book> updateById(Long id,Book book);
    Optional<Book> deleteById(Long id);
}
