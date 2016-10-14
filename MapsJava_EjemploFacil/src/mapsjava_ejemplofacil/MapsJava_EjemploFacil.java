package mapsjava_ejemplofacil;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;
import maps.java.*;
/**
 *
 * @author Luis Marcos
 */
public class MapsJava_EjemploFacil {

    public static void error(String funcionError){
        System.err.println("Algo ocurrió, no se pudo ejecutar la función: " + funcionError);
    }
    
    public static void main(String[] args) {
        System.out.println("Esto es un ejemplo fácil de utilización de las clases contenidas en MapsJava.jar");
        System.out.println("Las clases contenidas son: MapsJava, Geocoding, Elevation, Places, Route, ShowMaps, StaticMaps y StreetView");
        System.out.println("\n---------------------------------------");
        
        
        System.out.println("PRIMER EJEMPLO: Codificación geográfica directa/inversa");
        System.out.println("Buscaremos las coordenadas de la siguiente dirección: Madrid, Puerta del Sol "
                + "y las coordenadas recibidas las transformaremos de nuevo en dirección postal");
        Geocoding ObjGeocod=new Geocoding();
        try {
            
            Point2D.Double resultadoCD=ObjGeocod.getCoordinates("Madrid, Puerta del Sol");
            System.out.println("Las coordenadas de \"Madrid, Puerta del Sol\", son: " +
                    resultadoCD.x + "," + resultadoCD.y);
            
            System.out.println("Los resultados obtenidos para la búsqueda de dirección de " +
                    resultadoCD.x + "," + resultadoCD.y + " son:");
            ArrayList<String> resultadoCI=ObjGeocod.getAddress(resultadoCD.x,resultadoCD.y);
            for(String item:resultadoCI){
                System.out.println(item);
            }
        } catch (Exception e) {
            error("Codificación geográfica");
        }
        
         System.out.println("\n---------------------------------------");
        System.out.println("SEGUNDO EJEMPLO: Comprobar STATUS/URL,etc. de las peticiones");
        System.out.println("En este ejemplo veremos cómo comprobar el status, y las diferentes propiedades"
                + " comunes (y estáticas) a todas las clases");
        
        System.out.println("Sensor activo: " + MapsJava.getSensor());
        System.out.println("Idioma: " + MapsJava.getLanguage());
        System.out.println("Región: " + MapsJava.getRegion());
        System.out.println("Clave: " + MapsJava.getKey());
        
        System.out.println("Ahora vamos a comprobar el registro de peticiones");
        String[][] registroPeticiones=MapsJava.getStockRequest();
        for(int i=0;i<registroPeticiones.length;i++){
            System.out.println("Petición " + i);
            for(int j=0;j<registroPeticiones[0].length;j++){
                System.out.print(registroPeticiones[i][j] + "\t");
            }
        }
        
        System.out.println("\n\n---------------------------------------");
        System.out.println("TERCER EJEMPLO: Elevación de un punto");
        System.out.println("Buscaremos la altitud de un punto (el Teide) con las siguientes coordenadas: 28.2725, -16.6425 "
                + "y además comprobaremos la resolución de dicha altitud");
        Elevation ObjElevat=new Elevation();
        try {
            double resultadoAlt=ObjElevat.getElevation(28.2725,-16.6425);
            System.out.println("La altitud del punto 28.2725,-16.6425 es: " + String.valueOf(resultadoAlt) + " metros");
            System.out.println("La resolución de dicha altitud es de :" + String.valueOf(ObjElevat.getResolution()) + " metros");
        } catch (Exception e) {
            error("Elevación");
        }
        
        System.out.println("\n---------------------------------------");
        System.out.println("CUARTO EJEMPLO: Ruta");
        System.out.println("Buscaremos la ruta que hay que recorrer a coche entre Madrid y Barcelona (en coche)");
        Route ObjRout=new Route();
        try {
            String[][] resultado=ObjRout.getRoute("Madrid", "Barcelona", null, Boolean.TRUE, Route.mode.driving, Route.avoids.nothing);
            for(int i=0;i<resultado.length;i++){
                System.out.println("Tramo " + i + ":");
                for(int j=0;j<resultado[0].length;j++){
                    System.out.print(resultado[i][j] + "\t");
                }
                System.out.println("");
            }
        } catch (Exception e) {
            error("Ruta");
        }
        
        
        System.out.println("\n---------------------------------------");
        System.out.println("QUINTO EJEMPLO: IMAGEN STREET VIEW");
        System.out.println("Para esta funcionalidad se requeriría poder ver la imagen mostrada"
                + " y al ser una aplicación de consola no podremos, por lo tanto mostraremos la URL"
                + " asociada a la imagen");
        System.out.println("Vamos a buscar la imagen Street View asociada a \"Madrid, Puerta del Sol\"");
        StreetView ObjStreet=new StreetView();
        try {
            Image imgResultado=ObjStreet.getStreetView("Madrid, Puerta del Sol", new Dimension(300,300),
                    90, 100, -100);
            System.out.println("La URL asociada a la imagen es: " + MapsJava.getLastRequestURL());
        } catch (Exception e) {
            error("Street View");
        }
        
        
        System.out.println("\n---------------------------------------");
        System.out.println("SEXTO EJEMPLO: MAPA ESTÁTICO");
        System.out.println("\nPara esta funcionalidad se requeriría poder ver la imagen mostrada"
                + " y al ser una aplicación de consola no podremos, por lo tanto mostraremos la URL"
                + " asociada al mapa estático");
        System.out.println("Vamos a buscar el mapa asociada a \"Madrid, Puerta del Sol\", en concetro "
                + " el mapa del terreno.");
        
        StaticMaps ObjStatMap=new StaticMaps();
        try {
            Image resultadoMapa=ObjStatMap.getStaticMap("Madrid, Puerta del Sol", 14,new Dimension(300,300),
                    1, StaticMaps.Format.png, StaticMaps.Maptype.terrain);
            System.out.println("La URL asociada al mapa es: " + MapsJava.getLastRequestURL());
        } catch (Exception e) {
            error("Mapas estáticos");
        }
        
        System.out.println("\n---------------------------------------");
        System.out.println("SÉPTIMO EJEMPLO: Places");
        System.out.println("Para esta funcionalidad se requiere tener clave de desarrollador"
                + "");
        System.out.println("Introduzca su clave de desarrollador y pulsa INTRO");

        Scanner in = new Scanner(System.in);
        String claveAPI = in.next();
        MapsJava.setKey(claveAPI);
        
        System.out.println("Bien, ya hemos puesto la clave. Ahora estamos comprobando si es correcta..");
        if("OK".equals(MapsJava.APIkeyCheck(claveAPI))){
             System.out.println("La clave es correcta. Ya estamos buscando locales "
                + "cercanos a \"40.4171111,-3.7031133\"...\n");
            Places ObjPlace=new Places();
            try {
                String[][] resultado=ObjPlace.getPlaces(40.4171111, -3.7031133, 
                        3000, "", "", Places.Rankby.prominence, null);

                for(int i=0;i<resultado.length;i++){
                    System.out.println("Place " + i + ":");
                    for(int j=0;j<resultado[0].length;j++){
                        System.out.print(resultado[i][j] + "\t");
                    }
                    System.out.println("");
                }
            } catch (Exception e) {
                error("Place");
            }
            
        }else{
            System.out.println("Lo sentimos, la clave no es correcta :(");
        }
       
        
     
    }
}
