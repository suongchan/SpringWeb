package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID", nullable = false)
    private Integer orderID;
    private LocalDate orderDate;
    private String customerName;
    private String customerAddress;
    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

}
