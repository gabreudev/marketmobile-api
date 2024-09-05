package com.gabreudev.marketmobile_api.entities.product;

import com.gabreudev.marketmobile_api.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.UUID;

@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bar_code", "user_id"})
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_product;

    @Pattern(regexp = "\\d{12}", message = "O código de barras deve ter 12 dígitos.")
    private String barCode;

    @NotBlank(message = "O nome do produto não pode ser nulo.")
    @Size(min = 2, max = 100, message = "O nome do produto deve ter entre 2 e 100 caracteres.")
    private String name;

    private String description;

    @NotNull(message = "O preço do produto não pode ser nulo.")
    @Positive(message = "O preço deve ser um valor positivo.")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Product(ProductRegisterDTO data) {
        this.barCode = data.barCode();
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
    }
    public Product() {

    }
    public UUID getId() {
        return id_product;
    }

    public void setId(UUID id) {
        this.id_product = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

}
