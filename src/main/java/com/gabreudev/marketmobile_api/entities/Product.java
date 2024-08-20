package com.gabreudev.marketmobile_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Product {

    @Id
    @Pattern(regexp = "\\d{12}", message = "O código de barras deve ter 12 dígitos.")
    private String barCode;

    @NotBlank(message = "O nome do produto não pode ser nulo.")
    @Size(min = 2, max = 100, message = "O nome do produto deve ter entre 2 e 100 caracteres.")
    private String name;

    private String description;

    @NotBlank(message = "O preço do produto não pode ser nulo.")
    @Positive(message = "O preço deve ser um valor positivo.")
    private Float price;


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
