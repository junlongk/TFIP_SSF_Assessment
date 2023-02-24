package sg.edu.nus.iss.pizzastorerevamped.utils;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.pizzastorerevamped.models.Delivery;
import sg.edu.nus.iss.pizzastorerevamped.models.Order;
import sg.edu.nus.iss.pizzastorerevamped.models.Pizza;

import java.io.StringReader;

public class PizzaUtil {

    // From json object to Pizza object
    public static Pizza jsonToPizza(JsonObject json) {
        Pizza pizza = new Pizza();
        pizza.setPizza(json.getString("pizza"));
        pizza.setSize(json.getString("size"));
        pizza.setQuantity(json.getInt("quantity"));
        return pizza;
    }

    // From json object to Delivery object
    public static Delivery jsonToDelivery(JsonObject json) {
        Delivery delivery = new Delivery();
        delivery.setName(json.getString("name"));
        delivery.setAddress(json.getString("address"));
        delivery.setPhone(json.getString("phone"));
        delivery.setRush(json.getBoolean("rush"));
        delivery.setComments(json.getString("comments"));
        return delivery;
    }

    // From Order object to json to string
    // For saving Order string to Redis
    public static String orderToStr(Order order) {
        return Json.createObjectBuilder()
                .add("orderId", order.getOrderId())
                .add("name", order.getName())
                .add("address", order.getAddress())
                .add("phone", order.getPhone())
                .add("rush", order.getRush())
                .add("comments", order.getComments())
                .add("pizza", order.getPizza())
                .add("size", order.getSize())
                .add("quantity", order.getQuantity())
                .add("total", order.getTotalCost())
                .build()
                .toString();
    }

    // From string to json to Order object
    // For retrieving Order from Redis
    public static Order strToOrder(String str) {
        // Converts str to json
        JsonReader reader = Json.createReader(new StringReader(str));
        JsonObject json = reader.readObject();

        // Gets pizza object & delivery object from json
        Pizza pizza = jsonToPizza(json);
        Delivery delivery = jsonToDelivery(json);

        // Create new Order object with pizza & delivery
        Order order = new Order(pizza, delivery);
        order.setOrderId(json.getString("orderId"));
        order.setTotalCost((float) json.getJsonNumber("total").doubleValue());
        return order;
    }

}
