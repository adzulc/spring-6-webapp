package io.dzul.spring6webapp.service;

import io.dzul.spring6webapp.domain.Book;
import io.dzul.spring6webapp.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }
}
