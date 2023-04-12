package com.xc.designpattern.chainOfResponsibility;

public class ManagerHandOver extends HandOver{
    public ManagerHandOver(String name) {
        super(name);
    }
    @Override
    public boolean done(Employer employer) {
        if (!employer.getJobHandOver()){
            System.out.println("job交接失败");
            return false;
        }
        System.out.println("job交接成功");
        return true;
    }
}
