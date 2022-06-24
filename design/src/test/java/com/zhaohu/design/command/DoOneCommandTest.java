package com.zhaohu.design.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @creator : zhaohu
 * @date : 6/21/2022
 * @description :
 */
class DoOneCommandTest {

    @Test
    void execute() {
        Command command=new DoOneCommand(new TVServer());
        command.execute();
    }
}