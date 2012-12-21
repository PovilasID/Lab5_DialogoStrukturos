/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

/**
 *
 * @author PovilasSid
 */
public class PlaceArrayList {

    List<Place> pl = new ArrayList<Place>();
    ListIterator<Place> itr = pl.listIterator();

    public boolean addPlaceData(Place place) {
        return pl.add(place);
    }

    public void addPlaceData(String dataString) {
        Place place = new Place(dataString);
        addPlaceData(place);
    }

    public Place updatePlaceData(Place place, int index) {
        return pl.set(index, place);
    }

    public Place removePlaceData(int index) {
        return pl.remove(index);
    }

    public boolean removePlaceData(Place place) {
        return pl.remove(place);
    }

    public Place getPlaceData(int index) {
        return pl.get(index);
    }
    
    public int getPlaceDataSize(){
        return pl.size();
    }
    
    public boolean removeAllPlaceData() {
        return pl.removeAll(pl);
    }

    public boolean doesPlaceExist(Place professional) {
        if (pl.contains(professional)) {
            return true;
        }
        return false;
    }

    public void load(File fName) {
        if (fName.length() == 0) {
            return;
        }
        try {
            //(new File(Ks.getDataFolder())).mkdir();
            // String fN = Ks.getDataFolder() + File.separatorChar + fName;
            BufferedReader fReader =
                    new BufferedReader(new FileReader(fName));
            String dLine;
            while ((dLine = fReader.readLine()) != null) {
                addPlaceData(dLine);
            }
            fReader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void addToTable(DefaultTableModel tableModel) {
        for (Place p : pl) {
            tableModel.addRow(p.toStringArray());
        }
    }

    public void isvedimas(JTextArea taLaukas) {
        taLaukas.append("\n Ivesti Elementai:\n");
        for (Place p : pl) {
            ouSwn(p, taLaukas);
        }
    }

    static public void ouSwn(Object obj, JTextArea ta) {
        ta.append(obj.toString() + "\n");
    }
    
        public void save(File fName, PlaceArrayList bli) {
        PrintWriter fWriter = null;
        try {
            fWriter = new PrintWriter(new FileWriter(fName));
            Iterator itr = pl.iterator();
            while (itr.hasNext()) {
                Place pairs = (Place) itr.next();
                fWriter.println(pairs.toString());
                itr.remove();
            }
            fWriter.close();
        } catch (IOException e) {
            System.exit(0);
        }
    }
}
