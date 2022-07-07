package com.zhaohu.tool.shortcut.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @creator : zhaohu
 * @date : 7/7/2022
 * @description :
 */
public class Application {
    private String name;
    private String icon;

    private List<Keyboard> keyboards;

    public Application(String name) {
        this(name, null);
    }

    public Application(String name, String icon) {
        this.name = name;
        this.icon = icon;
        keyboards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public List<Keyboard> getKeyboards() {
        return keyboards;
    }

    public boolean addKeyboard(Keyboard keyboard) {
        return this.keyboards.add(keyboard);
    }

    public boolean addKeyboards(List<Keyboard> keyboards) {
        return this.keyboards.addAll(new ArrayList<>(keyboards));
    }
}
