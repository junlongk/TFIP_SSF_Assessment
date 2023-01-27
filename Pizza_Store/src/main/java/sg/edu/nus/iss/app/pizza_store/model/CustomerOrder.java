package sg.edu.nus.iss.app.pizza_store.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Random;

public class CustomerOrder implements Serializable {

    private String orderId;

    @NotNull(message = "Name is mandatory")
    @Size(min = 3, message = "Name must be of minimum 3 characters")
    private String name;

    @NotNull(message = "Address is mandatory")
    private String address;

    @NotNull(message = "Phone number is mandatory")
    @Size(min = 8, max = 8, message = "Phone numbers must be 8 digits")
    private String phone;

    private boolean rush;

    private String comments;

    @NotNull(message = "Please choose a pizza!")
    private String pizzaType;

    @NotNull(message = "Please choose a size!")
    private String size;

    @Min(value = 1, message = "You need to order a minimum of 1 pizza!")
    @Max(value = 10, message = "You can only order a maximum of 10 pizzas!")
    private int quantity;

    public CustomerOrder() {
        this.orderId = this.generateId(8);
    }

    public CustomerOrder(String name,
                        String address,
                        String phone, boolean rush, String comments,
                        String pizzaType,
                        String size,
                        int quantity) {
        this.orderId = this.generateId(8);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rush = rush;
        this.comments = comments;
        this.pizzaType = pizzaType;
        this.size = size;
        this.quantity = quantity;
    }

    public CustomerOrder(String orderId,
                        String name,
                        String address,
                        String phone, boolean rush, String comments,
                        String pizzaType,
                        String size,
                        int quantity) {
        this.orderId = orderId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rush = rush;
        this.comments = comments;
        this.pizzaType = pizzaType;
        this.size = size;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isRush() {
        return rush;
    }

    public void setRush(boolean rush) {
        this.rush = rush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
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

    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numChars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numChars);
    }
}
