package com.zhaohu.design.headfirst.obverse;

import java.util.Observable;
import java.util.Observer;

public class Subject extends Observable {
    public static void main(String[] args) {
        User user=new User();
        Subject subject=new Subject();
        subject.addObserver(user);
        subject.change();
    }

    public int getInt(){
        return 1;
    }
    public void change(){

        setChanged();
        notifyObservers();
    }
}

class User implements Observer{


    @Override
    public void update(Observable o, Object arg) {
        if(Subject.class.isInstance(o)){
            int a=((Subject)o).getInt();
            System.out.println(a);
        }
    }
}
