package services;

import models.ReglaAutorizacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


/**
 * @author Johnathan Steven Salamanca Lancheros
 * Clase encargada de leer el archivo .csv que contiene las reglas de autorización
 */
public class CSVReader {

    /**
     * Método encargado de realizar la lectura del archivo csv y cargar las reglas de autorización a la base de datos
     * <b>pre: </b>El archivo .csv debe existir en la carpeta "./public/data". Debe llamarse "WhiteList.csv"
     * <b>pos: </b>Se crean y cargan a la base de datos las reglas de autorización contenidas en el archivo
     */
    public static void read() {

        List<ReglaAutorizacion> reglasAutorizacion = ReglaAutorizacion.FINDER.all();

        // Borrado de las reglas de autorización que se encuentran en la base de datos
        for(int i = 0; i < reglasAutorizacion.size(); i++){
            reglasAutorizacion.get(i).delete();
        }

        String csvFile = "./public/data/WhiteList.csv";
        String line = "";
        String cvsSplitBy = ",";

        // Lectura del archivo .csv y persistencia de cada regla de autorización
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] reglaAuth = line.split(cvsSplitBy);

                System.out.println(reglaAuth[0] + " - " + reglaAuth[1]);

                String origen = reglaAuth[0];
                String destino = reglaAuth[1];

                ReglaAutorizacion regla = new ReglaAutorizacion(origen, destino);
                regla.save();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
