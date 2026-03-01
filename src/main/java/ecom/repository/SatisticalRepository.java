package ecom.repository;

import ecom.entity.Category;
import ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatisticalRepository extends JpaRepository<Product, Long> {

    @Query(value = "select sum(p.sold), c.name , (p.price * sum(p.sold) - (p.discount)*sum(p.sold)) ] \r\n"
            + "from categories c left join products p on c.id = p.id \r\n"
            + "group by c.name order by sum(p.sold) desc", nativeQuery = true)
    List<Object[]> getCategoryBestSeller();
}
