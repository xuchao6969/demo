package com.xc.designpattern.factory.abstractFactory;

public class Test {
    public static void main(String[] args) {
        Client c = new Client(new SxFactory());
        Apple a = c.getApple();
        Peach p = c.getPeach();
        a.getName();
        p.getName();
    }
}
