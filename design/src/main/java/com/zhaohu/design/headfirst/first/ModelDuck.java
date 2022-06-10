package com.zhaohu.design.headfirst.first;

public class ModelDuck extends Duck{

    public ModelDuck(){
        flyBehavior=new FlyBehavior() {
            @Override
            public void fly() {
                System.out.println("ss");
            }
        };
    }
    @Override
    public void disPlay() {
        System.out.println("display");
    }
}
