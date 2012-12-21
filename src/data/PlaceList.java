/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;
import studijosKTU.ListKTUx;

/**
 *
 * @author PovilasSid
 */
public class PlaceList {
    public ListKTUx<Place> allPlaces = new ListKTUx(new Place());
   
    /*
     * Finds all the places that we have more than
     * defined in variable int sparsks
     */
    public ListKTUx<Place> findSparksPlacesByLowerLimit(int sparks) {
      ListKTUx<Place> mostSparksPlaces = new ListKTUx(new Place());
      
      for (Place p : allPlaces) {
         if (p.getSparks() > sparks) {
            mostSparksPlaces.add(p);
         }
      }
      
      return mostSparksPlaces;
    }
    
  
    public ListKTUx<Place> findPlacesByDistanceByLowerLimit(double place) {
      ListKTUx<Place> dis = new ListKTUx(new Place());
      
      for (Place p : allPlaces) {
         if (p.getDistance()> place) {
            dis.add(p);
         }
      }
      
      return dis;
    }
    
    public ListKTUx<Place> findByPlaceType(String placeType) {
      ListKTUx<Place> placesOfTheSearchedType = new ListKTUx(new Place());
      for (Place p : allPlaces){
         String plType = p.getType();
         if (plType.startsWith(placeType)) {
            placesOfTheSearchedType.add(p);
         }
      }
      return placesOfTheSearchedType;
   }
    
    public ListKTUx<Place> findByNameColor(String placeName) {
      ListKTUx<Place> placesOfTheSearchedType = new ListKTUx(new Place());
      for (Place p : allPlaces){
         String plType = p.getName();
         if (plType.startsWith(placeName)) {
            placesOfTheSearchedType.add(p);
         }
      }
      return placesOfTheSearchedType;
   }
    
    static public void ouSwn(Object obj, JTextArea ta) {
		ta.append(obj.toString() + "\n");
    }
    
    public void loadAndPrint(File fName, JTextArea ta) {
        try {
            BufferedReader fReader =  new BufferedReader(new FileReader(fName));
            String line;
            ta.append("\n     Duomenys is failo <" + fName.getName() + ">\n");

            while ((line = fReader.readLine()) != null) {
                ta.append(line + "\n");
                allPlaces.add(line);
            }
            fReader.close();
        } catch (IOException e) {
            ta.append("\n!!! Failo " + fName.getName() + " skaitymo klaida");
        }
    }
        
    public void isvedimas(JTextArea taLaukas) {
        taLaukas.append("\n Ivesti Elementai:\n");
	for(Place b : allPlaces){
            ouSwn(b, taLaukas);
        }
    }
}