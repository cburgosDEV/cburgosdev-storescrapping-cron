package cburgosdev.java.Services;

import cburgosdev.java.ProductBean;

import java.util.HashMap;

public interface IProductService {
    HashMap<String, Integer> saveProductAndDetail(ProductBean productBean, int storeId);
}
