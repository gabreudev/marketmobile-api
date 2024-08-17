package com.gabreudev.marketmobile_api.entities;

import jakarta.persistence.*;

@Entity
public class SaleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_barcode")
    private Product product;

    private Integer quantity;

    private Float partialPrice;

    public Product getProduct() {
        return product;
    }

    public Float getPartialPrice() {
        return partialPrice;
    }

    public void setPartialPrice(Float partialPrice) {
        this.partialPrice = partialPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
