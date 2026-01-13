package clases;

import java.util.ArrayList;
import componentes.LabelCombateEvento;

/**
 *
 * @author Ivan Diaz Vera
 */
public final class Enemigo extends Personaje {

    /**
     * Constructor de Enemigo
     *
     * @param nombre Variable de tipo String para indicar un nombre.
     * @param descripcion Variable de tipo String para escribir una descripcion.
     * @param saludMaxima Variable de tipo entero que indica la salud maxima.
     * @param salud Variable de tipo entero que indica la salud actual.
     * @param mana Variable de tipo entero que indica el mana actual.
     * @param manaMaximo Variable de tipo entero que indica el mana maximo.
     * @param fuerza Variable de tipo entero que indica la fuerza actual.
     * @param magia Variable de tipo entero que indica la magia actual.
     * @param agilidad Variable de tipo entero que indica la agilidad actual.
     * @param defensa Variable de tipo entero que indica la defensa actual.
     * @param habilidadesArray Array con las habilidades.
     * @param dinero Variable de tipo entero que indica el dinero actual.
     * @param experiencia Variable de tipo entero que indica la experencia actual.
     */
    public Enemigo(String nombre, String descripcion, int saludMaxima, int salud, int manaMaximo, int mana, int fuerza,
            int magia, int agilidad, int defensa, ArrayList<Habilidad> habilidadesArray, int dinero, int experiencia) {
        super(nombre, descripcion, saludMaxima, salud, manaMaximo, mana, fuerza, magia, agilidad, defensa, habilidadesArray, dinero, experiencia);
    }

    /**
     * Funcion que permite utilizar una habilidad del enemigo gastando mana, el heroe recibe el daño de dicha habilidad.
     *
     * @param heroe Personaje que recibe el daño de una habilidad.
     * @param registro JLabel encargado de mostrar el texto en la pantalla de Lucha.
     */
    public void usarHabilidadesEnemigos(Heroe heroe, LabelCombateEvento registro) {
        int aleatorio = numeroAleatorio(0, 1);
        String resultadoUso = "<html><center><b>" + getNombre() + " utiliza una habilidad.<br>";
        if (getMana() >= getHabilidadesArray().get(aleatorio).getManaUtilizado()) {
            setMana(getMana() - getHabilidadesArray().get(aleatorio).getManaUtilizado());
            resultadoUso += (getHabilidadesArray().get(aleatorio).getNombre() + " inflige "
                    + getMagia() * getHabilidadesArray().get(aleatorio).getEspecial() + " puntos de daño.");
            resultadoUso += "</b></center></html>";
            registro.setText(resultadoUso);
            dañoHabilidades(heroe, aleatorio);
        } else {
            registro.setText("<html><center><b>¡No tiene suficiente mana!</b></center></html>");
        }
    }
}
