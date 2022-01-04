package org.example;

import org.example.core.Template;
import org.example.models.Light;
import org.example.models.Thermostat;
import org.example.models.Thing;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThingController {
    private final HomeSystem homeSystem;

    public ThingController(HomeSystem homeSystem) {
        this.homeSystem = homeSystem;
    }

    public String detail(Request request, Response response) {

        //FIXME Protect request param for invalid values
        String idParam = request.params(":id");
        int id = Integer.parseInt(idParam);
        int index = id - 1;

        List<Thing> things = homeSystem.getThings();
        Thing thing = things.get(index);

        if (thing instanceof Light){
            return detailLight(request,response,id,(Light) thing);
        }
        else if (thing instanceof Thermostat){
            return detailThermostat(request,response,id,(Thermostat) thing);
        }
        return "";
    }

    private String detailThermostat(Request request, Response response, int id, Thermostat thermostat) {
        String action = request.queryParamOrDefault("action", "");
        String value = request.queryParamOrDefault("value", "");

        if (action.equals("set_temperature")) {
            int temperature = Integer.parseInt(value);
            thermostat.setTemperature(temperature);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("id", id);
        model.put("thermostat", thermostat);
        return Template.render("thing_thermostat.html", model);
    }

    private String detailLight(Request request, Response response, int id, Light light) {
        String action = request.queryParamOrDefault("action", "");

        if (action.equals("toggle")) {
            light.setLightOn(!light.isLightOn());
        }

        Map<String, Object> model = new HashMap<>();
        model.put("id", id);
        model.put("Light", light);
        return Template.render("thing_light.html", model);
    }
}
