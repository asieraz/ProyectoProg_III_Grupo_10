package gui;

import javax.swing.*; 

import domain.Categoria;
import domain.Departamento;
import domain.Producto;
import domain.Proveedor;
import domain.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

import BD.GestorBD;

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
        	ArrayList<Producto> productos = GestorBD.obtenerProductos();
        	new VentanaProductos(productos).setVisible(true);
        	dispose();
        });
        
        btnUsuarios.addActionListener(e -> {
        	ArrayList<Usuario> usuarios = GestorBD.obtenerUsuarios();
        	if (usuarios != null && !usuarios.isEmpty()) {
               
                Usuario u = usuarios.get(0);

                // Hay que pasar el usuario a un String[] por el formato en VentanaTablaUsuario
                String[] fila = {
                    u.getUsername(),                   
                    "",                                  
                    "",                                  
                    u.getPais(),                        
                    u.getContrasena()                    
                };

                new VentanaTablaUsuarios(fila).setVisible(true);
                dispose();
            }

        });
        
        btnDepartamento.addActionListener(e -> {
        	ArrayList<Departamento> departamentos = GestorBD.obtenerDepartamentos();    	
    		new VentanaDepartamento(departamentos).setVisible(true);       
        	dispose();
        });
        
        
        
        btnProveedores.addActionListener(e -> {
        	ArrayList<Proveedor> proveedores = GestorBD.obtenerProveedores();
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
