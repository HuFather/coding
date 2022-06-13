package com.zhaohu.design.headfirst.obverse;

import java.util.ArrayList;
import java.util.List;

interface Observable{
    public void Register(Observer observer);
    public void UnRegister(Observer observer);

    public void Notification();
}

interface Observer{
    public void Update();
}
class Platform implements Observer{

    @Override
    public void Update() {
        System.out.println("ssss");
    }
}
public class WeatherDta  implements Observable{
    List<Observer> list;
    public WeatherDta(){
        list=new ArrayList<>();
    }
    @Override
    public void Register(Observer observer) {
        list.add(observer);
    }

    @Override
    public void UnRegister(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void Notification() {
        for (Observer observer:list){
            observer.Update();
        }
    }

    public static void main(String[] args) {
        WeatherDta weatherDta=new WeatherDta();
        Platform platform=new Platform();
        weatherDta.Register(platform);
        weatherDta.Notification();
    }
}
