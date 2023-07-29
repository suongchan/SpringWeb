package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orderDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailID;
    @ManyToOne
    @JoinColumn(name = "orderID")
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductEntity product;
    @Column(name = "quantity")
    private Integer quantity;
}
