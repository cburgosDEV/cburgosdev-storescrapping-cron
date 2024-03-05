package cburgosdev.java.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "products_records")
public class ProductRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private Double price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ProductRecord{" +
                "id=" + id +
                ", productId=" + productId +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
