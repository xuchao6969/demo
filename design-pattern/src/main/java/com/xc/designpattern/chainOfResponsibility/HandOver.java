package com.xc.designpattern.chainOfResponsibility;

public abstract class HandOver {
    private String name;
    private HandOver nextHandOver;
    public HandOver(String name) {
        this.name = name;
    }
    public HandOver setNext(HandOver hand){
        this.nextHandOver = hand;
        return hand;
    }
    public abstract boolean done(Employer employer) ;

    public void workHand(Employer employer){
        if (!done(employer)){
            System.out.println(name + "处理失败");
        }else{
            System.out.println(name + "部分交接完成");
            if(null != nextHandOver){
                nextHandOver.workHand(employer);
            }
        }
    }
}
