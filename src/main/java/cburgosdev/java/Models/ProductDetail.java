package cburgosdev.java.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "products_details")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private String price;
    private String priceWithCard;
    private String brand;
    private String detailHref;
    private String imgSrc;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceWithCard() {
        return priceWithCard;
    }

    public void setPriceWithCard(String priceWithCard) {
        this.priceWithCard = priceWithCard;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDetailHref() {
        return detailHref;
    }

    public void setDetailHref(String detailHref) {
        this.detailHref = detailHref;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "id=" + id +
                ", productId=" + productId +
                ", price='" + price + '\'' +
                ", priceWithCard='" + priceWithCard + '\'' +
                ", brand='" + brand + '\'' +
                ", detailHref='" + detailHref + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", date=" + date +
                '}';
    }
}
