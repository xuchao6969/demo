package com.xc.designpattern.chainOfResponsibility;

public class Client {
    public static void main(String[] args) {

        // 模拟员工离职交接 需要交接git job file
        Employer e = new Employer(true ,true, true);
        NetManagerHandOver net =new NetManagerHandOver("net");
        ManagerHandOver manger = new ManagerHandOver("manager");
        AdminHandOver admin = new AdminHandOver("admin");

        // 三个对象可以随便设置next 他们是同级关系
        net.setNext(manger);
        manger.setNext(admin);

        net.workHand(e);
    }
}
