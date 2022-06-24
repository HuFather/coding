package com.zhaohu.design.command;

/**
 * @creator : zhaohu
 * @date : 6/21/2022
 * @description :
 */
public class DoTwoCommand implements Command{
    @Override
    public void execute() {
        System.out.println("do two");
    }
}
