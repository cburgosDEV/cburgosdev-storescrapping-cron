package cburgosdev.java.Mappers;

import cburgosdev.java.Models.Product;
import cburgosdev.java.DTOs.ProductDTO;
import cburgosdev.java.Utils.CommonUtils;
import cburgosdev.java.Utils.StringUtils;

import java.util.Date;

public class ProductMapper {
    public static Product beanToModel(ProductDTO productBean, Long storeId, Long brandId, Long categoryId) {
        Date today = new Date();
        Double price = productBean.getPrice().isEmpty() ? null : StringUtils.getPriceFromStringRipley(productBean.getPrice());
        Double priceWithCard = productBean.getPriceWithCard().isEmpty() ? null : StringUtils.getPriceFromStringRipley(productBean.getPriceWithCard());

        Product product = new Product();
        product.setName(productBean.getName().toUpperCase());
        product.setLastPrice(CommonUtils.getMinPrice(priceWithCard, price));
        product.setHistoricalMinPrice(CommonUtils.getMinPrice(priceWithCard, price));
        product.setDiscountRate(0.0);
        product.setLastPriceChanged(CommonUtils.getMinPrice(priceWithCard, price));
        product.setIsHistoricalPrice(false);
        product.setDetailHref(productBean.getDetailHref());
        product.setImgSrc(productBean.getImgSrc());
        product.setStoreId(storeId);
        product.setBrandId(brandId);
        product.setCategoryId(categoryId);
        product.setCreatedDate(today);
        product.setModifiedDate(today);

        return product;
    }
}
