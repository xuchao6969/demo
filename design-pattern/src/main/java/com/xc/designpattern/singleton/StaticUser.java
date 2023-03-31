package com.xc.designpattern.singleton;

public class StaticUser {

    private static class UserHolder{
        public static StaticUser instance = new StaticUser();
    }
    public static StaticUser getInstance(){
        return  UserHolder.instance;
    }

}
