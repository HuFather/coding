package com.example.demo.enumdemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void values() {
        Planet[] values = Planet.values();
        assertEquals(2,values.length);
    }

    @Test
    void valueOf() {
        Planet earth = Planet.valueOf("EARTH");
        Planet other = Planet.valueOf("Other");
        assertEquals(Planet.EARTH,earth);

    }

    @Test
    void test(){
        Planet.EARTH.Test();
    }
}