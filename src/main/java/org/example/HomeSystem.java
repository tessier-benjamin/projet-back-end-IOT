package org.example;

import org.example.models.Light;
import org.example.models.Thing;

import java.util.ArrayList;
import java.util.List;

public class HomeSystem implements Light.OnLightChangedListener {

    public enum State {
        ON,
        OFF,
    }


    private final List<Thing> things;
    private State state;
    private final SystemLogger logger;

    public HomeSystem(SystemLogger logger) {
        this.things = new ArrayList<>();
        this.state = State.ON;
        this.logger = logger;
    }


    public boolean addThing(Thing thing) {
        return things.add(thing);
    }

    public List<Thing> getThings() {
        return things;
    }

    @Override
    public void onLightChanged(Light light) {
        String message = "HomeSystem - Light " + light.getName() + " updated. Light on=" + light.isLightOn();
        logger.addLog(message);
    }

    public void toggleAllLights(boolean isLightOn) {

        if (state == State.OFF){
            return;
        }
        for (Light l : getLights()) {
            l.setLightOn(isLightOn);
        }

    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Light> getLights() {
        List<Light> list = new ArrayList<>();

        for (Thing t : things) {
            if (t instanceof Light) {
                list.add((Light) t);
            }
        }
        return list;
    }

}
