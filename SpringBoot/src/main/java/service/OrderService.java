package service;

import converter.ProductConverter;
import entity.OrderDetailEntity;
import entity.OrderEntity;
import model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderDetailRepository;
import repository.OrderRepository;


import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderEntity addOrder(String name, String address) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerName(name);
        orderEntity.setOrderDate(LocalDate.now());
        orderEntity.setCustomerAddress(address);
        return orderRepository.save(orderEntity);
    }

    public void addOrderDetail(OrderEntity orderEntity, List<Cart> productList) {
        for(Cart cart : productList) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrder(orderEntity);
            orderDetailEntity.setProduct(ProductConverter.toEntity(cart.getProduct()));
            orderDetailEntity.setQuantity(cart.getQuantity());
            orderDetailRepository.save(orderDetailEntity);
        }
    }
}
