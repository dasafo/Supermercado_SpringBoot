package com.dasafodata.SupermercadoPruebaTecnica.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msje) {

        super(msje); //super a la clase que extiende RuntimeExceptio
    }
}
