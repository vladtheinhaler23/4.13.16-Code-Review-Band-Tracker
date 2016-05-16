import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("bandName");
      String hometown = request.queryParams("hometown");
      Band newBand = new Band(name, hometown);
      newBand.save();
      response.redirect("/");
      return null;
    });

    post("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String city = request.queryParams("city");
      Venue newVenue = new Venue(name, city);
      newVenue.save();
      response.redirect("/");
      return null;
    });
  }
}
