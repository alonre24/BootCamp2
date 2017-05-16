package com.example.rest;

/**
 * Created by Administrator on 04/05/2017.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class SoldiresTest {

    private Crew crew;


    @Before
    public void setUp() {

    }

    @Test
    public void addSoliderTest () {
        assertEquals(0, crew.getNumberOfSoliders());
        assertEquals("Yossi", crew.getCommander().getFirstName());

//        Solider sol1 = new Solider(1212121, "Sol1", "-", "0501111111",
//                "Tel-Aviv", "01-01-1992", "Yossi", 1, 97, Rank.RECRUIT);
//        crew.addSoilder(sol1);
        assertEquals(1, crew.getNumberOfSoliders());

    }



}
