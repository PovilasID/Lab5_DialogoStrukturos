/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import studijosKTU.Ks;
import studijosKTU.ListKTU;
import studijosKTU.Timekeeper;

/**
 *
 * @author PovilasSid
 */
public class SpeedTest {
    
    static Place[] placeStack;
    static ListKTU<Place> aSeries = new ListKTU<Place>();
    static Random ag = new Random();  // atsitiktinių generatorius
    static int[] analyzedNumber = {1000, 2000, 4000, 8000};//, 16000};
    public static double MIN_LAT = 0;
    public static double MAX_LAT = 90;
    public static double MIN_LON = -180;
    public static double MAX_LON = 180;

        private int memUsed;

    private void memDifference(String rem) {
        System.gc();
        System.gc();
        System.gc();
        long memTotal = Runtime.getRuntime().totalMemory();
        long memFree = Runtime.getRuntime().freeMemory();
        int memUsed1 = (int) (memTotal - memFree);
        System.out.println(String.format(rem + " užima atminties =\t%,6d ",
                (memUsed1 - memUsed)));
        memUsed = memUsed1;
    }
    
    public static void chooseTest(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generatePlaces(20);
        for(Place a: aSeries) Ks.oun(a);
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0,1,2,3,4,5,6);
        for(int n:analyzedNumber) simpleTest(n);
        //systematicTest();
        minmaxTest();
    }
    
    static void generatePlaces(int generN) {
        //Atsitiktinių generatorius
        String[][] am = { // galimų vietu duomenu masyvas
            {"KTU Statybos rumai", "akadem", "univer", "moksl", "architek", "statyb"},
            {"KTU SC", "akadem", "univer", "moksl", "IT", "spaus","komp"},
            {"KTU Elektronikos rumai", "akadem", "univer", "moksl", "base", "konf", "valg"},
            {"KTU Dizaino fukultetas", "akadem", "univer", "dizain", "menas", "daile", "mada"},
            {"KTU centriniai rūmai", "akadem", "univer", "atstovybe", "centras", "administracija"},
            {"PC Akropolis", "parduotuves", "batai", "rubai", "maistas", "elektronika", "maxima"},
            {"Žalgerio arena", "renginiai", "koncertai", "krepsinis", "spektakliai", "show"},
            {"Kauno sporto halė", "sportas", "atletika", "stadijonas", "koncertai"},
            {"PC Savas", "parduotuves", "batai", "rubai", "maistas", "sultys", "indai", "rimi"},
            {"PC Dainava", "parduotuves", "maistas", "turgus", "rimi"}
        };
        placeStack = new Place[generN];
        ag.setSeed(1957);
        double randomLat;
        double randomLon;
        for (int i = 0; i < generN; i++) {
            int ma = ag.nextInt(am.length);        // indeksas  0..
            int mo = ag.nextInt(am[ma].length - 1) + 1;// tipo indeksas 1..
            randomLat = MIN_LAT + (MAX_LAT - MIN_LAT) * ag.nextDouble();
            randomLon = MIN_LON + (MAX_LON -MIN_LON) * ag.nextDouble();
            placeStack[i] = new Place(am[ma][0], randomLat, randomLon, ag.nextInt(1500 - 10 + 1) + 10, am[ma][mo]);
        }
        Collections.shuffle(Arrays.asList(placeStack));
        aSeries.clear();
        for(Place a: placeStack) aSeries.add(a);
    }

        public static void simpleTest(int numberOfElements){
// Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        generatePlaces(numberOfElements);
        ListKTU<Place> aSeries2=(ListKTU<Place>)aSeries.clone();
        ListKTU<Place> aSeries3=(ListKTU<Place>)aSeries.clone();
        ListKTU<Place> aSeries4=(ListKTU<Place>)aSeries.clone();
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        aSeries.sort();
        long t3=System.nanoTime();
        aSeries2.sort(Place.usingDistance);
        long t4=System.nanoTime();
        aSeries3.sortBurbuliuku();
        long t5=System.nanoTime();
        aSeries4.sortBurbuliuku(Place.usingDistance);
        long t6=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", numberOfElements,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9 );
    }
        
    public static void minmaxTest(){
        Timekeeper tk = new Timekeeper(analyzedNumber);
        for (int number : analyzedNumber) {
           generatePlaces(number);
        ListKTU<Place> aSeries2 =(ListKTU<Place>)aSeries.clone();
        ListKTU<Place> aSeries3=(ListKTU<Place>)aSeries.clone();
        ListKTU<Place> aSeries4=(ListKTU<Place>)aSeries.clone();
        ListKTU<Place> aSeries5=(ListKTU<Place>)aSeries.clone();
        ListKTU<Place> aSeries6=(ListKTU<Place>)aSeries.clone();
        
        tk.start();
        aSeries.sort();
        tk.finish("NoCo");
        
        aSeries2.sort(Place.usingDistance);
        tk.finish("UsingCo");
        
        aSeries3.sortMinMax();
        tk.finish("MinMax");
        
        aSeries4.sortMinMax(Place.usingDistance);
        tk.finish("MinMaxC");
        
        aSeries5.sortBurbuliuku();
        tk.finish("BurBeCom");
        
        aSeries6.sortBurbuliuku(Place.usingDistance);
        tk.finish("BurSuCom");
        
        tk.seriesFinish();
        
        }
    }
    
    public static void systematicTest() {
// Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(analyzedNumber);
        for (int number : analyzedNumber) {
           generatePlaces(number);
           ListKTU<Place> aSeries2=(ListKTU<Place>)aSeries.clone();
           ListKTU<Place> aSeries3=(ListKTU<Place>)aSeries.clone();
           ListKTU<Place> aSeries4=(ListKTU<Place>)aSeries.clone();

//  Greitaveikos bandymai ir laiko matavimai
            tk.start();
            aSeries.sort();
            tk.finish("SysBeCom");
            aSeries2.sort(Place.usingDistance);
            tk.finish("SysSuCom");
            aSeries3.sortBurbuliuku();
            tk.finish("BurBeCom");
            aSeries4.sortBurbuliuku(Place.usingDistance);
            tk.finish("BurSuCom");
            tk.seriesFinish();
        }
    }
}
