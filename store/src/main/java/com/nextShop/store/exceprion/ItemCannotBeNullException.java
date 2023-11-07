package com.nextShop.store.exceprion;

public class ItemCannotBeNullException extends RuntimeException {
    public ItemCannotBeNullException(String fieldName) {
        super(fieldName + " can't be null.");
    }
}
