/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import data.Place;
import javax.swing.JOptionPane;

/**
 *
 * @author PovilasSid
 */
public class PlaceHashMap implements Serializable {

    Map<String, Place> pl = new HashMap<String, Place>();


    public boolean doesPlaceDataExist(String eMail) {
        return pl.containsKey(eMail);
    }

    public void addPlaceData(Place place, String key) {
        pl.put(key, place);
    }

    public void addPlaceData(Place place) {
        pl.put(place.getType(), place);
    }

    public void addPlaceData(String dataString) {
        addPlaceData(new Place(dataString));
    }

    public Place removePlaceData(String eMail) {
        return pl.remove(eMail);
    }

    public void removeAllPlaceData() {
        pl.clear();
    }

    public Place getPlaceData(String eMail) {
        return pl.get(eMail);
    }

    public void isvedimas(JTextArea taLaukas) {
        taLaukas.append("\n Ivesti Elementai:\n");
        printMap(taLaukas);
    }

    static public void ouSwn(Object obj, JTextArea ta) {
        ta.append(obj.toString() + "\n");
    }

    public void printMap(JTextArea taLaukas) {
        Iterator itra = pl.values().iterator();
        while (itra.hasNext()) {
            Place pairs = (Place) itra.next();
            ouSwn(pairs, taLaukas);
            //itra.remove(); // avoids a ConcurrentModificationException
        }
    }

    public void load(File fName) {
        if (fName.length() == 0) {
            return;
        }
        try {
            BufferedReader fReader = new BufferedReader(new FileReader(fName));
            String dLine;
            while ((dLine = fReader.readLine()) != null) {
                addPlaceData(dLine);
                loadObject("temp.obj");
            }
            fReader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void addToTable(DefaultTableModel tableModel) {
        Iterator it = pl.values().iterator();
        while (it.hasNext()) {
            Place pairs = (Place) it.next();
            tableModel.addRow(pairs.toStringArray());
            //it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public void save(File fName, PlaceHashMap bli) {
        PrintWriter fWriter = null;
        try {
            fWriter = new PrintWriter(new FileWriter(fName));
            Iterator itr = pl.values().iterator();
            while (itr.hasNext()) {
                Place pairs = (Place) itr.next();
                saveObject(pairs, "temp.obj");
                fWriter.println(pairs.toString());
                //itr.remove();
            }
            for (Map.Entry<String, Place> entry : pl.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
            fWriter.close();
        } catch (IOException e) {
            System.exit(0);
        }
    }
    
    private void saveObject(Object obj, String fName) {
      try {
         FileOutputStream fos = new FileOutputStream(fName);
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(obj);
         oos.flush();
         oos.close();
         fos.close();
      } catch (IOException ex) {
      }
   }
    private Object loadObject(String fName) {
      Object obj = null;
      try {
        FileInputStream fis = new FileInputStream(fName);
        ObjectInputStream oin = new ObjectInputStream(fis);
        obj = oin.readObject();
      } catch (IOException ex) {
          return null;
      } catch (ClassNotFoundException ex) {
          return null;
      }
      return obj;
    }
}
