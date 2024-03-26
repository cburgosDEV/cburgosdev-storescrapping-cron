package cburgosdev.java.Services.Implements;

import cburgosdev.java.Mappers.ProductDetailMapper;
import cburgosdev.java.Mappers.ProductMapper;
import cburgosdev.java.Models.Product;
import cburgosdev.java.Models.ProductDetail;
import cburgosdev.java.DTOs.ProductDTO;
import cburgosdev.java.Repositories.IProductDetailRepository;
import cburgosdev.java.Repositories.IProductRepository;
import cburgosdev.java.Services.IBrandService;
import cburgosdev.java.Services.IProductService;
import cburgosdev.java.Utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IProductDetailRepository productDetailRepository;
    @Autowired
    private IBrandService brandService;
    @Override
    public HashMap<String, Integer> saveProductAndDetail(ProductDTO productBean, Long storeId, Long categoryId) {
        HashMap<String, Integer> result = new HashMap<>();
        result.put("productResult", 0);
        result.put("productDetailResult", 0);

       try {
           Product productFromDB = productRepository.getProductByName(productBean.getName(), storeId);
           if(productFromDB == null) {
               Long brandId = brandService.getBrandId(productBean.getBrand(), categoryId);

               //save product
               Product productToSave = ProductMapper.beanToModel(productBean, storeId, brandId, categoryId);
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

               //update product
               productFromDB.setDiscountRate(CommonUtils.getDiscountRate(productDetailToSave.getMinPrice(), productFromDB.getDiscountRate(), productFromDB.getLastPriceChanged()));
               productFromDB.setLastPrice(productDetailToSave.getMinPrice());

               //seteamos el lastPriceChanged solo si el precio que viene del scrapping es diferente al lastPriceChanged de la BD
               if (!Objects.equals(productDetailToSave.getMinPrice(), productFromDB.getLastPriceChanged())) {
                   productFromDB.setLastPriceChanged(productDetailToSave.getMinPrice());
                   productFromDB.setIsHistoricalPrice(false);
               }

               //seteamos el historicalMinPrice solo si el precio que viene del scrapping es menor al historicalMinPrice de la BD
               if(productDetailToSave.getMinPrice() < productFromDB.getHistoricalMinPrice()) {
                   productFromDB.setHistoricalMinPrice(productDetailToSave.getMinPrice());
                   productFromDB.setIsHistoricalPrice(true);
               }

               productFromDB.setDetailHref(productBean.getDetailHref());
               productFromDB.setModifiedDate(new Date());
               productRepository.save(productFromDB);

               result.replace("productDetailResult", 1);
           }
       } catch (Exception e) {
           System.out.println("=>>>>>>>>>>> THERE WAS AN ERROR ON PRODUCT  " + productBean.toString());
       }

       return result;
    }
}
