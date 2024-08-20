package com.gabreudev.marketmobile_api.exceptions;

public class ProductNotFoundException extends RuntimeException{
    ProductNotFoundException(){
        super("produto não encontrado");
    }
    ProductNotFoundException(String message){
        super(message);
    }
}
