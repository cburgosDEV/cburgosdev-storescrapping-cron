package cburgosdev.java;

public class ProductBean {
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
        this.name = name;
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
