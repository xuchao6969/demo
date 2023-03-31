package com.xc.designpattern.factory.simpleFactory;

public class Client {
    private FruitFactory factory;

    public Client(FruitFactory factory){
        this.factory = factory;
    }

    public Fruit getFruit(String type){
        return factory.getFruit(type);
    }
}
