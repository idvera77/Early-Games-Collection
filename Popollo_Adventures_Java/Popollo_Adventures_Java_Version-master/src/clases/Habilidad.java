package clases;

import exceptions.InvalidTipoException;

/**
 *
 * @author Ivan Diaz Vera
 */
public final class Habilidad extends ElementoIdentificador {

    private int especial;
    private int manaUtilizado;
    private tipoHechizo tipo;

    /**
     * Constructor de Habilidad
     *
     * @param nombre Variable de tipo String para indicar un nombre.
     * @param descripcion Variable de tipo String para escribir una descripcion.
     * @param especial Variable de tipo entero que indica el atributo especial.
     * @param manaUtilizado Variable de tipo entero que indica el mana utilizado por esta habilidad.
     * @param tipo Variable tipo que nos permite indicar el tipo de hechizo y asi modificar ciertas funciones.
     */
    public Habilidad(String nombre, String descripcion, int especial, int manaUtilizado, String tipo) throws InvalidTipoException {
        super(nombre, descripcion);
        this.especial = especial;
        this.manaUtilizado = manaUtilizado;
        setTipo(tipo);
    }

    public int getEspecial() {
        return especial;
    }

    public void setEspecial(int especial) {
        this.especial = especial;
    }

    public int getManaUtilizado() {
        return manaUtilizado;
    }

    public void setManaUtilizado(int manaUtilizado) {
        this.manaUtilizado = manaUtilizado;
    }

    public tipoHechizo getTipo() {
        return tipo;
    }

    public enum tipoHechizo {
        OFENSIVO,
        CURATIVO
    };

    public void setTipo(String tipo) throws InvalidTipoException {
        switch (tipo.toLowerCase()) {
            case "ofensivo":
                this.tipo = tipoHechizo.OFENSIVO;
                break;
            case "curativo":
                this.tipo = tipoHechizo.CURATIVO;
                break;
            default:
                throw new InvalidTipoException(tipo + " no es valido. Solo puede ser 'ofensivo' o 'curativo'");
        }
    }
}
