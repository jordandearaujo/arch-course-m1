package org.example;

import org.example.models.Light;
import org.example.models.Thing;

import java.util.ArrayList;
import java.util.List;

public class HomeSystem implements Light.OnLightChangeListener {

    private static HomeSystem instance;
    private final List<String> logs = new ArrayList<>();
    private final List<Thing> things;

    private HomeSystem() {
        this.things = new ArrayList<>();
    }

    public static HomeSystem getInstance() {
        if (instance == null) {
            instance = new HomeSystem();
        }

        return instance;
    }

    public boolean addThing(Thing thing) {
        return things.add(thing);
    }

    public List<Thing> getThings() {
        return things;
    }

    @Override
    public void onLightChange(Light light) {
        String message = "HomeSystem - Light " + light.getName() + " updated. light on=" + light.isLightOn();
        System.out.println(message);
        logs.add(message);
    }
}
