package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Johnathan Steven Salamanca Lancheros
 */
@Entity
@Table(name = "reporte")
public class Reporte extends Model {

    public static Finder<Long,Reporte> FINDER = new Finder<>(Reporte.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    public Long id;

    public String urlOrigen;

    @OneToMany(cascade = CascadeType.ALL)
    public List<URLDestino> urlsDestino;

    public boolean valido;

    public Reporte(String urlOrigen){
        this.urlOrigen = urlOrigen;
        urlsDestino = new ArrayList<>();
    }
}
