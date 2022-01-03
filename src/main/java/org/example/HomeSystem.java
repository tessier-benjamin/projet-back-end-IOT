package org.example;

import org.example.models.Thing;

import java.util.ArrayList;
import java.util.List;

public class HomeSystem {
    private final List<Thing> things;

    public HomeSystem() {
        this.things = new ArrayList<>();
    }

    public boolean addThing(Thing thing){
        return things.add(thing);
    }

    public List<Thing> getThings() {
        return things;
    }
}
