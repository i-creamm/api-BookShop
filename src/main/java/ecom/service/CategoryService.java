package ecom.service;

import java.util.List;

import ecom.entity.Category;


public interface CategoryService {

    List<Category> getAllByStatus();

    Category findCategory(long id);

    Category toggleStatus(long id);

    Category saveCategory(Category category);

    void deleteCategory(long id);

}
