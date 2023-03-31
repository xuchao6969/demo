package com.xc.designpattern.factory.simpleFactory;


public class FruitFactory {
    public Fruit getFruit(String type){
        if ("apple".equals(type)){
            return new Apple();
        }else if ("orange".equals(type)){
            return new Orange();
        }else {
            return null;
        }
    }

}
