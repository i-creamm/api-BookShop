package ecom.service;

import java.util.List;

import ecom.entity.Product;

public interface ProductService {
    
    List<Product> findAllProduct();

    Product findProduct(long id);

    List<Product> findAllProductByCategory(long id);

    List<Product> findAllProductLatest();

    List<Product> findProductBestseller();

    void save (Product product);
}
