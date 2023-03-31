package com.xc.designpattern.factory.abstractFactory;

public class SdFactory extends FruitFactory {

    @Override
    public Apple getApple() {
        return new YtApple();
    }

    @Override
    public Peach getPeach() {
        return new StPeach();
    }
}
