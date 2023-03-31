package com.xc.designpattern.factory.factoryMethod;

public class OrangeFactory extends FruitFactory{
    @Override
    public Fruit getFruit() {
        return new Orange();
    }
}
