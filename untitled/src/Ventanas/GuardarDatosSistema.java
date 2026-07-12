package Ventanas;

import javax.swing.*;
import java.awt.event.*;

public class GuardarDatosSistema extends JDialog {
    private JPanel contentPane;
    private JButton leerDatosInicialesButton;
    private JButton guardarDatosDelSistemaButton;
    private JButton recuperarDatosDelSitemaButton;



    public GuardarDatosSistema() {
        setContentPane(contentPane);
        setModal(true);
        pack();
        setLocationRelativeTo(null);




        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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
