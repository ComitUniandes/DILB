package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.ReglaAutorizacion;
import models.Reporte;
import models.URLDestino;
import play.libs.Json;
import play.mvc.*;
import scala.util.parsing.json.JSONObject;
import services.CSVReader;

import java.util.ArrayList;
import java.util.List;

import static com.avaje.ebean.Expr.eq;
import static com.avaje.ebean.Expr.ilike;

/**
 * @author Johnathan Steven Salamanca Lancheros
 */
public class WebPage extends Controller{

    /**
     * Crea una nueva petición de revisión para la autorización de enlaces externos.
     * Método POST que recibe un objeto JSON, lo lee y realiza los análisis correspondientes.
     * @return JSON de respuesta para la petición.
     */
    public Result crear() {

        Long tinit = System.currentTimeMillis();

        JsonNode reporte = request().body().asJson();
        Reporte objeto = Json.fromJson(reporte, Reporte.class);
        String urlOrigen = objeto.urlOrigen;
        List<URLDestino> urlsDestino = objeto.urlsDestino;
        List<ReglaAutorizacion> reglas = ReglaAutorizacion.FINDER.where()
                .or(ilike("pagina_fuente", urlOrigen),eq("pagina_fuente", ReglaAutorizacion.GENERAL))
                .findList();

        List<String> urlsMal = new ArrayList<>();

        for(int i = 0; i < urlsDestino.size(); i++){
            String urlDestino = urlsDestino.get(i).link;
            boolean bien = false;
            for(int j = 0; j < reglas.size() && !bien; j++){
                ReglaAutorizacion regla = reglas.get(j);
                if(urlDestino.contains(regla.paginaDestino )){
                    bien = true;
                }
            }
            if(!bien){
                urlsMal.add(urlDestino);
            }

        }

        Long tfin = System.currentTimeMillis();

        System.out.println("Tiempo: " + (tfin - tinit));

        if(urlsMal.size() > 0){
            Respuesta rta = new Respuesta("Mal");
            return ok(Json.toJson(rta));
        }
        else {
            Respuesta rta = new Respuesta("Bien");
            return ok(Json.toJson(rta));
        }
    }

    /**
     * Método que muestra las reglas de autorización que se encuentran registradas en la base de datos.
     * @return JSON de respuesta con las reglas de autorización registradas.
     */
    public Result dar(){
        List<ReglaAutorizacion> reglasAutorizacion = ReglaAutorizacion.FINDER.all();
        return ok(Json.toJson(reglasAutorizacion));
    }

    /**
     * Método que carga la lista blanca en la base de datos.
     * @return Respuesta a la solicitud de carga.
     */
    public Result cargarLista(){
        CSVReader.read();
        return ok("La lista se encuentra cargada en la base de datos");
    }

    /**
     * Sub-Clase que representa la estructura de la respuesta del servidor
     */
    public class Respuesta{
        public String respuesta;
        public Respuesta(String pRespuesta){
            respuesta = pRespuesta;
        }
    }
}
