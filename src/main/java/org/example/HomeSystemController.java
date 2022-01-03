package org.example;

import org.example.core.Template;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeSystemController {

    public String list(Request request, Response response) {


        Map<String, Object> model = new HashMap<>();

            model.put("things", HomeSystem.getInstance().getThings());

        return Template.render("home.html", model);
    }

}
