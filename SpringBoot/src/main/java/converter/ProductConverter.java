package converter;


import entity.ProductEntity;
import model.Product;

public class ProductConverter {
    public static Product toModel(ProductEntity entity) {
        Product product = new Product();
        product.setProductID(entity.getProductID());
        product.setName(entity.getProductName());
        product.setPrice(entity.getUnitPrice());
        return product;
    }
    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setProductID(product.getProductID());
        entity.setProductName(product.getName());
        entity.setUnitPrice(product.getPrice());
        return entity;
    }
}
