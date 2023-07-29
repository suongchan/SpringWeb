package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;


@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;
    @Transactional
    public void addProductToCart(int productId, int quantity){

    }
}
