package clases;

import exceptions.InvalidTipoException;

/**
 *
 * @author Ivan Diaz Vera
 */
public final class Objeto extends ElementoIdentificador {

    private int poder;
    private int cantidad;
    private tipoObjeto tipo;
    private int precio;

    /**
     * Constructor de Objeto
     *
     * @param nombre Variable de tipo String para indicar un nombre.
     * @param poder Variable de tipo entero que indica el poder del objeto actual.
     * @param cantidad Variable de tipo entero que indica la cantidad disponible del objeo.
     * @param descripcion Variable de tipo String para escribir una descripcion.
     * @param tipo Variable tipo que nos permite indicar el tipo de objeto y asi modificar ciertas funciones.
     * @param precio Variable de tipo entero que indica el precio de un objeto.
     */
    public Objeto(String nombre, String descripcion, int poder, int cantidad, String tipo, int precio) throws InvalidTipoException {
        super(nombre, descripcion);
        this.poder = poder;
        this.cantidad = cantidad;
        setTipo(tipo);
        this.precio = precio;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public enum tipoObjeto {
        OFENSIVO,
        CURATIVO
    };

    public tipoObjeto getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) throws InvalidTipoException {
        switch (tipo.toLowerCase()) {
            case "ofensivo":
                this.tipo = tipoObjeto.OFENSIVO;
                break;
            case "curativo":
                this.tipo = tipoObjeto.CURATIVO;
                break;
            default:
                throw new InvalidTipoException(tipo + " no es valido. Solo puede ser 'ofensivo' o 'curativo'");
        }
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
