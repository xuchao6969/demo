package com.xc.designpattern.factory.abstractFactory;

public abstract class Peach {
    String name;
    public abstract void getName();
}

class StPeach extends Peach{

    public StPeach(){
        this.name = "山东寿桃";
    }
    @Override
    public void getName() {
        System.out.println(name);
    }
}
class YoutPeach extends Peach{
    public YoutPeach(){
        this.name = "山西油桃";
    }
    @Override
    public void getName() {
        System.out.println(name);
    }
}