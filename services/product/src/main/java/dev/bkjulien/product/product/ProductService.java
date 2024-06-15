package dev.bkjulien.product.product;

import java.util.List;

public interface ProductService {
    Integer createProduct(ProductRequest request);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests);

    ProductResponse getProduct(Integer productId);

    List<ProductResponse> getProducts(Integer productId);
}
