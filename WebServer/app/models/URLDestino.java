package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * @author Johnathan Steven Salamanca Lancheros
 */
@Entity
@Table(name = "urldestino")
public class URLDestino {

    public static Model.Finder<Long,URLDestino> FINDER = new Model.Finder<>(URLDestino.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    public Long id;

    public String tagName;
    public String link;
    public String action;

}
