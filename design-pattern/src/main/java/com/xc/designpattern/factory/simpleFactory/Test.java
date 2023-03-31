package com.xc.designpattern.factory.simpleFactory;

public class Test {
    public static void main(String[] args) {
        Client client = new Client(new FruitFactory());
        Fruit apple = client.getFruit("apple");
        Fruit orange = client.getFruit("orange");
        apple.getName();
        orange.getName();
    }
}
