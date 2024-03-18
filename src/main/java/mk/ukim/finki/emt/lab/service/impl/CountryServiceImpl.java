package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        Country country = countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        Country country = new Country(name,continent);

        countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Country country = countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);

        country.setName(name);
        country.setContinent(continent);

        countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> delete(Long id) {
        Country country = countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);

        countryRepository.deleteById(id);

        return Optional.of(country);
    }
}
