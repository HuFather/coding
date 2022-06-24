package com.zhaohu.design.command;

/**
 * @creator : zhaohu
 * @date : 6/21/2022
 * @description :
 */
public class DoOneCommand implements Command {

    TVServer tvServer;

    public DoOneCommand(TVServer tvServer) {
        this.tvServer = tvServer;
    }

    @Override
    public void execute() {
        tvServer.on();
        System.out.println("do one");
        tvServer.off();
    }
}
