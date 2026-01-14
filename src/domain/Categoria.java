package domain;

import java.util.ArrayList;

import domain.Producto;

@SuppressWarnings("unused")
public class Categoria {
    private String nombre;
    private ArrayList<Categoria> subCategorias = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>(); // <-- lista de productos

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    // Añadir subcategoría
    public void agregarSubCategoria(Categoria c) {
        subCategorias.add(c);
    }

    // Añadir producto
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // Obtener subcategorías
    public ArrayList<Categoria> getSubCategorias() {
        return subCategorias;
    }

    // Obtener productos
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public String getNombre() {
        return nombre;
    }

    // Método recursivo para mostrar la categoría y productos
    public void mostrarRecursivamente(int nivel) {
        System.out.println(" ".repeat(nivel * 2) + nombre);
        for (Producto p : productos) {
            System.out.println(" ".repeat(nivel * 2 + 2) + p.getNombreProd());
        }
        for (Categoria sub : subCategorias) {
            sub.mostrarRecursivamente(nivel + 1);
        }
    }
}
