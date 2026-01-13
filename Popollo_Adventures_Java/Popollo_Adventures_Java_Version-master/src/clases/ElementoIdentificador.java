package clases;

/**
 *
 * @author Ivan Diaz Vera
 */
public abstract class ElementoIdentificador {

    private String nombre;
    private String descripcion;

    /**
     * Constructor de ElementoIdenfiticador
     *
     * @param nombre Variable de tipo String para indicar un nombre.
     * @param descripcion Variable de tipo String para escribir una descripcion.
     */
    public ElementoIdentificador(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
