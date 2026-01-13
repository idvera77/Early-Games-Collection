package exceptions;

/**
 *
 * @author Ivan Diaz Vera
 */
public class InvalidTipoException extends Exception {

    //No permite ningun String en tipo que no sea ofensivo o curativo. Esto afecta a los objetos y habilidades.
    public InvalidTipoException(String m) {
        super(m);
    }
}
