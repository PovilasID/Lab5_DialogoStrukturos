/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JTextArea;

/**
 *
 * @author PovilasSid
 */
public class PlaceTreeMap {
    Map<String, Place> pl = new TreeMap<String, Place>(listBySuggestionRating);

    public boolean doesPlaceDataExist(String eMail) {
        return pl.containsKey(eMail);
    }

    public void addPlaceData(Place place) {
        pl.put(place.getType(), place);
    }
    
    public void addPlaceTree(HashMap place){
        pl.putAll(place);
    }

    public Place removePlaceData(String eMail) {
        return pl.remove(eMail);
    }

    public Place getPlaceData(String eMail) {
        return pl.get(eMail);
    }

    public void isvedimas(JTextArea taLaukas) {
        taLaukas.append("\n Ivesti Elementai:\n");
        printMap(pl, taLaukas);
    }

    static public void ouSwn(Object obj, JTextArea ta) {
        ta.append(obj.toString() + "\n");
    }

    public static void printMap(Map mp, JTextArea taLaukas) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            ouSwn(pairs, taLaukas);
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
    public static Comparator listBySuggestionRating = new Comparator() {
       public int compare(Object o1, Object o2) {
          double r1 = ((Place) o1).getSparks() / ((Place) o1).getDistance();
          double r2 = ((Place) o2).getSparks() / ((Place) o2).getDistance();
          // rida atvirkščia mažėjančia tvarka, pradedant nuo didžiausios
          if(r1<r2) return 1;
          if(r1>r2) return -1;
          return 0;
       }
    };
    
}
