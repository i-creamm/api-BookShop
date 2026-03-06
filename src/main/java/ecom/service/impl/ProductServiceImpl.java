package ecom.service.impl;

import java.time.LocalDate;
import java.util.List;

import ecom.dto.ProductDTO;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;


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
    public Product saveProduct (ProductDTO productDTO){
        if(productRepository.existsByName(productDTO.getName())){
            throw new BadRequestException("Product name already exists!");
        }
        Product product = modelMapper.map(productDTO, Product.class);
        product.setStatus(true);
        product.setImage(null);
        product.setSold(0);
        product.setEnteredDate(LocalDate.now());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct (long id ,ProductDTO productDTO){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Not found Id")
        );
        modelMapper.map(productDTO, product);
        return productRepository.save(product);
    }

    @Override
    public Product toggleStatus (long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Not found Id")
        );
        product.setStatus(!product.isStatus());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct (long id){
        if(!productRepository.existsById(id)){
            throw new NotFoundException("Not found product with id: " + id);
        }
        productRepository.deleteById(id);
    }

}