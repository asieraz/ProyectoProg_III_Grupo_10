package gui;


import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;



import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import domain.Producto;

public class VentanaProductos extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productos;
	
	
	public VentanaProductos(ArrayList<Producto> productos) {
	    this.productos = productos;
	    
	    // Crear el modelo y la tabla
	    ModeloTablaProductos modeloProd = new ModeloTablaProductos(this.productos);
	    JTable tablaProductos = new JTable(modeloProd);

	    // Configurar la tabla
	    tablaProductos.setShowGrid(false);
	    tablaProductos.getTableHeader().setReorderingAllowed(false);
	    tablaProductos.setRowHeight(30);

	    // Cabecera personalizada
	    tablaProductos.getTableHeader().setDefaultRenderer((table, value, isSelected, hasFocus, row, column) -> {
	        JLabel result = new JLabel(value.toString());
	        result.setHorizontalAlignment(SwingConstants.CENTER);
	        result.setFont(new Font("Arial", Font.BOLD, 16));
	        result.setBackground(Color.LIGHT_GRAY);
	        result.setOpaque(true);
	        return result;
	    });

	    // Renderizado de celdas
	    tablaProductos.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
	        JLabel result = new JLabel(value.toString(), SwingConstants.CENTER);
	        result.setFont(new Font("Arial", Font.PLAIN, 14));
	        return result;
	    });

	    // ✅ Agregar la tabla dentro de un JScrollPane
	    add(new javax.swing.JScrollPane(tablaProductos));

	    // ✅ Configurar la ventana
	    setTitle("Lista de Productos");
	    setSize(600, 400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null); // Centrar en pantalla
	}
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		ArrayList<Producto> productos = inicializarProductos();
		SwingUtilities.invokeLater(() -> {
        	VentanaProductos ventana = new VentanaProductos(productos);
            ventana.setVisible(true);
        });
	}
		

	
	
	
	private static ArrayList<Producto> inicializarProductos(){
		ArrayList<Producto> productos = new ArrayList<>();
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"patata",22,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		productos.add(new Producto(2,"Jabon",11,1,7));
		
		
		return productos;
		
	}

}
