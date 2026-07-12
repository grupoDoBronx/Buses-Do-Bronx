package Ventanas;

import controlador.SistemaVentaPasajes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ListarViajes extends JDialog {
    private JPanel contentPane;
    private JButton ButtonBack;
    private JTable listaViajes;
    private JLabel viajes;

    public ListarViajes() {
        setContentPane(contentPane);
        setModal(true);

        ButtonBack.addActionListener(new ActionListener() {
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

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        viajes.setText("<html>" +
                "<span style='font-size:12px; font-weight:bold;'>VIAJES</span>" +
                "</html>");
        String[] columnas = {"fecha", "hora", "precio", "bus", "duracion", "auxiliar", "conductor", "T. salida", "T. llegada"};

        String[][] listViajes = SistemaVentaPasajes.getInstance().listViajes();

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };

        for (int i = 0; i < listViajes.length; i++) {
            modelo.addRow(listViajes[i]);
        }

        listaViajes.setModel(modelo);
        listaViajes.setPreferredScrollableViewportSize(
                new Dimension(listaViajes.getPreferredSize().width,
                        listaViajes.getRowHeight() * 5));
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] datos) {
        ListarViajes dialog = new ListarViajes();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
