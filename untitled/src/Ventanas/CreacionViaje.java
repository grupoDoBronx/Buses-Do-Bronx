package Ventanas;

import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class CreacionViaje extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton persistenciasButton;
    private JButton ventaPasajesButton;
    private JButton creacionViajeButton;
    private JButton listadoButton;
    private JButton listadoVIajesButton;
    private JButton listadoBusesEmpresaButton;
    private JTextField fechaViaje;
    private JTextField horaViaje;
    private JTextField duracionViaje;
    private JTextField precioViaje;
    private JComboBox<String> listaBuses;
    private JComboBox<String> listaAuxiliares;
    private JComboBox<String> listaConductores;

    private static CreacionViaje instance = null;

    public static CreacionViaje getInstance() {
        if (instance == null) {
            instance = new CreacionViaje();
        }
        return instance;

    }
    public CreacionViaje() {
        setContentPane(contentPane);
        setModal(true);
        pack();

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void leerConductores() {
        try {
            Scanner sc = new Scanner(new File("untitled/datos/tripulantes.txt"));

            listaConductores.removeAllItems();

            while (sc.hasNextLine()) {
                listaConductores.addItem(sc.nextLine());
            }

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leerAuxiliares() {
        try {
            Scanner sc = new Scanner(new File("untitled/datos/tripulantes.txt"));

            listaAuxiliares.removeAllItems();

            while (sc.hasNextLine()) {
                listaAuxiliares.addItem(sc.nextLine());
            }

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leerBuses() {
        try {
            Scanner sc = new Scanner(new File("untitled/datos/buses.txt"));

            listaBuses.removeAllItems();

            while (sc.hasNextLine()) {
                listaBuses.addItem(sc.nextLine());
            }

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        CreacionViaje dialog = new CreacionViaje();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
