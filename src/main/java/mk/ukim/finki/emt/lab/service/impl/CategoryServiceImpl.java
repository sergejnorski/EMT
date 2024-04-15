package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> listAllCategories() {
        return Arrays.asList(Category.values());
    }
}
