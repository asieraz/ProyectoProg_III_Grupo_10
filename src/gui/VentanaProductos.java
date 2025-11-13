package gui;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		
		ModeloTablaProductos modeloProd = new ModeloTablaProductos(this.productos);
		
		
		JTable tablaProductos = new JTable(modeloProd);
		
		tablaProductos.setShowGrid(false);
		
		tablaProductos.getTableHeader().setReorderingAllowed(false);
		
		tablaProductos.setRowHeight(64);
		
		tablaProductos.getTableHeader().setDefaultRenderer((table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			result.setHorizontalAlignment(SwingConstants.CENTER);
			result.setFont(new Font("Arial", Font.BOLD, 18));
			result.setBackground(Color.WHITE);
			result.setOpaque(true);
			
			return result;
		});	
		
		
		TableCellRenderer cellRenderer = ((table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel();
			result.setHorizontalAlignment(SwingConstants.CENTER);

			// Configuración del renderizado para la primera columna (Horas)
			if (column == 0) {
				result.setFont(new Font("Arial", Font.BOLD, 18));
				result.setText((String) value);
			
			}else {
				result.setFont(new Font("Arial", Font.BOLD, 18));
				result.setText((String) value);
			}
			return result;
		});		
		
		
		tablaProductos.setDefaultRenderer(Object.class, cellRenderer);
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
