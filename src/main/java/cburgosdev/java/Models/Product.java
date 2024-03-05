package cburgosdev.java.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double lastPrice;
    private Double historicalMinPrice;
    private Double discountRate;
    private Double lastPriceChanged;
    private Boolean isHistoricalPrice;
    private String detailHref;
    private String imgSrc;
    private int brandId;
    private int storeId;
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getHistoricalMinPrice() {
        return historicalMinPrice;
    }

    public void setHistoricalMinPrice(Double historicalMinPrice) {
        this.historicalMinPrice = historicalMinPrice;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public Double getLastPriceChanged() {
        return lastPriceChanged;
    }

    public void setLastPriceChanged(Double lastPriceChanged) {
        this.lastPriceChanged = lastPriceChanged;
    }

    public Boolean getIsHistoricalPrice() {
        return isHistoricalPrice;
    }

    public void setIsHistoricalPrice(Boolean isHistoricalPrice) {
        this.isHistoricalPrice = isHistoricalPrice;
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

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastPrice=" + lastPrice +
                ", historicalMinPrice=" + historicalMinPrice +
                ", discountRate=" + discountRate +
                ", lastPriceChanged=" + lastPriceChanged +
                ", isHistoricalPrice=" + isHistoricalPrice +
                ", detailHref='" + detailHref + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", brandId=" + brandId +
                ", storeId=" + storeId +
                ", categoryId=" + categoryId +
                '}';
    }
}
