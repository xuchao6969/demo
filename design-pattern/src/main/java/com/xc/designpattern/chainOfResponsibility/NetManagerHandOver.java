package com.xc.designpattern.chainOfResponsibility;

public class NetManagerHandOver extends HandOver{

    public NetManagerHandOver(String name) {
        super(name);
    }
    @Override
    public boolean done(Employer employer) {
        if (!employer.getGitHandOver()){
            System.out.println("git账号交接失败");
            return false;
        }
        System.out.println("git账号交接成功");
        return true;
    }

}
