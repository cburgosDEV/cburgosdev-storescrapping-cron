package cburgosdev.java.Services.Implements;

import cburgosdev.java.Constants.CategoryConstants;
import cburgosdev.java.Constants.RipleyConstants;
import cburgosdev.java.Constants.StoreConstants;
import cburgosdev.java.DTOs.ProductDTO;
import cburgosdev.java.Services.IProductService;
import cburgosdev.java.Services.IRipleyService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RipleyService implements IRipleyService {
    @Autowired
    private IProductService productService;
    @Override
    public void getSmartphones(WebClient webClient) {
        System.out.println("==============>>>>>>>>>>> INIT METHOD getSmartphones()  <<<<<<<<<<<==============");

        int pageSize = 3;

        for (int i = 1; i <= pageSize; i++) {
            getByPage(i, webClient, RipleyConstants.urlCellphones.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.SMARTPHONES);
        }
    }
    @Override
    public void getToys(WebClient webClient) {
        System.out.println("==============>>>>>>>>>>> INIT METHOD getToys()  <<<<<<<<<<<==============");

        int pageSize = 3;

        for (int i = 1; i <= pageSize; i++) {
            getByPage(i, webClient, RipleyConstants.urlPokemonToys.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.TOYS);
            getByPage(i, webClient, RipleyConstants.urlHotwheelsToys.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.TOYS);
            getByPage(i, webClient, RipleyConstants.urlBandaiToys.replace("{pageNumber}", Integer.toString(i)), CategoryConstants.TOYS);
        }
    }
    private void getByPage(int pageNumber, WebClient webClient, String urlCategory, int categoryId) {
        try {
            HtmlPage page = webClient.getPage(RipleyConstants.baseUrl + urlCategory);
            String pageTitle = page.getTitleText();
            String url = page.getUrl().toString();
            String baseUrl = page.getUrl().getProtocol() + "://" + page.getUrl().getHost();

            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " EXECUTION STARTED <<<<<<<<<<<=");
            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " TITLE: " + pageTitle);
            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " URL: " + url);
            System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " BASE URL: " + baseUrl);

            DomElement containerHtml = page.getFirstByXPath("//*[contains(concat(' ', @class, ' '), ' " + RipleyConstants.containerHtmlClass + " ')]");
            Iterable<DomElement> cardHtmlList = containerHtml.getChildElements();

            int productQuantity = 0;
            int productDetailQuantity = 0;
            for (DomElement cardHtml : cardHtmlList) {
                ProductDTO productBean = fillProductAttributes(cardHtml);
                if(!(productBean == null || productBean.getName().isEmpty())) {
                    System.out.println("=>>>>>>>>>>> PAGE " + pageNumber + " PRODUCT TO SAVE: " + productBean);

                    HashMap<String, Integer> saveProductResult = productService.saveProductAndDetail(productBean, StoreConstants.Ripley, categoryId);
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
            HtmlElement cardNameHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + RipleyConstants.cardNameHtmlClass + " ')]");
            HtmlElement cardPriceHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + RipleyConstants.cardPriceHtmlClass + " ')]");
            HtmlElement cardWithCardHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + RipleyConstants.cardPriceWithHtmlClass + " ')]");
            HtmlElement cardBrandHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + RipleyConstants.cardBrandHtmlClass + " ')]");
            HtmlElement cardDetailHrefHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + RipleyConstants.cardDetailHrefHtmlClass + " ')]");
            HtmlElement cardImageSrcHtml = cardHtml.getFirstByXPath(".//*[contains(concat(' ', @class, ' '), ' " + RipleyConstants.cardImageSrcHtmlClass + " ')]");

            productBean.setName(cardNameHtml == null ? "" : cardNameHtml.getFirstChild().getNodeValue());
            productBean.setPrice(cardPriceHtml == null ? "" : cardPriceHtml.getFirstChild().getNodeValue());
            productBean.setPriceWithCard(cardWithCardHtml == null ? "" : cardWithCardHtml.getFirstChild().getNodeValue());
            productBean.setBrand(cardBrandHtml == null ? "" : cardBrandHtml.getFirstChild().getFirstChild().getNodeValue());
            productBean.setDetailHref(cardDetailHrefHtml == null ? "" : cardDetailHrefHtml.getAttribute("href"));
            productBean.setImgSrc(cardImageSrcHtml == null ? "" : cardImageSrcHtml.getFirstElementChild().getAttribute("data-src"));

            return productBean;
        } catch (Exception e) {
            System.out.println("=>>>>>>>>>>> THERE WAS AN ERROR ON PRODUCT: " + cardHtml);
            return null;
        }
    }
}
