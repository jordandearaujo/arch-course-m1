package org.example;

import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.models.Light;
import spark.Spark;

public class App {


    public static void main(String[] args) {
        initialize();

        HomeSystem homeSystem = HomeSystem.getInstance();

        Light light = new Light();
        light.setName("Living Room");
        light.setLightChangeListener(homeSystem);
        homeSystem.addThing(light);

        Light light2 = new Light();
        light2.setName("Bed Room");
        light2.setLightChangeListener(homeSystem);
        homeSystem.addThing(light2);

        HomeSystemController homeSystemController = new HomeSystemController();
        ThingController thingController = new ThingController();

        Spark.get("/", homeSystemController::list);
        Spark.get("/things/:id", thingController::detail);

    }

    static void initialize() {
        Template.initialize();

        // Display exceptions in logs
        Spark.exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // Serve static files (img/css/js)
        Spark.staticFiles.externalLocation(Conf.STATIC_DIR.getPath());

        // Configure server port
        Spark.port(Conf.HTTP_PORT);
        final LoggerMiddleware log = new LoggerMiddleware();
        Spark.before(log::process);
    }
}
