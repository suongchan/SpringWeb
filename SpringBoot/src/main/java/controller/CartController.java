package controller;

import entity.CartEntity;
import entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.OrderService;


@Controller
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartController {
    @Autowired
    private CartEntity cartEntity;
    @Autowired
    private OrderService orderService;
    @PostMapping("/addToCart")
    public String addToCart(@RequestParam int productID) {
        cartEntity.addItem(productID);
        return "redirect:/product/list";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartEntity.getProductList());
        return "cart";
    }
    @PostMapping("/remove")
    public String remove(@RequestParam int productID) {
        cartEntity.removeItem(productID);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("cartItems", cartEntity.getProductList());
        model.addAttribute("total", cartEntity.getProductList().stream().mapToInt(cart -> (int) (cart.getProduct().getPrice() * cart.getQuantity())).sum());
        return "checkout";
    }
    @PostMapping("/invoice")
    @Transactional
    public String invoice(@RequestParam String name, @RequestParam String address, Model model) {
        OrderEntity orderEntity = orderService.addOrder(name, address);
        orderService.addOrderDetail(orderEntity, cartEntity.getProductList());
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("cartItems", cartEntity.getProductList());
        model.addAttribute("total", cartEntity.getProductList().stream().mapToInt(cart -> (int) (cart.getProduct().getPrice() * cart.getQuantity())).sum());
        return "invoice";
    }
}
