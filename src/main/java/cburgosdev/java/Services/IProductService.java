package cburgosdev.java.Services;

import cburgosdev.java.DTOs.ProductDTO;

import java.util.HashMap;

public interface IProductService {
    HashMap<String, Integer> saveProductAndDetail(ProductDTO productBean, Long storeId, Long categoryId);
}
