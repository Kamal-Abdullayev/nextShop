package com.nextShop.product.exceprion;

public class ItemDeletedException extends RuntimeException {
    public ItemDeletedException(String name) {
        super(name.substring(0, 1).toUpperCase() + " ");
    }
}
