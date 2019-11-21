package ru.javawebinar.topjava.util.exception;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException implements Supplier {
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public Object get() {
        return null;
    }
}