package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "product/list";
    }
}
