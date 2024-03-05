package cburgosdev.java.Repositories;

import cburgosdev.java.Models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Query("SELECT pd.productId, MIN(pd.minPrice) AS price_record, DATE(pd.date) AS date_record " +
            "FROM ProductDetail pd " +
            "WHERE DATE(pd.date) = :dateToSearch " +
            "GROUP BY pd.productId, DATE(pd.date)")
    List<Object[]> findDetailForRecord(@Param("dateToSearch") LocalDate dateToSearch);
}
