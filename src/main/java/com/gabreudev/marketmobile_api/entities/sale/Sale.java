package com.gabreudev.marketmobile_api.entities.sale;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gabreudev.marketmobile_api.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_sale;

    private LocalDateTime saleDate;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SaleProduct> saleProducts;

    @NotNull
    private Float totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Sale(SaleRegisterDTO data, List<SaleProduct> saleProducts) {
        this.totalPrice = data.totalPrice();
        this.saleProducts = saleProducts;
    }
    public Sale(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SaleProduct> getSaleProducts() {
        return saleProducts;
    }

    public Long getId() {
        return id_sale;
    }

    public void setId(Long id) {
        this.id_sale = id;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public void setSaleProducts(List<SaleProduct> saleProducts) {
        this.saleProducts = saleProducts;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}

