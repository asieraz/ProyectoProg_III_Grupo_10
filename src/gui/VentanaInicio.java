package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaInicio extends JFrame {
    private static final long serialVersionUID = 1L;

    protected Image fondo;

    // Añadimos el correo y la contraseña a este mapa para luego preguntarle si está
    protected static HashMap<String, String> mapa;

    // Cargamos los datos para ver luego si están en la base de datos
    public void cargarDatosCSV() {
        try (Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("data/personas.csv"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] campos = linea.split(";");
                if (campos.length >= 5) {
                    // Quitamos espacios y posibles caracteres invisibles de correo y contraseña
                    String correo = campos[3].trim().replaceAll("[\"\\r\\n]", "");
                    String contrasena = campos[4].trim().replaceAll("[\"\\r\\n]", "");
                    mapa.put(correo, contrasena);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VentanaInicio() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Ventana Inicio");
        setSize(317, 300);

        // Inicializamos el mapa
        mapa = new HashMap<>();
        cargarDatosCSV();

        this.setLocationRelativeTo(null);

        // Panel para la imagen de fondo
        JPanel mainPanel = new JPanel() {
            private static final long serialVersionUID = 510063825362016528L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                java.net.URL url = getClass().getClassLoader().getResource("img/fondo.jpg");
                if (url != null) {
                    ImageIcon fondo = new ImageIcon(url);
                    g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    System.out.println("No se encontró la imagen de fondo");
                }
            }
        };
        mainPanel.setLayout(null);

        // Creamos el menú y sus diferentes opciones
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Opciones de inicio");
        menuBar.add(fileMenu);

        JMenuItem inicio = new JMenuItem("Iniciar sesión");
        fileMenu.add(inicio);
        JMenuItem registro = new JMenuItem("Registrarse");
        fileMenu.add(registro);
        fileMenu.addSeparator();
        JMenuItem cerrar = new JMenuItem("Cerrar sesión");
        fileMenu.add(cerrar);
        fileMenu.addSeparator();
        JMenuItem salir = new JMenuItem("Salir");
        fileMenu.add(salir);

        // Panel para los botones
        JPanel panelBotones = new JPanel();

        // Etiqueta para el correo
        JLabel nombre = new JLabel("Correo:");
        nombre.setForeground(Color.black);
        nombre.setBounds(30, 50, 80, 25);
        mainPanel.add(nombre);

        // Campo de texto para el correo
        JTextField txt1 = new JTextField(20);
        txt1.setBounds(100, 50, 150, 25);
        mainPanel.add(txt1);

        // Etiqueta para la contraseña
        JLabel contraseña = new JLabel("Contraseña:");
        contraseña.setForeground(Color.black);
        contraseña.setBounds(30, 100, 80, 25);
        mainPanel.add(contraseña);

        // Campo de texto para la contraseña
        JPasswordField txt2 = new JPasswordField(20);
        txt2.setBounds(100, 100, 150, 25);
        mainPanel.add(txt2);

        // Botón para mostrar/ocultar contraseña
        ImageIcon foto1 = new ImageIcon(getClass().getClassLoader().getResource("img/fotnover.png"));
        JButton ocultar = new JButton(foto1);
        ocultar.setBounds(210, 100, 120, 30);
        ocultar.setBorderPainted(false);
        ocultar.setContentAreaFilled(false);
        ocultar.setFocusPainted(false);
        mainPanel.add(ocultar);

        // Botones de acción
        JButton botonAgregar = new JButton("Iniciar");
        JButton botonCerrar = new JButton("Cerrar");

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        panelBotones.add(botonAgregar);
        panelBotones.add(botonCerrar);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        // Abrir ventana de registro
        registro.addActionListener(e -> {
            dispose();
            VentanaRegistro ventana = new VentanaRegistro();
            ventana.setVisible(true);
        });

        // Botón salir
        salir.addActionListener(e -> {
            JLabel etiqueta1 = new JLabel("Quieres salir de la aplicación");
            Object[] message = {etiqueta1};
            int resultado = JOptionPane.showConfirmDialog(null, message, "Introduce los datos", JOptionPane.YES_NO_OPTION);
            if (resultado == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Botón cerrar
        botonCerrar.addActionListener(e -> dispose());

        // Mostrar/ocultar contraseña
        char valor = txt2.getEchoChar();
        ocultar.addActionListener(e -> {
            if (txt2.echoCharIsSet()) {
                txt2.setEchoChar((char) 0);
                ImageIcon fotoHide = new ImageIcon(getClass().getClassLoader().getResource("img/fotnover.png"));
                ocultar.setIcon(fotoHide);
            } else {
                txt2.setEchoChar(valor);
                ImageIcon fotoShow = new ImageIcon(getClass().getClassLoader().getResource("img/fotover.png"));
                ocultar.setIcon(fotoShow);
            }
        });

        // Iniciar sesión
        botonAgregar.addActionListener(e -> {
            String Correo = txt1.getText();
            String contra = new String(txt2.getPassword());
            if (mapa.containsKey(Correo) && mapa.get(Correo).equals(contra)) {
                JOptionPane.showMessageDialog(null, "Has iniciado sesión correctamente como usuario");
                SwingUtilities.invokeLater(VentanaMenuPrincipal::new);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        });

        // Confirmar salida al cerrar ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JLabel etiqueta1 = new JLabel("Quieres salir de la aplicación");
                Object[] message = {etiqueta1};
                int resultado = JOptionPane.showConfirmDialog(null, message, "Introduce los datos", JOptionPane.YES_NO_OPTION);
                if (resultado == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaInicio();
        System.out.println(mapa);
    }
}
