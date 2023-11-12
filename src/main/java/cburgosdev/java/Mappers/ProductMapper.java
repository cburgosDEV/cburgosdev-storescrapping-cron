package cburgosdev.java.Mappers;

import cburgosdev.java.Models.Product;
import cburgosdev.java.ProductBean;

public class ProductMapper {
    public static Product beanToModel(ProductBean productBean, int storeId) {
        Product product = new Product();
        product.setName(productBean.getName());
        product.setStoreId(storeId);
        return product;
    }
}
