package com.example.fishingapp;

import com.example.fishingapp.models.EntityFish;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class fishingTest {
    private EntityFish fish;

    @Before
    public void setUp(){
        this.fish = new EntityFish();
    }

    @Test
    public void idFish(){
        this.fish.setId("F1");
        assertEquals("F1", this.fish.getId());
        this.fish.setId("F2");
        assertEquals("F2", this.fish.getId());
    }

    @Test
    public void dateFish(){
        assertEquals(0, this.fish.setDate("21/02/2021"));
        assertEquals(2, this.fish.setDate("31/02/2020"));
        assertEquals(1, this.fish.setDate(""));
        assertEquals("21/02/2021", this.fish.getDate());
    }

    @Test
    public void fishFish(){
        assertEquals(0, this.fish.setFish("Carpa"));
        assertEquals(2, this.fish.setFish("123456"));
        assertEquals(1, this.fish.setFish(""));
        assertEquals("carpa", this.fish.getFish());
    }

    @Test
    public void fishWeight(){
        assertEquals(0, this.fish.setWeight("2.5kg"));
        assertEquals(0, this.fish.setWeight("2,5"));
        assertEquals(1, this.fish.setWeight(""));
        assertEquals("2,5", this.fish.getWeight());
    }

    @Test
    public void fishCaptures(){
        assertEquals(2, this.fish.setCaptures("Una"));
        assertEquals(0, this.fish.setCaptures("2"));
        assertEquals(1, this.fish.setCaptures(""));
        assertEquals("2", this.fish.getCaptures());
    }

    @Test
    public void fishFisher(){
        assertEquals(0, this.fish.setFisher("Andrea"));
        assertEquals(2, this.fish.setFisher("2"));
        assertEquals(1, this.fish.setFisher(""));
        assertEquals("andrea", this.fish.getFisher());
    }


    @Test
    public void fishInformation(){
        assertEquals(0, this.fish.setInformation("He pescado"));
        assertEquals(2, this.fish.setInformation("2"));
        assertEquals(1, this.fish.setInformation(""));
        assertEquals("he pescado", this.fish.getInformation());
    }

    @Test
    public void fishSex(){
        this.fish.setSex("Hembra");
        assertEquals("Hembra", this.fish.getSex());
        this.fish.setSex("Macho");
        assertEquals("Macho", this.fish.getSex());
    }

    @Test
    public void fishImage(){
        this.fish.setImage("Carpa.jpg");
        assertEquals("Carpa.jpg", this.fish.getImage());
        this.fish.setImage("Barbo.jpg");
        assertEquals("Barbo.jpg", this.fish.getImage());
    }

    @Test
    public void fishLoose(){
        this.fish.setLoose(true);
        assertEquals(true, this.fish.isLoose());
        this.fish.setLoose(false);
        assertEquals(false, this.fish.isLoose());
    }
}
