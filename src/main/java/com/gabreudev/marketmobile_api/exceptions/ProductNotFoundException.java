package com.gabreudev.marketmobile_api.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("produto não encontrado");
    }
    public ProductNotFoundException(String message){
        super(message);
    }
}
