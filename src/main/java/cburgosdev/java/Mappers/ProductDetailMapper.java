package cburgosdev.java.Mappers;

import cburgosdev.java.Models.ProductDetail;
import cburgosdev.java.DTOs.ProductDTO;
import cburgosdev.java.Utils.CommonUtils;
import cburgosdev.java.Utils.StringUtils;

import java.util.Date;

public class ProductDetailMapper {
    public static ProductDetail beanToModel(ProductDTO productBean, int productId) {
        Double price = productBean.getPrice().isEmpty() ? null : StringUtils.getPriceFromStringRipley(productBean.getPrice());
        Double priceWithCard = productBean.getPriceWithCard().isEmpty() ? null : StringUtils.getPriceFromStringRipley(productBean.getPriceWithCard());

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId(productId);
        productDetail.setPrice(price);
        productDetail.setPriceWithCard(priceWithCard);
        productDetail.setMinPrice(CommonUtils.getMinPrice(priceWithCard, price));
        productDetail.setDate(new Date());

        return productDetail;
    }
}
