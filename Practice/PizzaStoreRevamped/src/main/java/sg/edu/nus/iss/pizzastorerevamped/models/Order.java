package sg.edu.nus.iss.pizzastorerevamped.models;

import java.io.Serial;
import java.io.Serializable;

public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String orderId;
    private float totalCost;
    private final Pizza pizza;
    private final Delivery delivery;

    private float pizzaCost;

    public Order (Pizza pizza, Delivery delivery) {
        this.pizza = pizza;
        this.delivery = delivery;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public float getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getPizzaCost() {
        return pizzaCost;
    }
    public void setPizzaCost(float pizzaCost) {
        this.pizzaCost = pizzaCost;
    }

    public String getPizza() { return this.pizza.getPizza();}
    public String getSize() { return this.pizza.getSize(); }
    public int getQuantity() { return this.pizza.getQuantity(); }
    public String getName() { return this.delivery.getName(); }
    public String getAddress() { return this.delivery.getAddress(); }
    public String getPhone() { return this.delivery.getPhone(); }
    public boolean getRush() { return this.delivery.getRush(); }
    public String getComments() { return this.delivery.getComments(); }

    @Override
    public String toString() {
        return "Order{orderId='%s', totalCost=%f, pizza=%s, delivery=%s, pizzaCost=%f}".formatted(orderId, totalCost, pizza, delivery, pizzaCost);
    }
}
