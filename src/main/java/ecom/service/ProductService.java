package ecom.service;

import java.util.List;

import ecom.dto.ProductDTO;
import ecom.entity.Product;

public interface ProductService {
    
    List<Product> findAllProduct();

    Product findProduct(long id);

    List<Product> findAllProductByCategory(long id);

    List<Product> findAllProductLatest();

    List<Product> findProductBestseller();

    Product saveProduct (ProductDTO productDTO);

    Product updateProduct (long id, ProductDTO productDTO);

    Product toggleStatus (long id);

    void deleteProduct (long id);
}
