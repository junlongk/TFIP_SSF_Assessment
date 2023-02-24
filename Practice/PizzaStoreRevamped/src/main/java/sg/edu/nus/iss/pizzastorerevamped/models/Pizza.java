package sg.edu.nus.iss.pizzastorerevamped.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

public class Pizza implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "You need to select a pizza!")
    private String pizza;

    @NotNull(message = "You need to specify the size of the pizza!")
    private String size;

    @Min(value = 1, message = "You must order at least 1 pizza!")
    @Max(value = 10, message = "You can only order a maximum of 10 pizzas!")
    private int quantity;

    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Pizza{pizza='%s', size='%s', quantity=%d}".formatted(pizza, size, quantity);
    }
}
