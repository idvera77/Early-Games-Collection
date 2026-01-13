package exceptions;

/**
 *
 * @author Ivan Diaz Vera
 */
public class InvalidMoralException extends Exception {

    //No permite ningun String en moral que no sea legal, neutral o caotico. Esto afecta a los Npc.
    public InvalidMoralException(String m) {
        super(m);
    }
}
