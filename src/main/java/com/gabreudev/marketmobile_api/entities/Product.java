package com.gabreudev.marketmobile_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Product {

    @Id
    private String barCode;

    private String name;

    private String description;

}
