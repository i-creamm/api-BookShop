package ecom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ecom.entity.Category;
import ecom.service.CategoryService;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllByStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) {
        return ResponseEntity.ok(categoryService.findCategory(id));
    }

    @PostMapping
    public ResponseEntity<Category> addCategory (@RequestBody Category category) {
        Category savCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savCategory, HttpStatus.CREATED);
    }
    
    @PatchMapping("status/{id}")
    public ResponseEntity<Category> changeStatus(@PathVariable long id) {        
        return ResponseEntity.ok(categoryService.toggleStatus(id));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    
}
