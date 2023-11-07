package com.nextShop.store.exceprion;

public class ItemDeletedException extends RuntimeException {
    public ItemDeletedException(String name) {
        super(name.substring(0, 1).toUpperCase() + " ");
    }
}
