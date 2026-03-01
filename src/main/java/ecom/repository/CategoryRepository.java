package ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecom.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    List<Category> findByStatusTrue();

    Optional<Category> findByIdAndStatusTrue(long id);

    boolean existsByName(String name);
}
