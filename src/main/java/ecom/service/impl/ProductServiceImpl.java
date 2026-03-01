package ecom.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ecom.entity.Category;
import ecom.entity.Product;
import ecom.repository.CategoryRepository;
import ecom.repository.ProductRepository;
import ecom.exception.BadRequestException;
import ecom.exception.NotFoundException;
import ecom.service.ProductService;
import lombok.RequiredArgsConstructor;

import javax.sound.sampled.Port;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findByStatusTrue();
    }

    @Override
    public Product findProduct(long id){
        return productRepository.findByIdAndStatusTrue(id).orElseThrow(() ->
                new BadRequestException("not found" + id)
        );
    }

    @Override
    public List<Product> findAllProductLatest(){
        return productRepository.findByStatusTrueOrderByEnteredDateDesc();
    }

    @Override
    public List<Product> findProductBestseller() { return productRepository.findByStatusTrueOrderBySoldDesc(); }

    @Override
    public List<Product> findAllProductByCategory (long id) {
        if(!categoryRepository.existsById(id)){
            throw new NotFoundException("not found " + id);
        }
        Category category = categoryRepository.findById(id).get();
        return productRepository.findByCategory(category);
    }

    @Override
    public void save (Product product){
        return;
    }

}
