package cburgosdev.java.Services.Implements;

import cburgosdev.java.Constants.CategoryConstants;
import cburgosdev.java.Constants.OechsleConstants;
import cburgosdev.java.Constants.StoreConstants;
import cburgosdev.java.DTOs.ProductDTO;
import cburgosdev.java.Services.IOechsleService;
import cburgosdev.java.Services.IProductService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class OechsleService implements IOechsleService {
    @Autowired
    private IProductService productService;
    @Override
    public void getSmartphones(WebClient webClient) throws IOException {
        System.out.println("==============>>>>>>>>>>> INIT METHOD getSmartphones()  <<<<<<<<<<<==============");

        int pageSize = 2;

        for (int i = 1; i <= pageSize; i++) {
            getByPage(i, webClient, OechsleConstants.urlSamsungCellphones.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.SMARTPHONES);
            getByPage(i, webClient, OechsleConstants.urlMotorolaCellphones.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.SMARTPHONES);
            getByPage(i, webClient, OechsleConstants.urlXiaomiCellphones.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.SMARTPHONES);
            getByPage(i, webClient, OechsleConstants.urlHuaweiCellphones.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.SMARTPHONES);
        }
    }
    @Override
    public void getToys(WebClient webClient) {
        System.out.println("==============>>>>>>>>>>> INIT METHOD getToys()  <<<<<<<<<<<==============");

        int pageSize = 2;

        for (int i = 1; i <= pageSize; i++) {
            getByPage(i, webClient, OechsleConstants.urlPokemonToys.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.TOYS);
            getByPage(i, webClient, OechsleConstants.urlHotwheelsToys.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.TOYS);
            getByPage(i, webClient, OechsleConstants.urlBandaiToys.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.TOYS);
            getByPage(i, webClient, OechsleConstants.urlLegoToys.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.TOYS);
        }
    }
    private void getByPage(int pageNumber, WebClient webClient, String urlCategory, Long categoryId) {
        try {
            HtmlPage page = webClient.getPage(OechsleConstants.baseUrl + urlCategory);
            String pageTitle = page.getTitleText();
            String url = page.getUrl().toString();
            String baseUrl = page.getUrl().getProtocol() + "://" + page.getUrl().getHost();

            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " EXECUTION STARTED <<<<<<<<<<<=");
            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " TITLE: " + pageTitle);
            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " URL: " + url);
            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " BASE URL: " + baseUrl);

            HtmlElement containerHtml = page.getFirstByXPath("//ul[@class='" + OechsleConstants.containerHtmlClass + "']");
            Iterable<HtmlElement> cardHtmlList = containerHtml.getElementsByTagName("li");

            int productQuantity = 0;
            int productDetailQuantity = 0;
            for (DomElement cardHtml : cardHtmlList) {
                ProductDTO productBean = fillProductAttributes(cardHtml);
                if(!(productBean == null || productBean.getName().isEmpty())) {
                    System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " CATEGORY " + categoryId + " PRODUCT TO SAVE: " + productBean);

                    HashMap<String, Integer> saveProductResult = productService.saveProductAndDetail(productBean, StoreConstants.OECHSLE, categoryId);
                    productQuantity += saveProductResult.get("productResult");
                    productDetailQuantity += saveProductResult.get("productDetailResult");
                }
            }

            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " NEW PRODUCT LIST SIZE: " + productQuantity);
            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " NEW PRODUCT LIST DETAIL SIZE: " + productDetailQuantity);
            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " EXECUTION FINISHED <<<<<<<<<<<=");
        } catch (Exception e) {
            System.out.println("=>>>>>>>>>>> THERE WAS AN ERROR ON URL  " + urlCategory + " PAGE: " + pageNumber);
        }
    }
    private ProductDTO fillProductAttributes(DomElement cardHtml) {
        try {
            ProductDTO productBean = new ProductDTO();

            HtmlElement cardNameHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + OechsleConstants.cardNameHtmlClass + " ')]");
            if(cardNameHtml == null) return null;
            HtmlElement cardPriceHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + OechsleConstants.cardPriceHtmlClass + " ')]");
            HtmlElement cardWithCardHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + OechsleConstants.cardPriceWithHtmlClass + " ')]");
            HtmlElement cardBrandHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + OechsleConstants.cardBrandHtmlClass + " ')]");
            HtmlElement cardDetailHrefHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + OechsleConstants.cardDetailHrefHtmlClass + " ')]");
            HtmlElement cardImageSrcHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + OechsleConstants.cardImageSrcHtmlClass + " ')]");

            productBean.setName(cardNameHtml.getFirstChild().getNodeValue());
            productBean.setPrice(cardPriceHtml == null ? "" : cardPriceHtml.getFirstChild().getNodeValue());
            productBean.setPriceWithCard(cardWithCardHtml == null ? "" : cardWithCardHtml.getFirstChild().getNodeValue());
            productBean.setBrand(cardBrandHtml == null ? "" : cardBrandHtml.getFirstChild().getNodeValue());
            productBean.setDetailHref(cardDetailHrefHtml == null ? "" : cardDetailHrefHtml.getAttribute("href"));
            productBean.setImgSrc(cardImageSrcHtml == null ? "" : cardImageSrcHtml.getFirstChild().getNextElementSibling().getAttribute("src"));

            return productBean;
        } catch (Exception e) {
            System.out.println("=>>>>>>>>>>> THERE WAS AN ERROR ON PRODUCT: " + cardHtml);
            return null;
        }
    }
}
