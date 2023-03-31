package com.xc.designpattern.singleton;

public class HungryUser {
    private static HungryUser instance = new HungryUser();

    private HungryUser(){

    }

    public static HungryUser getInstance(){
        return instance;
    }
}
