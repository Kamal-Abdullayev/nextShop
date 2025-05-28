package com.nextShop.user_service.utils;


import com.nextShop.user_service.exception.BaseException;

public class CommonUtils {

    @FunctionalInterface
    public interface Checker {
        boolean check();
    }

    public static void throwIf(Checker checker, BaseException ex) {
        if (checker.check()) {
            throw ex;
        }
    }

}
