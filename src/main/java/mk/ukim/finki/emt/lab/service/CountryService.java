package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAllCountries();
    Optional<Country> findById(Long id);
    Optional<Country> create(String name, String continent);
    Optional<Country> update(Long id, String name, String continent);
    Optional<Country> delete(Long id);
}
