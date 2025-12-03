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
import domain.Departamento;

public class VentanaDepartamento extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Departamento> departamentos;
	
	
	public VentanaDepartamento(ArrayList<Departamento> departamentos) {
	    this.departamentos = departamentos;
	    
	    ModeloTablaDepartamento modeloDep = new ModeloTablaDepartamento(this.departamentos);
	    JTable tablaDepartamento = new JTable(modeloDep);

	    tablaDepartamento.setShowGrid(false);
	    tablaDepartamento.getTableHeader().setReorderingAllowed(false);
	    tablaDepartamento.setRowHeight(30);

	    tablaDepartamento.getTableHeader().setDefaultRenderer((table, value, isSelected, hasFocus, row, column) -> {
	        JLabel result = new JLabel(value.toString());
	        result.setHorizontalAlignment(SwingConstants.CENTER);
	        result.setFont(new Font("Arial", Font.BOLD, 16));
	        result.setBackground(Color.LIGHT_GRAY);
	        result.setOpaque(true);
	        return result;
	    });

	    tablaDepartamento.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
	    	JButton button = new JButton(value.toString());
	        button.setFont(new Font("Arial", Font.PLAIN, 14));      
	        button.setHorizontalAlignment(SwingConstants.LEFT);     
	        button.setContentAreaFilled(false);                                          
	        return button;
	    });
	    

	    add(new javax.swing.JScrollPane(tablaDepartamento), BorderLayout.CENTER);
	    
	    // boton de vuelta al menu principal
	    JButton btnVolver = new JButton("Volver al Menú Principal");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnVolver.addActionListener(e -> {
            VentanaMenuPrincipal menu = new VentanaMenuPrincipal();
            menu.setVisible(true);
            dispose();
        });
        
        add(btnVolver, BorderLayout.SOUTH);

	    setTitle("Lista de Departamentos");
	    setSize(600, 400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	}
	
	
	

	public static void main(String[] args) {
		ArrayList<Departamento> departamentos = GestorBD.obtenerDepartamentos();
		SwingUtilities.invokeLater(() -> {
			VentanaDepartamento ventana = new VentanaDepartamento(departamentos);
            ventana.setVisible(true);
        });
	}
	
}