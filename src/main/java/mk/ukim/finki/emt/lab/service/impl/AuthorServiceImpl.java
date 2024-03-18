package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(String name, String surname, Long countryId) {
        Country country = countryRepository.findById(countryId).orElseThrow(InvalidCountryIdException::new);
        Author author = new Author(name,surname,country);

        authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, Long countryId) {
        Author author = authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);
        Country country = countryRepository.findById(countryId).orElseThrow(InvalidCountryIdException::new);

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);

        authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> delete(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);

        authorRepository.deleteById(id);

        return Optional.of(author);
    }
}
