package com.xc.designpattern.chainOfResponsibility;

public class AdminHandOver extends HandOver{
    public AdminHandOver(String name) {
        super(name);
    }
    @Override
    public boolean done(Employer employer) {
        if (!employer.getFileHandOver()){
            System.out.println("file交接失败");
            return false;
        }
        System.out.println("file交接成功");
        return true;
    }
}
