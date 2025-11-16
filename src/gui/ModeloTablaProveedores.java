package gui;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import domain.Proveedor;

public class ModeloTablaProveedores extends AbstractTableModel {

    private List<Proveedor> proveedores;
    private final String[] columnas = {"Codigo", "Nombre", "Codigo Postal", "Contraseña"};

    public ModeloTablaProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    @Override
    public int getRowCount() {
        return proveedores.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnas[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedor p = proveedores.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getCodProveedor();
            case 1: return p.getNombreProveedor();
            case 2: return p.getCodPostal();
            default: return null;
        }
    }

    public void agregarProveedor(Proveedor p) {
        proveedores.add(p);
        fireTableRowsInserted(proveedores.size() - 1, proveedores.size() - 1);
    }

    public void eliminarProveedor(int fila) {
        if (fila >= 0 && fila < proveedores.size()) {
            proveedores.remove(fila);
            fireTableRowsDeleted(fila, fila);
        }
    }

    public Proveedor getProveedorAt(int fila) {
        if (fila >= 0 && fila < proveedores.size()) {
            return proveedores.get(fila);
        }
        return null;
    }

    public void actualizarTabla() {
        fireTableDataChanged();
    }
}
