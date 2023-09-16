package com.nextShop.product.exceprion;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String itemName) {
        super(itemName + " not found.");
    }
}
