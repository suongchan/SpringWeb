package entity;

import converter.ProductConverter;
import jakarta.persistence.*;
import model.Cart;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartEntity {
    @Autowired
    private ProductRepository productRepository;
    private Map<Integer, Integer> productList = new HashMap<>();
    public void addItem(int productID) {
      ProductEntity productEntity =  productRepository.findById(productID).orElseThrow(() -> new RuntimeException("Product not found"));
      count(productEntity);
    }
    public int count(ProductEntity productEntity) {
        int productID = productEntity.getProductID();
        int quantity = productList.getOrDefault(productID, 0);
        productList.put(productID, quantity + 1);
        return productList.size();
    }

    public List<Cart> getProductList() {
        List<Cart> cartList = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : productList.entrySet()) {
            int productID = entry.getKey();
            int quantity = entry.getValue();
            ProductEntity productEntity = productRepository.findById(productID).orElseThrow(() -> new RuntimeException("Product not found"));
            Product product = ProductConverter.toModel(productEntity);
            Cart cart = new Cart(product, quantity);
            cartList.add(cart);
        }
        return cartList;
    }
    public void removeItem(int productID) {
        productList.remove(productID);
    }


}