package dev.bkjulien.product.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping(path = "/products") @RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping(path = "/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> requests
    ){
        return ResponseEntity.ok(service.purchaseProducts(requests));
    }

    @GetMapping(path = "/{product-id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(service.getProduct(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts( Integer productId){
        return ResponseEntity.ok(service.getProducts(productId));
    }
}
