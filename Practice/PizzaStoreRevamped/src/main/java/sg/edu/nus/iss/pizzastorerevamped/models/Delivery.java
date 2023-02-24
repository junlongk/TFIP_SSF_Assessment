package sg.edu.nus.iss.pizzastorerevamped.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public class Delivery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Name is mandatory!")
    @Size(min = 3, message = "Name must have minimum of 3 characters!")
    private String name;

    @NotNull(message = "Address is mandatory!")
    private String address;

    @NotNull(message = "Phone number is mandatory!")
    @Size(min = 8, max = 8, message = "Phone number must consist of 8 digits!")
    private String phone;

    private Boolean rush = false;

    private String comments;


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

    public Boolean getRush() {
        return rush;
    }
    public void setRush(Boolean rush) {
        this.rush = rush;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Delivery{name='%s', address='%s', phone='%s', rush=%s, comments='%s'}".formatted(name, address, phone, rush, comments);
    }
}
