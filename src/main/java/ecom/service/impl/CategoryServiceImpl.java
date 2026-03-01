package ecom.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import ecom.entity.Category;
import ecom.exception.BadRequestException;
import ecom.exception.NotFoundException;
import ecom.repository.CategoryRepository;
import ecom.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllByStatus() {
        return categoryRepository.findByStatusTrue();
    }

    @Override
    public Category findCategory (long id){
        return categoryRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BadRequestException("đã có lỗi " + id));
    }

    @Override
    public Category toggleStatus (long id){
        Category category =  categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("khong tim thay id" + id));
        category.setStatus(!category.isStatus());
        return categoryRepository.save(category);
    }

    @Override
    public Category saveCategory (Category category){
        if(categoryRepository.existsByName(category.getName())){
            throw new BadRequestException("Category name already exists!");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory (long id){
        if(!categoryRepository.existsById(id)){
            throw new NotFoundException("Không tìm thấy Category với ID: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
