package gui;


import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import BD.GestorBD;
import domain.Producto;

public class VentanaProductos extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productos;
	
	
	public VentanaProductos(ArrayList<Producto> productos) {
	    this.productos = productos;
	    
	    ModeloTablaProductos modeloProd = new ModeloTablaProductos(this.productos);
	    JTable tablaProductos = new JTable(modeloProd);

	    tablaProductos.setShowGrid(false);
	    tablaProductos.getTableHeader().setReorderingAllowed(false);
	    tablaProductos.setRowHeight(30);

	    tablaProductos.getTableHeader().setDefaultRenderer((table, value, isSelected, hasFocus, row, column) -> {
	        JLabel result = new JLabel(value.toString());
	        result.setHorizontalAlignment(SwingConstants.CENTER);
	        result.setFont(new Font("Arial", Font.BOLD, 16));
	        result.setBackground(Color.LIGHT_GRAY);
	        result.setOpaque(true);
	        return result;
	    });

	    tablaProductos.getColumnModel().getColumn(0).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
	        JButton button = new JButton(value.toString());
	        button.setFont(new Font("Arial", Font.PLAIN, 14));      
	        button.setHorizontalAlignment(SwingConstants.LEFT);     
	        button.setContentAreaFilled(false);                                          
	        return button;
	    });

	    add(new javax.swing.JScrollPane(tablaProductos), BorderLayout.CENTER);
	    
	    // boton de vuelta al menu principal
	    JButton btnVolver = new JButton("Volver al Menú Principal");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnVolver.addActionListener(e -> {
            VentanaMenuPrincipal menu = new VentanaMenuPrincipal();
            menu.setVisible(true);
            dispose();
        });
        
        add(btnVolver, BorderLayout.SOUTH);

	    setTitle("Lista de Productos");
	    setSize(600, 400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	}
	
	
	
	

	public static void main(String[] args) {
		
                
        ArrayList<Producto> productos = GestorBD.obtenerProductos();
  		SwingUtilities.invokeLater(() -> {
        VentanaProductos ventana = new VentanaProductos(productos);
           ventana.setVisible(true);
           });

        
	}
		

	
	
	
}
