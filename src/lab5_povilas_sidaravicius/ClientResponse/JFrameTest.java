/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5_povilas_sidaravicius.ClientResponse;


import data.PlaceHashMap;
import data.PlaceTreeMap;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import studijosKTU.Timekeeper;

/**
 *
 * @author PovilasSid
 */
public class JFrameTest extends JFrame implements ActionListener {

    //static PlaceArrayList plist = new PlaceArrayList();
    static PlaceHashMap plist = new PlaceHashMap(); //.getInstance();
    static PlaceTreeMap plistt = new PlaceTreeMap();
    JPanel buttonPanel = new JPanel();
    JPanel tablePanel = new JPanel();
    JFrame f = new JFrame("Table model test");
    JButton load1 = new JButton("Load");
    JButton addItemButtn = new JButton("Add Item");
    JButton clean = new JButton("Clear");
    JButton exit = new JButton("Exit");
    JButton convert = new JButton("Convert");
    JButton memdiff = new JButton("MemDifference");
    JButton timekeeper = new JButton("Timekeeper");
    JTable jTab = new JTable();
    DefaultTableModel tableModel;

    public static void main(String[] args) {
        new JFrameTest();
    }

    public JFrameTest() {

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        load1.addActionListener(this);
        addItemButtn.addActionListener(this);
        clean.addActionListener(this);
        convert.addActionListener(this);
        memdiff.addActionListener(this);
        timekeeper.addActionListener(this);
        exit.addActionListener(this);

        f.setTitle("Testavimas");

        f.setSize(585, 400);
        f.setLocation(300, 200);

        buttonInt();
        tableInt();

        f.getContentPane().setLayout(new BorderLayout());

        f.getContentPane().add(tablePanel, BorderLayout.CENTER);
        f.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        f.setVisible(true);

    }

    public void buttonInt() {
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setPreferredSize(new Dimension(100, 25));
        buttonPanel.add(load1);
        buttonPanel.add(addItemButtn);
        buttonPanel.add(clean);
        buttonPanel.add(convert);
        buttonPanel.add(memdiff);
        buttonPanel.add(timekeeper);
        buttonPanel.add(exit);

    }

    public void tableInt() {

        jTab.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String[]{
                    "Name", "Lat", "Long", "sparks", "type"
                }));

        jTab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(jTab), BorderLayout.CENTER);
    }

    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        Object buttn = event.getSource();
        if (buttn == exit) {
            System.exit(0);
        } else if (buttn == addItemButtn) {
            try {
                AddItem.main();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JFrameTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(JFrameTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JFrameTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (buttn == convert) {
            //plistt.addPlaceTree(plist);
        } else if (buttn == memdiff){
            new ObjektuAtmintiesTyrimas().atmintiesSunaudojimoTyrimas();
        } else if (buttn == timekeeper){
            int[] kiekis = {500, 200,100,400,600};
            Timekeeper tk = new Timekeeper(kiekis);
            tk.start();
            tk.startAfterPause();
            tk.seriesFinish();
            tk.finish("Pabaiga");
        }else if (buttn == clean) {
            tableModel.getDataVector().removeAllElements();
            tableModel.fireTableDataChanged();
            plist.removeAllPlaceData();
        } else if (buttn == load1 || buttn == addItemButtn || buttn == clean) {
            JFileChooser fc = new JFileChooser(".");
            // "." tam, kad rodytų projekto katalogą
            fc.setDialogTitle("Atidaryti failą skaitymui");
            fc.setApproveButtonText("Atidaryti");
            int rez = fc.showOpenDialog(null);
            if (rez == JFileChooser.APPROVE_OPTION) {

                File f1 = fc.getSelectedFile();
                plist.load(f1);
                jTab.setModel(tableModel =
                        new DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                            "Quantity", "Type", "Date", "Color", "Price"
                        }));
                plist.addToTable(tableModel);
            } else if (rez == JFileChooser.CANCEL_OPTION) {
                // rodyti pagrindinio lango centre
                JOptionPane.showMessageDialog(f,
                        "Skaitymo atsisakyta (paspaustas ESC arba Cancel)",
                        "Skaitymas - rašymas", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
