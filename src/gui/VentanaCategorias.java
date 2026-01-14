package gui;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;


import domain.Categoria;
import domain.Producto;

public class VentanaCategorias extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Categoria raiz;
    private JTree tree;
    private DefaultTreeModel treeModel;

    public VentanaCategorias(Categoria raiz) {
        this.raiz = raiz;

        setTitle("Gestión de Categorías");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear nodo raíz del árbol
        DefaultMutableTreeNode rootNode = crearNodo(raiz);
        treeModel = new DefaultTreeModel(rootNode);
        tree = new JTree(treeModel);
        tree.setShowsRootHandles(true);

        JScrollPane scroll = new JScrollPane(tree);
        add(scroll, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 4, 10, 10));

        JButton btnAgregar = new JButton("Agregar categoría");
        JButton btnAgregarSub = new JButton("Agregar subcategoría");
        JButton btnEliminar = new JButton("Eliminar categoría");
        JButton btnMostrarProd = new JButton("Mostrar productos");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnAgregarSub);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMostrarProd);

        add(panelBotones, BorderLayout.SOUTH);

        // --- EVENTOS ---
        
        // Agregar categoría al raíz
        btnAgregar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre de la categoría:");
            if (nombre != null && !nombre.isEmpty()) {
                Categoria nueva = new Categoria(nombre);
                raiz.agregarSubCategoria(nueva);
                DefaultMutableTreeNode nodoNuevo = new DefaultMutableTreeNode(nombre);
                ((DefaultMutableTreeNode) treeModel.getRoot()).add(nodoNuevo);
                treeModel.reload();
            }
        });

        // Agregar subcategoría al nodo seleccionado
        btnAgregarSub.addActionListener(e -> {
            TreePath seleccion = tree.getSelectionPath();
            if (seleccion == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una categoría.");
                return;
            }
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) seleccion.getLastPathComponent();
            String nombre = JOptionPane.showInputDialog(this, "Nombre de la subcategoría:");
            if (nombre != null && !nombre.isEmpty()) {
                Categoria catSeleccion = buscarCategoria(raiz, nodo.toString());
                if (catSeleccion != null) {
                    Categoria sub = new Categoria(nombre);
                    catSeleccion.agregarSubCategoria(sub);
                    nodo.add(new DefaultMutableTreeNode(nombre));
                    treeModel.reload();
                }
            }
        });

        // Eliminar categoría seleccionada
        btnEliminar.addActionListener(e -> {
            TreePath seleccion = tree.getSelectionPath();
            if (seleccion == null || seleccion.getLastPathComponent() == treeModel.getRoot()) {
                JOptionPane.showMessageDialog(this, "Seleccione una subcategoría válida para eliminar.");
                return;
            }
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) seleccion.getLastPathComponent();
            DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo.getParent();

            // Eliminar del árbol de categorías
            Categoria catPadre = buscarCategoria(raiz, padre.toString());
            if (catPadre != null) {
                catPadre.getSubCategorias().removeIf(c -> c.getNombre().equals(nodo.toString()));
            }

            padre.remove(nodo);
            treeModel.reload();
        });

        // Mostrar productos de la categoría seleccionada (recursivo)
        btnMostrarProd.addActionListener(e -> {
            TreePath seleccion = tree.getSelectionPath();
            if (seleccion == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una categoría.");
                return;
            }
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) seleccion.getLastPathComponent();
            Categoria cat = buscarCategoria(raiz, nodo.toString());
            if (cat != null) {
                StringBuilder sb = new StringBuilder();
                mostrarProductosRecursivo(cat, sb, 0);
                JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "No hay productos.");
            }
        });
    }

    // Crear nodo recursivamente desde Categoria
    private DefaultMutableTreeNode crearNodo(Categoria c) {
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(c.getNombre());
        for (Categoria sub : c.getSubCategorias()) {
            nodo.add(crearNodo(sub));
        }
        return nodo;
    }

    // Buscar objeto Categoria por nombre (recursivo)
    private Categoria buscarCategoria(Categoria c, String nombre) {
        if (c.getNombre().equals(nombre)) return c;
        for (Categoria sub : c.getSubCategorias()) {
            Categoria resultado = buscarCategoria(sub, nombre);
            if (resultado != null) return resultado;
        }
        return null;
    }

    // Mostrar productos recursivamente
    private void mostrarProductosRecursivo(Categoria c, StringBuilder sb, int nivel) {
        for (Producto p : c.getProductos()) {
            sb.append(" ".repeat(nivel * 2)).append(p.getNombreProd()).append("\n");
        }
        for (Categoria sub : c.getSubCategorias()) {
            mostrarProductosRecursivo(sub, sb, nivel + 1);
        }
    }
}
