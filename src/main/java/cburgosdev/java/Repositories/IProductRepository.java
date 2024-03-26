package cburgosdev.java.Repositories;

import cburgosdev.java.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.storeId = :storeId")
    Product getProductByName(String name, Long storeId);
    List<Product> getProductListByName(String name);
}
