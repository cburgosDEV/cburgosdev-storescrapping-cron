package cburgosdev.java.Repositories;

import cburgosdev.java.Models.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRecordRepository extends JpaRepository<ProductRecord, Long> {
}
