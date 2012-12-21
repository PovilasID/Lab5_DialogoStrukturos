package lab5_povilassidaravicius;

import data.PlaceTest;
import data.SpeedTest;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import lab5_povilas_sidaravicius.ClientResponse.ClientResponse;

/*
 * Darbo Atlikimo tvarka - čia yra pradinė klasė:
 */
public class ValdymoModulis extends JFrame implements ActionListener {

    JButton jbPirmas = new JButton("Place Test");
    JButton jbAntras = new JButton("Speed Test");
    JButton jbTrecias = new JButton("User Interaction");

    public ValdymoModulis() {
        jbPirmas.addActionListener(this);
        jbAntras.addActionListener(this);
        jbTrecias.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setTitle("Greitas Pasirinkimas");
        arrangement();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object mygtukasX = event.getSource();
        if (mygtukasX == jbPirmas) {
            PlaceTest.chooseMethod();
        }
        if (mygtukasX == jbAntras) {
            SpeedTest.chooseTest();
        }
        if (mygtukasX == jbTrecias) {
            setVisible(false);
            ClientResponse.main();
        }
    }

    public void arrangement() {
        setLocation(200, 100);
        Container graphInterface = getContentPane();
        graphInterface.setBackground(Color.LIGHT_GRAY);
        graphInterface.setLayout(new BorderLayout());
        graphInterface.add(jbPirmas, BorderLayout.LINE_START);
        graphInterface.add(jbAntras, BorderLayout.LINE_END);
        graphInterface.add(jbTrecias, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        new ValdymoModulis();
    }
}
