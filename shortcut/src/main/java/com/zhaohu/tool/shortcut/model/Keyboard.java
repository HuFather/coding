package com.zhaohu.tool.shortcut.model;

/**
 * @creator : zhaohu
 * @date : 7/7/2022
 * @description :
 */
public class Keyboard {
    private String name;
    private String description;

    public Keyboard(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
