package com.xc.designpattern.factory.factoryMethod;

public class AppleFactory extends FruitFactory{

    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
