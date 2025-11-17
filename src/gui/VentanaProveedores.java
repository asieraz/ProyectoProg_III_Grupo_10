package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import domain.Proveedor;

public class VentanaProveedores extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable tabla;
    private DefaultTableModel model;
    private int filaMouseOver = -1;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField searchField;

    // Constructor principal
    public VentanaProveedores(ArrayList<Proveedor> proveedores) {
        super("Gestión de Proveedores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 400);
        setLocationRelativeTo(null);

        // Columnas
        String[] columnas = {"Código", "Nombre", "Código Postal", "Contraseña"};
        model = new DefaultTableModel(columnas, 0);

        // Cargar datos de la lista de proveedores
        if (proveedores != null) {
            for (Proveedor p : proveedores) {
                Object[] fila = {p.getCodProveedor(), p.getNombreProveedor(), p.getCodPostal(), p.getContrasena()};
                model.addRow(fila);
            }
        }

        // Inicializar tabla
        tabla = new JTable(model);
        sorter = new TableRowSorter<>(model);
        tabla.setRowSorter(sorter);

        // Campo de búsqueda
        searchField = new JTextField(20);
        searchField.setToolTipText("Buscar por nombre...");
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String query = searchField.getText().trim();
                if (query.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 1)); // filtra por columna Nombre
                }
            }
        });

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar por Nombre:"));
        searchPanel.add(searchField);

        JScrollPane scroll = new JScrollPane(tabla);
        add(searchPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Botón volver al menú
        JButton btnVolver = new JButton("Volver al Menú Principal");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.addActionListener(e -> {
            VentanaMenuPrincipal menu = new VentanaMenuPrincipal();
            menu.setVisible(true);
            dispose();
        });
        add(btnVolver, BorderLayout.SOUTH);

        // Resaltar fila al pasar el mouse
        tabla.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                filaMouseOver = tabla.rowAtPoint(e.getPoint());
                tabla.repaint();
            }
        });

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                filaMouseOver = -1;
                tabla.repaint();
            }
        });

        // Ocultar contraseña
        tabla.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel label = new JLabel("******");
                label.setOpaque(true);

                if (row == filaMouseOver) {
                    label.setBackground(Color.CYAN);
                } else if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                    label.setForeground(table.getSelectionForeground());
                } else {
                    label.setBackground(table.getBackground());
                    label.setForeground(table.getForeground());
                }
                return label;
            }
        });

        // Resaltado básico para todas las columnas
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (filaMouseOver == row && column != 3) { // no resaltar columna contraseña
                    comp.setBackground(Color.CYAN);
                } else if (!isSelected) {
                    comp.setBackground(Color.WHITE);
                    comp.setForeground(Color.BLACK);
                }
                return comp;
            }
        });

        setVisible(true);
    }

    // Método main de prueba
    public static void main(String[] args) {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        // Ejemplo de datos
        proveedores.add(new Proveedor(1, "Proveedor A", 28001, "1234"));
        proveedores.add(new Proveedor(2, "Proveedor B", 28002, "abcd"));

        SwingUtilities.invokeLater(() -> new VentanaProveedores(proveedores));
    }
} //aa
