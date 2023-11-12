package cburgosdev.java.Repositories;

import cburgosdev.java.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Product getProductByName(String name);
    List<Product> getProductListByName(String name);
}
