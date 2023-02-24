package sg.edu.nus.iss.pizzastorerevamped.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sg.edu.nus.iss.pizzastorerevamped.models.Delivery;
import sg.edu.nus.iss.pizzastorerevamped.models.Order;
import sg.edu.nus.iss.pizzastorerevamped.models.Pizza;
import sg.edu.nus.iss.pizzastorerevamped.services.PizzaService;

import java.util.logging.Logger;

@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaSvc;

    private final Logger logger = Logger.getLogger(PizzaController.class.getName());

    @GetMapping(path = {"/","/index.html"})
    public String getPizza(Model model, HttpSession session) {
        // Retrieve session, if session is empty, create new session
        Pizza pizza = (Pizza) session.getAttribute("pizza");
        if (null == pizza) {
            pizza = new Pizza();
            session.setAttribute("pizza", pizza);
        }

        model.addAttribute("pizza", pizza);
        return "index";
    }

    @PostMapping(path = "/pizza")
    public String postPizza (@Valid Pizza pizza, BindingResult bindingResult,
                             Model model, HttpSession session) {

        logger.info("PIZZA CREATED! \n%s".formatted(pizza.toString()));

        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", pizza);
            return "index";
        }

        session.setAttribute("pizza", pizza);

        model.addAttribute("delivery", new Delivery());
        return "delivery";
    }

    @PostMapping(path = "/pizza/order")
    public String postOrder(@Valid Delivery delivery, BindingResult bindingResult,
                            Model model, HttpSession session) {

        logger.info("DELIVERY DETAILS RECEIVED! \n%s".formatted(delivery.toString()));

        if (bindingResult.hasErrors()) {
            model.addAttribute("delivery", delivery);
            return "delivery";
        }

        // Retrieve pizza object from session
        Pizza pizza = (Pizza) session.getAttribute("pizza");

        // create order object with pizza object and delivery object
        Order order = pizzaSvc.createOrder(pizza, delivery);

        logger.info("ORDER CREATED! \n%s".formatted(order.toString()));

        model.addAttribute("order", order);

        return "order";
    }

}
