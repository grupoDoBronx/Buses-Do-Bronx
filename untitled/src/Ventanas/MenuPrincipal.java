package Ventanas;

import javax.swing.*;
import java.awt.event.*;

public class MenuPrincipal extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton persistenciasButton;
    private JButton ventaPasajesButton;
    private JButton creacionViajeButton;
    private JButton listadoButton;
    private JButton listadoVIajesButton;
    private JButton listadoBusesEmpresaButton;

    private static MenuPrincipal instance = null;

    public static MenuPrincipal getInstance() {
        if (instance == null) {
            instance = new MenuPrincipal();
        }
        return instance;

    }
    public MenuPrincipal() {
        setContentPane(contentPane);
        setModal(true);
        pack();

        ventaPasajesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //le tocaba hacer al seba

            }
        });

        creacionViajeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //le tocaba hacer al javier
            }
        });

        listadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarEmpresas empresas = new ListarEmpresas();
                empresas.setVisible(true);
            }
        });
        listadoVIajesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarViajes viajes = new ListarViajes();
                viajes.setVisible(true);
            }
        });
        listadoBusesEmpresaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        persistenciasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuardarDatosSistema persistencias = new GuardarDatosSistema();
                persistencias.setVisible(true);
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        MenuPrincipal dialog = new MenuPrincipal();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
