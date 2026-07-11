package vista;

import controlador.ControladorEmpresas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ListarEmpresas extends JDialog {
    private JPanel contentPane;
    private JButton ButtonBack;
    private JTable listaEmpresas;
    private JLabel empresas;

    public ListarEmpresas(String[] datos) {
        setContentPane(contentPane);
        setModal(true);

        ButtonBack.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        empresas.setText("<html>" +
                "<span style='font-size:12px; font-weight:bold;'>EMPRESAS</span>" +
                "</html>");

        cargarTablaEmpresas();
    }

    private void cargarTablaEmpresas() {
        String[] columnas = {"Nombre", "Rut"};
        String[][] datosEmpresas = ControladorEmpresas.getInstance().listEmpresas();

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };

        if (datosEmpresas != null) {
            for (String[] fila : datosEmpresas) {
                modeloTabla.addRow(fila);
            }
        }

        listaEmpresas.setModel(modeloTabla);
        listaEmpresas.setPreferredScrollableViewportSize(
                new Dimension(listaEmpresas.getPreferredSize().width,
                        listaEmpresas.getRowHeight() * 5)
        );
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] datos) {
        ListarEmpresas dialog = new ListarEmpresas(datos);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
