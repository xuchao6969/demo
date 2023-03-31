package com.xc.designpattern.factory.abstractFactory;

public class Client {
    private FruitFactory factory;
    public Client(FruitFactory factory){
        this.factory = factory;
    }
    public Apple getApple(){
        return this.factory.getApple();
    }
    public Peach getPeach(){
        return this.factory.getPeach();
    }
}
