package sg.edu.nus.iss.app.pizza_store.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sg.edu.nus.iss.app.pizza_store.model.CustomerOrder;

@Controller
public class PizzaController {

    @GetMapping(path = "/")
    public String showPizzaForm(Model model) {
        model.addAttribute("pizzaOrder", new CustomerOrder());
        System.out.println("View 0 :Ran GetMapping");
        return "index";
    }

    @PostMapping(path = "/pizza")
    public String savePizzaOrder(@Valid CustomerOrder pizzaOrder,
                            BindingResult bindingResult,
                            Model model){
        if(bindingResult.hasErrors()){
            System.out.println("View 0: BindingResult error, return to index.html");
            return "index";
        }
        model.addAttribute("pizzaOrder", pizzaOrder);
        System.out.println("View 0: No error, proceed to View 1");
        return "pizza";
    }

    @GetMapping(path = "/pizza")
    public String showDeliveryForm(Model model) {
        System.out.println("View 1 :Ran GetMapping");
        return "pizza";
    }

    @PostMapping(path = "/pizza/order")
    public String saveDeliveryDetails(@Valid CustomerOrder pizzaOrder,
                            BindingResult bindingResult,
                            Model model){
        if(bindingResult.hasErrors()){
            System.out.println("View 1: BindingResult error, return to pizza.html");
            return "pizza";
        }
        model.addAttribute("pizzaOrder", pizzaOrder);
        System.out.println("View 1: No error, proceed to View 2");
        return "order";
    }

}
