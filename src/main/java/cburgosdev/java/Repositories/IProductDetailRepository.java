package cburgosdev.java.Repositories;

import cburgosdev.java.Models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
