package com.xc.designpattern.factory.factoryMethod;

public class Client {
    private FruitFactory factory;
    public Client(FruitFactory factory){
        this.factory = factory;
    }
    public Fruit getFruit(){
        return this.factory.getFruit();
    }
}
