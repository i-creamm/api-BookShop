package ecom.controller;

import org.springframework.web.bind.annotation.*;

import ecom.entity.Product;
import ecom.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {
    
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getALlProduct() {
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getById(@PathVariable long id) {
        return ResponseEntity.ok(productService.findProduct(id));
    }

    @GetMapping("latest")
    public ResponseEntity<List<Product>> getLated() {
        return ResponseEntity.ok(productService.findAllProductLatest());
    }

    @GetMapping("bestseller")
    public ResponseEntity<List<Product>> getBestseller() {
        return ResponseEntity.ok(productService.findProductBestseller());
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable long id) {
        return ResponseEntity.ok(productService.findAllProductByCategory(id));
    }

//    @PostMapping
//    public ResponseEntity<Product> add (@RequestBody Product request){
//
//    }
    
}
