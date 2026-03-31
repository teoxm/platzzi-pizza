package com.platzi.pizzeria.service.exepcion;

public class EmailApiExeption extends RuntimeException {
    public EmailApiExeption(){
        super("Error sending email...");
    }
}
