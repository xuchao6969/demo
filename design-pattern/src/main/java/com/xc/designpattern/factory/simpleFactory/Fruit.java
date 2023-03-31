package com.xc.designpattern.factory.simpleFactory;

public abstract class Fruit {
    String name;
    public abstract void getName();
}
class Apple extends Fruit{

    public Apple() {
        this.name = "apple";
    }

    @Override
    public void getName() {
        System.out.println(name);
    }
}
class Orange extends Fruit{
    public Orange(){
        this.name = "orange";
    }

    @Override
    public void getName() {
        System.out.println(name);
    }
}