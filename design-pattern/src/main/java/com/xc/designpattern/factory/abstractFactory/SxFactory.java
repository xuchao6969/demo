package com.xc.designpattern.factory.abstractFactory;

public class SxFactory extends FruitFactory {


    @Override
    public Apple getApple() {
        return new HxApple();
    }

    @Override
    public Peach getPeach() {
        return new YoutPeach();
    }
}
