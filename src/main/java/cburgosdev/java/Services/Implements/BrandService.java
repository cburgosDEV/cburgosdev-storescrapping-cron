package cburgosdev.java.Services.Implements;

import cburgosdev.java.Models.Brand;
import cburgosdev.java.Repositories.IBrandRepository;
import cburgosdev.java.Services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public int getBrandId(String name) {
        Brand brand = brandRepository.getBrandByName(name.toUpperCase());

        if(brand == null) {
            brand = new Brand();
            brand.setName(name);
            brandRepository.save(brand);

            return brand.getId();
        }

        return brand.getId();
    }
}
