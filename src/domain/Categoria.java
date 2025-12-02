package domain;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private String nombre;
    private List<Categoria> subcategorias = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public void addSubcategoria(Categoria c) {
        subcategorias.add(c);
    }

    public void addProducto(Producto p) {
        productos.add(p);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    // -----------------------------------------------------
    // MÉTODO RECURSIVO: cuenta categorías dentro del árbol
    // -----------------------------------------------------
    public int contarCategoriasRec() {
        int total = 1; // cuenta esta categoría

        for (Categoria sub : subcategorias) {
            total += sub.contarCategoriasRec(); // llamada recursiva
        }
        return total;
    }
}
