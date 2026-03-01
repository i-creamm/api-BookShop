package ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ecom.entity.Category;
import ecom.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>  {
    
    List<Product> findByStatusTrue();

    List<Product> findByCategory(Category category);

    Optional<Product> findByIdAndStatusTrue(Long id);

    List<Product> findByStatusTrueOrderByEnteredDateDesc();

    List<Product> findByStatusTrueOrderBySoldDesc();

    List<Product> findByStatusTrueOrderByQuantityDesc();

}
