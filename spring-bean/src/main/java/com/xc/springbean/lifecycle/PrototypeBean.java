package com.xc.springbean.lifecycle;

import org.springframework.beans.factory.DisposableBean;

public class PrototypeBean implements DisposableBean {

    private int id;

    private String name;

    public PrototypeBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void myDestroy() {
        System.out.println("prototypeBean调用 myDestroy-method 方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("prototypeBean 调用DisposableBean destroy-method 方法");
    }
}
