package controllers;
import java.sql.DriverManager;
import java.sql.Connection;
import play.mvc.*;
import play.libs.Json;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }


    public Result first() {
        System.out.println("13");
        try {
            System.out.println("aaa");
            Class.forName("org.postgresql.Driver");
            System.out.println("a");
            Connection connection = null;
            System.out.println("b");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/library","postgres", "root");
            System.out.println("c");
            connection.close();
        } catch (Exception ex) {
            // Log or abort here
            System.out.println("11");
            System.out.print(ex);
        }
        return ok(Json.toJson("Discrepancies raised"));
    }
}
