package cburgosdev.java.Constants;

public class OechsleConstants {
    public static String baseUrl = "https://busca.oechsle.pe/";

    //HTML CLASS
    public static String containerHtmlClass = "biggy-search__products__list";
    public static String cardNameHtmlClass = "prod-name";
    public static String cardPriceHtmlClass = "BestPrice";
    public static String cardPriceWithHtmlClass = "OhPrice";
    public static String cardBrandHtmlClass = "brand";
    public static String cardDetailHrefHtmlClass = "prod-image";
    public static String cardImageSrcHtmlClass = "prod-img";

    //CATEGORIES - SMARTPHONES
    public static String urlSamsungCellphones = "search/sub-categoria/celulares?query=samsung&operator=and&fuzzy=0&page={pageNumber}";
    public static String urlMotorolaCellphones = "search/sub-categoria/celulares?query=motorola&operator=and&fuzzy=0&page={pageNumber}";
    public static String urlXiaomiCellphones = "search/sub-categoria/celulares?query=xiaomi&operator=and&fuzzy=0&page={pageNumber}";
    public static String urlHuaweiCellphones = "search/sub-categoria/celulares?query=huawei&operator=and&fuzzy=0&page={pageNumber}";

    //CATEGORIES - TOYS
    public static String urlPokemonToys = "search/categoria/jugueteria?query=pokemon&operator=and&fuzzy=0&page={pageNumber}";
    public static String urlHotwheelsToys = "search/categoria/jugueteria?query=hotwheels&operator=and&fuzzy=0&page={pageNumber}";
    public static String urlBandaiToys = "search/categoria/jugueteria?query=bandai&operator=and&fuzzy=0&page={pageNumber}";
    public static String urlLegoToys = "search/categoria/jugueteria?query=lego&operator=and&fuzzy=0&page={pageNumber}";
}
