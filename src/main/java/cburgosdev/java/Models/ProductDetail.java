package cburgosdev.java.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "products_details")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Double price;
    private Double priceWithCard;
    private Double minPrice;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceWithCard() {
        return priceWithCard;
    }

    public void setPriceWithCard(Double priceWithCard) {
        this.priceWithCard = priceWithCard;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
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
                ", price=" + price +
                ", priceWithCard=" + priceWithCard +
                ", minPrice=" + minPrice +
                ", date=" + date +
                '}';
    }
}
