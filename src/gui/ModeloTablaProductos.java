package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Producto;

public class ModeloTablaProductos extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productos;
	
	public ModeloTablaProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return productos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return productos.get(rowIndex).getNombreProd();	
		}else {
			return productos.get(rowIndex).getPrecio();
		}
	}

	 @Override
	 public boolean isCellEditable(int rowIndex, int columnIndex) {
         return false; 
	    }
	
}
