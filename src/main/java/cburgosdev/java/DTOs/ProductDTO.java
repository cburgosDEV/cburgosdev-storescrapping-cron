package cburgosdev.java.DTOs;

public class ProductDTO {
    String name;
    String price;
    String priceWithCard;
    String brand;
    String detailHref;
    String imgSrc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim().toUpperCase();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price.trim();
    }

    public String getPriceWithCard() {
        return priceWithCard;
    }

    public void setPriceWithCard(String priceWithCard) {
        this.priceWithCard = priceWithCard.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand.trim().toUpperCase();
    }

    public String getDetailHref() {
        return detailHref;
    }

    public void setDetailHref(String detailHref) {
        this.detailHref = detailHref.trim();
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc.trim();
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", priceWithCard='" + priceWithCard + '\'' +
                ", brand='" + brand + '\'' +
                ", detailHref='" + detailHref + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                '}';
    }
}
