package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.model.exceptions.NoAvailableCopiesException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAllBooks();
    Optional<Book> findById(Long id);
    Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> delete(Long id);
    Optional<Book> lowerAvailableCopies(Long id) throws NoAvailableCopiesException;
}
