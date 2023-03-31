package com.xc.designpattern.singleton;

import java.util.concurrent.*;

public class LazyUser {

    private volatile static LazyUser instance;

    public static LazyUser getInstance(){
        if (instance == null){
            synchronized (LazyUser.class){
                if (instance == null){
                    instance = new LazyUser();
                }
            }
        }
        return instance;
    }


}
