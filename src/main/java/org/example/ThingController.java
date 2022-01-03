package org.example;

import org.example.core.Template;
import org.example.models.Light;
import org.example.models.Thing;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThingController {

    public String detail (Request request, Response response) {

        // FIXME Protect request param for invalid values !

        String idParam = request.params(":id");
        int id = Integer.parseInt(idParam);
        int index = id - 1;

        List<Thing> thingList = HomeSystem.getInstance().getThings();
        Thing thing = thingList.get(index);
        Light light = (Light) thing;

        String action = request.queryParamOrDefault("action", "");

        if (action.equals("toggle")) {
            light.setLightOn(!light.isLightOn());
            System.out.println("TOGGLED LIGHT = " + light.isLightOn());
        }

        Map<String, Object> model = new HashMap<>();
        model.put("id", id);
        model.put("light", light);
        return Template.render("thing_light.html", model);
    }

}
