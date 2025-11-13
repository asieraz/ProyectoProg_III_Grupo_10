package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import domain.Departamento;


public class ModeloTablaDepartamento extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Departamento> departamentos;
	
	public ModeloTablaDepartamento(ArrayList<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return departamentos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
			return departamentos.get(rowIndex).getNombreDepartamento();	
	}

	public String getColumnName(int column) {
	    switch (column) {
	        case 0: return "Departamento";
	        default: return "";
	    }
	}
	
	 @Override
	 public boolean isCellEditable(int rowIndex, int columnIndex) {
         return false; 
	    }
	
}
