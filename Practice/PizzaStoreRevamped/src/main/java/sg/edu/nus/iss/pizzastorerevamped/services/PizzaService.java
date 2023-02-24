package sg.edu.nus.iss.pizzastorerevamped.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.iss.pizzastorerevamped.models.Delivery;
import sg.edu.nus.iss.pizzastorerevamped.models.Order;
import sg.edu.nus.iss.pizzastorerevamped.models.Pizza;
import sg.edu.nus.iss.pizzastorerevamped.repositories.PizzaRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepo;

    public Order createOrder (Pizza pizza, Delivery delivery) {
        // Creates new order using pizza object & delivery object
        Order order = new Order(pizza, delivery);

        // Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        order.setOrderId(orderId);

        // Calculate order cost
        float total;
        float pizzaCost = 0;
        float pizzaSizeMultiplier = 0;

        String pizzaType = order.getPizza();
        String pizzaSize = order.getSize();
        int pizzaQty = order.getQuantity();

        switch (pizzaType) {
            case "bella", "marinara", "spianatacalabrese":
                pizzaCost = 30f;
                break;
            case "margherita":
                pizzaCost = 22f;
                break;
            case "trioformaggio":
                pizzaCost = 25f;
                break;
            default:
        }

        switch (pizzaSize) {
            case "sm":
                pizzaSizeMultiplier = 1.0f;
                break;
            case "md":
                pizzaSizeMultiplier = 1.2f;
                break;
            case "lg":
                pizzaSizeMultiplier = 1.5f;
                break;
            default:
        }

        total = pizzaCost * pizzaSizeMultiplier * pizzaQty;

        // Set pizzaCost for View 2
        order.setPizzaCost(total);

        if (order.getRush())
            total += 2;

        order.setTotalCost(total);

        // Save order to redis
        pizzaRepo.saveOrder(order);

        return order;
    }

    public Optional<Order> getOrder(String orderId) {
        return pizzaRepo.getOrder(orderId);
    }
}
