package controllers;

import play.mvc.*;

import views.html.*;

/**
 * @author Johnathan Steven Salamanca Lancheros
 */
public class HomeController extends Controller {

    /**
     * Método que lleva al index de la aplicación web
     */
    public Result index() {
        return ok("Bienvenido al servidor web de Security Filter");
    }

}
