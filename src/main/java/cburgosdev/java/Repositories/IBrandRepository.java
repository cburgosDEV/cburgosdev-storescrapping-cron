package cburgosdev.java.Repositories;

import cburgosdev.java.Models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<Brand, Long> {
    Brand getBrandByName(String name);
}
