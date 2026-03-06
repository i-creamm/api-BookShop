package ecom.controller;

import ecom.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import ecom.entity.Product;
import ecom.service.ProductService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<Product> add (@Valid @RequestBody ProductDTO request){
        Product savProduct = productService.saveProduct(request);
        return new ResponseEntity<>(savProduct, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> change (@PathVariable long id, @RequestBody ProductDTO request){
        return ResponseEntity.ok(productService.updateProduct(id,request));
    }

    @PatchMapping("status/{id}")
    public ResponseEntity<?> changeStatus (@PathVariable long id){
        return ResponseEntity.ok(productService.toggleStatus(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
}
