package com.gtv.hanhee.customview.Interface;

public class PageLessException extends Exception {
    @Override
    public String getMessage() {
        return "Pages must equal or larger than 2";
    }
}
