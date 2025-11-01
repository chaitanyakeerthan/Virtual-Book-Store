package com.example.VirtualBookStore.Service;

import com.example.VirtualBookStore.Entity.Book;
import com.example.VirtualBookStore.Repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Optional<Book> updateById(Long id, Book book) {
        Optional<Book> existing=bookRepository.findById(id);
        if(existing.isPresent())
        {
            book.setId(id);
            return Optional.of(bookRepository.save(book));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Book> deleteById(Long id) {
        Optional<Book> exist=bookRepository.findById(id);
        if(exist.isPresent())
        {
            bookRepository.deleteById(id);
        }
        return exist;
    }
}
