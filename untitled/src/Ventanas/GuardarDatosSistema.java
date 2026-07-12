package Ventanas;

import excepciones.SVPException;
import controlador.*;
import javax.swing.*;
import java.awt.event.*;

public class GuardarDatosSistema extends JDialog {
    SistemaVentaPasajes sistem = SistemaVentaPasajes.getInstance();
    private JPanel contentPane;
    private JButton leerDatosInicialesButton;
    private JButton guardarDatosDelSistemaButton;
    private JButton recuperarDatosDelSitemaButton;



    public GuardarDatosSistema() {
        setContentPane(contentPane);
        setModal(true);
        pack();
        setLocationRelativeTo(null);

        leerDatosInicialesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readDatosIniciales();
            }
        });
        guardarDatosDelSistemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDatosSistema();
            }
        });
        recuperarDatosDelSitemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readDatosSistema();
            }
        });


        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    private void readDatosIniciales(){
        try {
            sistem.readDatosIniciales();
        }catch (SVPException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void saveDatosSistema(){
        try {
            sistem.saveDatosSistema();
        }catch (SVPException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void readDatosSistema(){
        try {
            sistem.readDatosSistema();
        } catch (SVPException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        GuardarDatosSistema dialog = new GuardarDatosSistema();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
