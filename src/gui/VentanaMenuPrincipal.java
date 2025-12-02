package gui;

import javax.swing.*;

import domain.Categoria;
import domain.Departamento;
import domain.Producto;
import domain.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class VentanaMenuPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private final Color COLOR_FONDO = new Color(245, 245, 245);
    private final Color COLOR_PRIMARIO = new Color(33, 150, 243);
    private final Color COLOR_BOTON = new Color(25, 118, 210);
    private final Color COLOR_TEXTO_BOTON = Color.WHITE;

	protected Categoria raizCategorias;

	public VentanaMenuPrincipal() {
		
		setTitle("Supermercado - Menu Principal");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Fondo
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout());
        
        // Barra de menú simple
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Cerrar");
        JMenuItem salir = new JMenuItem("Cerrar aplicacion");
        
        salir.addActionListener(e -> cerrarAplicacion());

        menuArchivo.add(salir);
        menuBar.add(menuArchivo);
        

        JMenu ayuda = new JMenu("Ayuda");
        JMenuItem acerca = new JMenuItem("Acerca del sistema");
        acerca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaAcercaDelSistema acercaDelSistema = new VentanaAcercaDelSistema();
				acercaDelSistema.setVisible(true);
				
			}
		});
    

        ayuda.add(acerca);
        menuBar.add(ayuda);

        setJMenuBar(menuBar);
        
        // Título principal con el logo de nuestro super
        JLabel titulo = new JLabel("SuperMarket", new ImageIcon("resources/img/logoSuper.png"), SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        // Para poner el titulo debajo del logo
        titulo.setVerticalTextPosition(JLabel.BOTTOM);
        titulo.setHorizontalTextPosition(JLabel.CENTER);
        
        add(titulo, BorderLayout.NORTH);
        

        // Panel central profesional
        JPanel panel = new JPanel(new GridLayout(2, 3, 20, 20));
        panel.setBorder(new EmptyBorder(20, 40, 40, 40));
        panel.setBackground(COLOR_FONDO);

        // Botones del menu
        JButton btnProductos = new JButton("Gestión de Productos");
        btnProductos.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnProductos.setBackground(COLOR_BOTON);
        btnProductos.setForeground(COLOR_TEXTO_BOTON);
        btnProductos.setFocusPainted(false);
        btnProductos.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(COLOR_PRIMARIO, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        
        JButton btnUsuarios = new JButton("Usuarios");
        btnUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnUsuarios.setBackground(COLOR_BOTON);
        btnUsuarios.setForeground(COLOR_TEXTO_BOTON);
        btnUsuarios.setFocusPainted(false);
        btnUsuarios.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(COLOR_PRIMARIO, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        
        
        JButton btnDepartamento = new JButton("Departamentos");
        btnDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDepartamento.setBackground(COLOR_BOTON);
        btnDepartamento.setForeground(COLOR_TEXTO_BOTON);
        btnDepartamento.setFocusPainted(false);
        btnDepartamento.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(COLOR_PRIMARIO, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        
        JButton btnProveedores = new JButton("Proveedores");
        btnProveedores.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnProveedores.setBackground(COLOR_BOTON);
        btnProveedores.setForeground(COLOR_TEXTO_BOTON);
        btnProveedores.setFocusPainted(false);
        btnProveedores.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(COLOR_PRIMARIO, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        
        panel.add(btnProductos);
        panel.add(btnUsuarios);
        panel.add(btnDepartamento);
        panel.add(btnProveedores);

        add(panel, BorderLayout.CENTER);
        
        // Conexiones de los botones al resto de ventanas
        btnProductos.addActionListener(e -> {
        	ArrayList<Producto> productos = BD.CargarDatos.cargarProductos();
        	new VentanaProductos(productos).setVisible(true);
        	dispose();
        });
        
        btnUsuarios.addActionListener(e -> {
        	new VentanaTablaUsuarios(null).setVisible(true);
        	dispose();

        });
        
        btnDepartamento.addActionListener(e -> {
        	ArrayList<Departamento> departamentos = BD.CargarDatos.cargarDepartamentos();    	
    		new VentanaDepartamento(departamentos).setVisible(true);       
        	dispose();
        });
        
        
        
        btnProveedores.addActionListener(e -> {
        	//ArrayList<Proveedor> proveedores = BD.CargarDatos.cargarProveedores();
        	ArrayList<Proveedor> proveedores = new ArrayList<>();
            // Ejemplo de datos
            proveedores.add(new Proveedor(1, "Álvaro Ma.", 28001, "123498fg"));
            proveedores.add(new Proveedor(2, "Peter Mu.", 28002, "abcd27"));
            proveedores.add(new Proveedor(3, "Lucas Landa", 28002, "QWERT87"));
            proveedores.add(new Proveedor(4, "Luis O.", 28001, "abcdefghijk"));
            proveedores.add(new Proveedor(5, "Alberto D.", 28002, "abcdefg"));
            proveedores.add(new Proveedor(6, "Ana Perez", 28003, "abcd3477"));
            proveedores.add(new Proveedor(7, "Ana María", 28003, "abcd1235"));
            proveedores.add(new Proveedor(8, "Lucas Prieto", 28004, "abddc33"));
    		new VentanaProveedores(proveedores).setVisible(true);       
        	dispose();
        });
        
        
    }
	
	private void cerrarAplicacion() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Desea cerrar la aplicación?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
        
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new VentanaMenuPrincipal().setVisible(true));
	}

}
