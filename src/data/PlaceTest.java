/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Comparator;
import studijosKTU.Ks;
import studijosKTU.ListKTUx;

/**
 *
 * @author PovilasSid
 */
public class PlaceTest {
    static ListKTUx<Place> test=new ListKTUx (new Place());

    public static void chooseMethod(){
        //checkSeparatePlaces();
        //formPlaceList();
        //checkList("Turistinis");
        //addToList();
        //checkPlaceList();
        checkSorting();
    }
    
    public static void checkSeparatePlaces() {
        Place p1 = new Place("KTU Statybos rumai", 54.90596, 23.956235, 5, "akadem");
        Place p2 = new Place("KTU SC", 54.904923, 23.957029, 8, "akadem");
        Place p3 = new Place("KTU Elektronikos rumai", 54.903964, 23.958064, 10, "akadem");
        Place p4 = new Place("KTU Dizaino fukultetas", 54.901068, 23.960451, 6, "akadem");
        Place p5 = new Place("KTU centriniai rūmai", 54.898986, 23.91284, 7, "akadem");
        Place p6 = new Place("PC Akropolis", 54.891582, 23.919145, 6, "pc");
        Place p7 = new Place("Žalgerio arena", 54.890308, 23.914456, 8, "arena");
        Place p8 = new Place("Kauno sporto halė", 54.896111, 23.935833, 7, "arena");
        Place p9 = new Place("PC Savas", 54.92258, 23.963379, 6, "pc");
        Place p10 = new Place("PC Dainava", 54.917736, 23.96677, 4, "pc");

        Ks.oun(p1);
        Ks.oun(p2);
        Ks.oun(p3);
        Ks.oun("Pirmų 3 vietu atstumu vidurkis= "+
                ((p1.getDistance()+p2.getDistance()+p3.getDistance())/3));
        Ks.oun(p4);
        Ks.oun(p5);
        Ks.oun(p6);
        Ks.oun("Kitų 3 vietu intensyvymo suma= "+
                (p4.getSparks()+p5.getSparks()+p6.getSparks()));
    }
    
    public static void formPlaceList() {
        Place p1 = new Place("KTU Statybos rumai", 54.90596, 23.956235, 5, "akadem");
        Place p2 = new Place("KTU SC", 54.904923, 23.957029, 8, "akadem");
        Place p3 = new Place("KTU Elektronikos rumai", 54.903964, 23.958064, 10, "akadem");
        test.add(p1);
        test.add(p2);
        test.add(p3);
        test.println("3 pirmos vietos");
        test.add("KTU Dizaino fukultetas, 54.901068, 23.960451, 6, akadem");
        test.add("KTU centriniai rūmai, 54.898986, 23.91284, 7, akadem");
        test.add("PC Akropolis, 54.891582, 23.919145, 6, pc");

        test.println("Visos 6-ioss vietos");
        Ks.oun("Pirmų 3 vietu atstumu vidurkis= "+
                (test.get(0).getDistance()+test.get(1).getDistance()+
                 test.get(2).getDistance())/3);

        Ks.oun("Kitų 3 vietu intensyvymo suma= "+
                (test.get(3).getSparks())+test.get(4).getSparks()+
                 test.get(5).getSparks());
   }
    
    public static void checkList(String typeSearch){
        int sk=0;
        for (Place p = test.get(1); p!=null; p=test.getNext()){
            if (p.getType().compareTo(typeSearch)==0)
                sk++;
        }
        Ks.oun(typeSearch+" tipo vietu yra = "+sk);
    }
    
    public static void addToList(){
        for (int i=0; i<12; i++){
            test.add(new Place("KTU Statybos rumai", 54.90596, 23.956235, 5+i, "akadem"));
        }
        test.add("KTU Dizaino fukultetas, 54.901068, 23.960451, 6, akadem");
        test.add("KTU centriniai rūmai, 54.898986, 23.91284, 7, akadem");
        test.add("PC Akropolis, 54.891582, 23.919145, 6, pc");
        test.println("Testuojamų vietu sąrašas");
        test.save("ban.txt");
    }

    public static void checkPlaceList(){
        PlaceList bilist = new PlaceList();
        
        bilist.allPlaces.load("ban.txt");
        bilist.allPlaces.println("Bandomasis rinkinys");

        test = bilist.findSparksPlacesByLowerLimit(1000);
        test.println("Pradedant nuo 1000");

        test = bilist.findByPlaceType("akadem");
        test.println("Akdemines vietos");

        test = bilist.findByPlaceType("pc");
        test.println("Prekybos centrai");
    }
    
    public static void checkSorting(){
        PlaceList bList = new PlaceList();

        bList.allPlaces.load("ban.txt");
        Ks.oun("========"+bList.allPlaces.get(0));
        bList.allPlaces.println("Bandomasis rinkinys");
        bList.allPlaces.sort(Place.usingDistance);
        bList.allPlaces.println("Rūšiavimas pagal nuotoli nuo mano namu");
        bList.allPlaces.sort(Place.usingSparks);
        bList.allPlaces.println("Rūšiavimas pagal intencyvuma");
        bList.allPlaces.sort(Place.usingTypeAndName);
        bList.allPlaces.println("Rūšiavimas pagal tipa ir pavadinima");
        bList.allPlaces.sort(listBySuggestionRating);
        bList.allPlaces.println("Rūšiavimas pagal siulymotikimybe");
        bList.allPlaces.sort();
        bList.allPlaces.println("Rūšiavimas pagal compareTo - Intencyvumas");
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
