package com.xc.designpattern.factory.factoryMethod;

public class Test {
    public static void main(String[] args) {
        Client c = new Client(new AppleFactory());
        Fruit f = c.getFruit();
        f.getName();

        Client ci = new Client(new OrangeFactory());
        Fruit fr = ci.getFruit();
        fr.getName();
    }
}
