package cburgosdev.java.Services.Implements;

import cburgosdev.java.Mappers.ProductDetailMapper;
import cburgosdev.java.Mappers.ProductMapper;
import cburgosdev.java.Models.Product;
import cburgosdev.java.Models.ProductDetail;
import cburgosdev.java.ProductBean;
import cburgosdev.java.Repositories.IProductDetailRepository;
import cburgosdev.java.Repositories.IProductRepository;
import cburgosdev.java.Services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IProductDetailRepository productDetailRepository;
    @Override
    public HashMap<String, Integer> saveProductAndDetail(ProductBean productBean, int storeId) {
        HashMap<String, Integer> result = new HashMap<>();
        result.put("productResult", 0);
        result.put("productDetailResult", 0);

        Product productFromDB = productRepository.getProductByName(productBean.getName());
        if(productFromDB == null) {
            //save product
            Product productToSave = ProductMapper.beanToModel(productBean, storeId);
            productRepository.save(productToSave);
            //save detail
            ProductDetail productDetailToSave = ProductDetailMapper.beanToModel(productBean, productToSave.getId());
            productDetailRepository.save(productDetailToSave);

            result.replace("productResult", 1);
            result.replace("productDetailResult", 1);
        } else {
            //save detail
            ProductDetail productDetailToSave = ProductDetailMapper.beanToModel(productBean, productFromDB.getId());
            productDetailRepository.save(productDetailToSave);

            result.replace("productDetailResult", 1);
        }

        return result;
    }
}
