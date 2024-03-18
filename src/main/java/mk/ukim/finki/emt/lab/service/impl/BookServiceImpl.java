package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.emt.lab.model.exceptions.NoAvailableCopiesException;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId).orElseThrow(InvalidAuthorIdException::new);
        Book book = new Book(name,category,author,availableCopies);

        bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId).orElseThrow(InvalidAuthorIdException::new);
        Book book = bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);

        bookRepository.deleteById(id);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> lowerAvailableCopies(Long id) throws NoAvailableCopiesException {
        Book book = bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);

        if(book.getAvailableCopies() == 0){
            throw new NoAvailableCopiesException();
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        bookRepository.save(book);

        return Optional.of(book);
    }
}
