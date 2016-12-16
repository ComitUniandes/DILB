package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * @author Johnathan Steven Salamanca Lancheros
 */
@Entity
@Table(name = "regla")
public class ReglaAutorizacion extends Model {

    public final static String GENERAL = "General";

    public static Model.Finder<Long,ReglaAutorizacion> FINDER = new Model.Finder<>(ReglaAutorizacion.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    public Long id;

    public String paginaFuente;

    public String paginaDestino;

    /**
     * Método constructor de la clase
     * @param paginaFuente. String que representa la página fuente de la regla de autorización.
     * @param paginaDestino. String que representa la página destino de la regla de autorización.
     */
    public ReglaAutorizacion(String paginaFuente, String paginaDestino) {
        this.paginaFuente = paginaFuente;
        this.paginaDestino = paginaDestino;
    }
}
