package cburgosdev.java.Mappers;

import cburgosdev.java.Models.ProductDetail;
import cburgosdev.java.ProductBean;

import java.util.Date;

public class ProductDetailMapper {
    public static ProductDetail beanToModel(ProductBean productBean, int productId) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId(productId);
        productDetail.setPrice(productBean.getPrice());
        productDetail.setPriceWithCard(productBean.getPriceWithCard());
        productDetail.setBrand(productBean.getBrand());
        productDetail.setDetailHref(productBean.getDetailHref());
        productDetail.setImgSrc(productBean.getImgSrc());
        productDetail.setDate(new Date());

        return productDetail;
    }
}
