package gui;

import domain.Categoria;
import domain.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class VentanaAcercaDelSistema extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread hiloReloj;

    public VentanaAcercaDelSistema() {
        super((Frame) null, "Acerca del Sistema", true);

        setSize(500, 450);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // ----------------- PANEL DE INFORMACIÓN -----------------
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelSuperior.setBackground(new Color(240, 248, 255));

        JLabel titulo = new JLabel("📌 Sistema de Gestión de Supermercados – SGM 1.0");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(titulo);
        panelSuperior.add(Box.createVerticalStrut(10));

        JLabel autores = new JLabel("Autores: Alumno 1, Alumno 2");
        autores.setFont(new Font("Arial", Font.PLAIN, 14));
        autores.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(autores);
        panelSuperior.add(Box.createVerticalStrut(10));

        JLabel tecnologias = new JLabel("Tecnologías: Java 17, Swing, Hilos, Recursividad, Logging");
        tecnologias.setFont(new Font("Arial", Font.PLAIN, 14));
        tecnologias.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(tecnologias);

        add(panelSuperior, BorderLayout.NORTH);

        // ----------------- CATEGORÍAS Y RECURSIVIDAD -----------------
        Categoria raizCategorias = crearCategoriasEjemplo();

        JTextArea areaCategorias = new JTextArea();
        areaCategorias.setEditable(false);
        areaCategorias.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaCategorias.setText("Categorías registradas (recursivo):\n\n" + mostrarCategoriasRec(raizCategorias, 0));

        JScrollPane scrollPane = new JScrollPane(areaCategorias);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Árbol de categorías"));
        add(scrollPane, BorderLayout.CENTER);

        // ----------------- HILO: RELOJ EN VIVO -----------------
        JLabel labelReloj = new JLabel("Tiempo activo del sistema: 0 s");
        labelReloj.setFont(new Font("Arial", Font.BOLD, 14));
        labelReloj.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelReloj, BorderLayout.SOUTH);

        AtomicInteger segundos = new AtomicInteger(0);
        hiloReloj = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    segundos.getAndIncrement();
                    SwingUtilities.invokeLater(() ->
                            labelReloj.setText("Tiempo activo del sistema: " + segundos.get() + " s")
                    );
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                // hilo detenido
            }
        });
        hiloReloj.start();

        // ----------------- BOTÓN CERRAR -----------------
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setBackground(new Color(100, 149, 237));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> {
            hiloReloj.interrupt();
            dispose();
        });
        add(btnCerrar, BorderLayout.NORTH);

        setVisible(true);
    }

    // ----------------- MÉTODO PARA CREAR CATEGORÍAS DE EJEMPLO -----------------
    private Categoria crearCategoriasEjemplo() {
        Categoria raiz = new Categoria("Supermercado");

        Categoria lacteos = new Categoria("Lácteos");
        lacteos.addProducto(new Producto(1, "Leche", 1.5f, 1, 100));
        lacteos.addProducto(new Producto(2, "Queso", 2.5f, 1, 101));

        Categoria bebidas = new Categoria("Bebidas");
        bebidas.addProducto(new Producto(3, "Refresco", 1.0f, 2, 102));
        bebidas.addProducto(new Producto(4, "Agua", 0.8f, 2, 103));

        Categoria congelados = new Categoria("Congelados");
        Categoria helados = new Categoria("Helados");
        helados.addProducto(new Producto(5, "Vainilla", 1.2f, 3, 104));
        congelados.addSubcategoria(helados);

        raiz.addSubcategoria(lacteos);
        raiz.addSubcategoria(bebidas);
        raiz.addSubcategoria(congelados);

        return raiz;
    }

    // ----------------- MÉTODO RECURSIVO PARA MOSTRAR CATEGORÍAS -----------------
    private String mostrarCategoriasRec(Categoria categoria, int nivel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nivel; i++) sb.append("  "); // sangría
        sb.append("- ").append(categoria.getNombre()).append("\n");

        // mostrar productos
        for (Producto p : categoria.getProductos()) {
            for (int i = 0; i <= nivel; i++) sb.append("  ");
            sb.append("* ").append(p.getNombreProd()).append(" ($").append(p.getPrecio()).append(")\n");
        }

        for (Categoria sub : categoria.getSubcategorias()) {
            sb.append(mostrarCategoriasRec(sub, nivel + 1));
        }

        return sb.toString();
    }

    // ----------------- DEMO -----------------
    public static void main(String[] args) {
        new VentanaAcercaDelSistema();
    }
}
