package com.zhaohu.tool.shortcut.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @creator : zhaohu
 * @date : 7/7/2022
 * @description :
 */
class ApplicationTest {

    @Test
    void addKeyboards() {
        Application application = new Application("jj");
        List<Keyboard> list = new ArrayList<Keyboard>();
        Keyboard keyboard = new Keyboard("1", "2");
        list.add(keyboard);

        application.addKeyboards(list);

        list.remove(keyboard);

        assertEquals(application.getKeyboards().size(), 1);

    }
}