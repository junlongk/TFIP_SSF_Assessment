package sg.edu.nus.iss.pizzastorerevamped.controllers;

import jakarta.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.iss.pizzastorerevamped.models.Order;
import sg.edu.nus.iss.pizzastorerevamped.services.PizzaService;
import sg.edu.nus.iss.pizzastorerevamped.utils.PizzaUtil;

import java.util.Optional;

@RestController
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaSvc;

    @GetMapping(path = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOrderById(@PathVariable String orderId) {
        Optional<Order> opt = pizzaSvc.getOrder(orderId);
        if (opt.isEmpty()) {
            String resp = Json.createObjectBuilder()
                    .add("message", "Order %s not found".formatted(orderId))
                    .build()
                    .toString();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(resp);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(PizzaUtil.orderToStr(opt.get()));
    }
}
