package service;

import converter.ProductConverter;
import entity.ProductEntity;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Transactional
    public List<Product> getAllProduct() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(ProductConverter::toModel)
                .collect(Collectors.toList());
    }
}
