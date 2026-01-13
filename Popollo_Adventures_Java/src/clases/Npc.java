package clases;

import exceptions.InvalidMoralException;
import pantallas.Ventana;
import javax.swing.JLabel;

/**
 *
 * @author Ivan Diaz Vera
 */
public final class Npc extends ElementoIdentificador {

    private tipoMoral moral;

    /**
     * Constructor de Npc
     *
     * @param nombre Variable de tipo String para indicar un nombre.
     * @param descripcion Variable de tipo String para escribir una descripcion.
     * @param moral Variable tipo que nos permite indicar la moral del npc y asi modificar ciertas funciones.
     */
    public Npc(String nombre, String descripcion, String moral) throws InvalidMoralException {
        super(nombre, descripcion);
        setMoral(moral);
    }

    public enum tipoMoral {
        LEGAL,
        NEUTRAL,
        CAOTICO
    }

    public tipoMoral getMoral() {
        return moral;
    }

    public void setMoral(String moral) throws InvalidMoralException {
        switch (moral.toLowerCase()) {
            case "legal":
                this.moral = tipoMoral.LEGAL;
                break;
            case "neutral":
                this.moral = tipoMoral.NEUTRAL;
                break;
            case "caotico":
                this.moral = tipoMoral.CAOTICO;
                break;
            default:
                throw new InvalidMoralException(moral + " no es valida. Solo puede ser 'legal', 'neutral' o 'caotico'.");
        }
    }

    /**
     * Funcion que compara la reputacion del heroe y la moral del npc para a�adir dialogos o dar recompensas al heroe.
     *
     * @param npc Del cual conseguiremos beneficios o dialogos dependiendo de su moral.
     * @param heroe Del cual sacaremos una reputacion y obtendra beneficios.
     * @param registro JLabel que guarda la informacion y la muestra.
     */
    public static void recompensasNpc(Npc npc, Heroe heroe, JLabel registro) {
        //sonido
        String sonidoPremio = "./recursos/sonidos/Premio.wav";

        String tipo = String.valueOf(npc.getMoral());
        if (tipo.equals("LEGAL")) {
            if (heroe.getReputacion() > 25 & heroe.getReputacion() <= 50) {
                heroe.setSaludMaxima(heroe.getSaludMaxima() + 15);
                heroe.setSalud(heroe.getSalud() + 15);
                heroe.setDinero(heroe.getDinero() + 1000);
                registro.setText("<html><center>* Recibes 1000 monedas de oro * <br>* La salud máxima aumenta 15 puntos *</center></html>");
            }
            if (heroe.getReputacion() > 50) {
                heroe.setSaludMaxima(heroe.getSaludMaxima() + 30);
                heroe.setSalud(heroe.getSalud() + 30);
                heroe.setDinero(heroe.getDinero() + 2000);
                heroe.setDefensa(heroe.getDefensa() + 5);
                registro.setText("<html><center>* Recibes 2000 monedas de oro *<br>* La salud máxima aumenta 30 puntos *<br>* El atributo defensa aumenta 5 puntos *</center></html>");
            }
        }
        if (tipo.equals("NEUTRAL")) {
            if (heroe.getReputacion() <= 25 && heroe.getReputacion() >= -25) {
                heroe.setSaludMaxima(heroe.getSaludMaxima() + 10);
                heroe.setSalud(heroe.getSalud() + 10);
                heroe.setManaMaximo(heroe.getManaMaximo() + 5);
                heroe.setMana(heroe.getMana() + 5);
                heroe.setFuerza(heroe.getFuerza() + 5);
                registro.setText("<html><center>* La salud máxima aumenta 10 puntos *<br>* El mana máximo aumenta 5 puntos *<br>* El atributo fuerza aumenta 5 puntos *</center></html>");
            }
        }
        if (tipo.equals("CAOTICO")) {
            if (heroe.getReputacion() < -25 && heroe.getReputacion() >= -50) {
                heroe.setManaMaximo(heroe.getManaMaximo() + 10);
                heroe.setMana(heroe.getMana() + 10);
                heroe.setDinero(heroe.getDinero() + 1000);
                registro.setText("<html><center>* Recibes 1000 monedas de oro *<br>* El mana máximo aumenta 10 puntos *</center></html>");

            }
            if (heroe.getReputacion() < -50) {
                heroe.setManaMaximo(heroe.getManaMaximo() + 10);
                heroe.setMana(heroe.getMana() + 10);
                heroe.setMagia(heroe.getMagia() + 1);
                heroe.setDinero(heroe.getDinero() + 1500);
                registro.setText("<html><center>* Recibes 1500 monedas de oro *<br>* El mana máximo aumenta 10 puntos *<br>* El atributo magia aumenta 1 punto *</center></html>");
            }
        }
        Ventana.comenzarSonido(sonidoPremio);
    }
}
