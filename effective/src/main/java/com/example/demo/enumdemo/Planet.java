package com.example.demo.enumdemo;

public enum Planet {
    EARTH(13,20){
        @Override
        public void apply() {
            System.out.println("");
        }
    },
    Other(14,24){
        @Override
        public void apply() {
            
        }
    };

    private final int mass;
    private final double radius;
    private final double surfaceGravity;
    Planet(int mass,double radius){
        this.mass=mass;
        this.radius=radius;
        this.surfaceGravity=mass*radius;
    }

    public abstract void apply();

    public void Test(){
        System.out.println(this.toString());
    }
}
