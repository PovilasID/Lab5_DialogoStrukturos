package studijosKTU;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 * @author eimutis
 */
public class ListKTUx<Data extends DataKTU> extends ListKTU<Data>
        implements Cloneable{
    private Data baseObj;       // bazinis objektas skirtas naujų kūrimui

    public ListKTUx(Data baseObj) { // konstruktorius su bazinio objekto
        this.baseObj = baseObj;     // fiksacija dėl naujų elementų kūrimo
    }
    public void add(String dataString) {        // sukuria elementą iš String
        add((Data) baseObj.create(dataString)); // ir įdeda jį į pabaigą
    }
    
    public void load(String fName) {//suformuoja sąrašą iš fName failo
        clear();
        if(fName.length()==0)return;
        if(baseObj==null){          // elementų kūrimui reikalingas baseObj
            Ks.ern("Naudojant load-metodą, "+
                "reikia taikyti konstruktorių = new ListKTU(new Data())");
            System.exit(0);
        }
        try {
            (new File(Ks.getDataFolder())).mkdir();
            String fN = Ks.getDataFolder() + File.separatorChar + fName;
            BufferedReader fReader =
                    new BufferedReader(new FileReader(new File(fN)));
            String dLine;
            while ((dLine = fReader.readLine()) != null) {
                add(dLine);
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            Ks.ern("Duomenų failas " + fName + " nerastas");
//            System.exit(0);
        } catch (IOException e) {
            Ks.ern("Failo " + fName + " skaitymo klaida");
            System.exit(0);
        }
    }
    
    public void load(File fName) {//suformuoja sąrašą iš fName failo
        clear();
        if(fName.length()==0)return;
        if(baseObj==null){          // elementų kūrimui reikalingas baseObj
            Ks.ern("Naudojant load-metodą, "+
                "reikia taikyti konstruktorių = new ListKTU(new Data())");
            System.exit(0);
        }
        try {
            //(new File(Ks.getDataFolder())).mkdir();
           // String fN = Ks.getDataFolder() + File.separatorChar + fName;
            BufferedReader fReader =
                    new BufferedReader(new FileReader(fName));
            String dLine;
            while ((dLine = fReader.readLine()) != null) {
                add(dLine);
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            Ks.ern("Duomenų failas " + fName + " nerastas");
//            System.exit(0);
        } catch (IOException e) {
            Ks.ern("Failo " + fName + " skaitymo klaida");
            System.exit(0);
        }
    }
    
    public void load(File fName, JTextArea ta) {//suformuoja sąrašą iš fName failo
        clear();
        if(fName.length()==0)return;
        if(baseObj==null){          // elementų kūrimui reikalingas baseObj
            Ks.ern("Naudojant load-metodą, "+
                "reikia taikyti konstruktorių = new ListKTU(new Data())");
            System.exit(0);
        }
        try {
            BufferedReader fReader =  new BufferedReader(new FileReader(fName));
            String dLine;
            ta.append("\n     Duomenys is failo <" + fName.getName() + ">\n");
            while ((dLine = fReader.readLine()) != null) {
                ta.append(dLine + "\n");
                add(dLine);
            }
            fReader.close();
        } catch (IOException e) {
            Ks.ern("Failo " + fName + " skaitymo klaida");
            System.exit(0);
        }
    }
            
    public void save(File fName) {    // išsaugoja sąrašą faile fName
        PrintWriter fWriter = null;     // tekstiniu formatu
        try {                           // tinkamu vėlesniam skaitymui
            fWriter = new PrintWriter(new FileWriter (fName));
            for (DataKTU d1 = super.get(0); d1 != null; d1=super.getNext()) {
                fWriter.println(d1.toString());
            }
            fWriter.close();
        } catch (IOException e) {
            Ks.ern("!!! Klaida formuojant " + fName + " failą.");
            System.exit(0);
        }
    }
        public void save(String fName) {    // išsaugoja sąrašą faile fName
        PrintWriter fWriter = null;     // tekstiniu formatu
        try {                           // tinkamu vėlesniam skaitymui
            // jei vardo nėra - failas neformuojamas
            if (fName.equals("")) return;

            String fN = Ks.getDataFolder() + File.separatorChar + fName;
            fWriter = new PrintWriter(new FileWriter (new File(fN)));
            for (DataKTU d1 = super.get(0); d1 != null; d1=super.getNext()) {
                fWriter.println(d1.toString());
            }
            fWriter.close();
        } catch (IOException e) {
            Ks.ern("!!! Klaida formuojant " + fName + " failą.");
            System.exit(0);
        }
    }
    public void println() {  // sąrašas spausdinamas į Ks.oln("");
        int eilNr=0;
        if (super.isEmpty()){
            Ks.oun("Sąrašas yra tuščias");
        }else
           for (DataKTU d1 = super.get(0); d1 != null; d1=super.getNext()) {
           String printData=String.format("%3d: %s ", eilNr++, d1.toString());
           String badData=d1.validate();
           Ks.oun (printData + badData);
        }
        Ks.oun("****** Bendras elementų kiekis yra "+super.size());
    }
    public void println(String title) { // spausdinant galima nurodyti antraštę
        Ks.oun("========"+title+"=======");
        println();
        Ks.oun("======== Sąrašo pabaiga =======");
    }
    
    public void isvedimas(JTextArea taLaukas, String title) { // spausdinant galima nurodyti antraštę
        Ks.ouSwn("========"+title+"=======", taLaukas);
        isvedimas(taLaukas);
        Ks.ouSwn("======== Sąrašo pabaiga =======", taLaukas);
    }
    
    public void isvedimas(JTextArea taLaukas) {
        int eilNr=0;
        if (super.isEmpty()){
            Ks.oun("Sąrašas yra tuščias");
        }else
           for (DataKTU d1 = super.get(0); d1 != null; d1=super.getNext()) {
           String printData=String.format("%3d: %s ", eilNr++, d1.toString());
           String badData=d1.validate();
           Ks.ouSwn (printData + badData, taLaukas);
        }
    }
    @Override
    public ListKTUx<Data> clone(){
       ListKTUx<Data> cl= (ListKTUx<Data>)super.clone();
       cl.baseObj = this.baseObj;
       return cl;
    }
    
    static public void ouSwn(Object obj, JTextArea ta) {
		ta.append(obj.toString() + "\n");
    }
}
