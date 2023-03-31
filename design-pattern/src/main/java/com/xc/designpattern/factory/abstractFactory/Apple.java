package com.xc.designpattern.factory.abstractFactory;

public abstract class Apple {
    String name;
    public abstract void getName();
}

class HxApple extends Apple{

    public HxApple(){
        this.name = "山西红星苹果";
    }
    @Override
    public void getName() {
        System.out.println(name);
    }
}

class YtApple extends Apple{
    public YtApple(){this.name = "山东烟台苹果";}

    @Override
    public void getName() {
        System.out.println(name);
    }
}