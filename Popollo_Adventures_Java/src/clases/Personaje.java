package clases;

import java.util.ArrayList;
import componentes.LabelCombateEvento;

/**
 *
 * @author Mystra77
 */
public abstract class Personaje extends ElementoIdentificador {

    private int saludMaxima;
    private int salud;
    private int manaMaximo;
    private int mana;
    private int fuerza;
    private int magia;
    private int agilidad;
    private int defensa;
    private ArrayList<Habilidad> habilidadesArray;
    private int dinero;
    private int experiencia;

    /**
     * Constructor de Personaje
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
     * @param experiencia Puntos de experiencia.
     */
    public Personaje(String nombre, String descripcion, int saludMaxima, int salud, int manaMaximo, int mana, int fuerza,
            int magia, int agilidad, int defensa, ArrayList<Habilidad> habilidadesArray, int dinero, int experiencia) {
        super(nombre, descripcion);
        this.saludMaxima = saludMaxima;
        this.salud = salud;
        this.manaMaximo = manaMaximo;
        this.mana = mana;
        this.fuerza = fuerza;
        this.magia = magia;
        this.agilidad = agilidad;
        this.defensa = defensa;
        this.habilidadesArray = habilidadesArray;
        this.dinero = dinero;
        this.experiencia = experiencia;
    }

    //Getters y Setters
    public int getSaludMaxima() {
        return saludMaxima;
    }

    public void setSaludMaxima(int saludMaxima) {
        this.saludMaxima = saludMaxima;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getManaMaximo() {
        return manaMaximo;
    }

    public void setManaMaximo(int manaMaximo) {
        this.manaMaximo = manaMaximo;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public int getAgilidad() {
        return agilidad;
    }

    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public ArrayList<Habilidad> getHabilidadesArray() {
        return habilidadesArray;
    }

    public void setHabilidadesArray(ArrayList<Habilidad> habilidadesArray) {
        this.habilidadesArray = habilidadesArray;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    //FUNCIONES
    /**
     * Funcion para determinar el daño causado, defensa - el daño
     *
     * @param dañoEntrante Parametro externo que indica el daño que recibe el personajeX
     */
    public void daño(int dañoEntrante) {
        int inflige = defensa - dañoEntrante;
        if (inflige <= 0) {
            this.salud -= -inflige;
        } else {
            this.salud -= 0;
        }
    }

    /**
     * Funcion que multiplica en 2 su atributo defensivo.
     */
    public void Bloqueo() {
        this.defensa = defensa * 2;
    }

    /**
     * Funcion que divide en 2 su atributo defensivo.
     */
    public void BloqueoOff() {
        this.defensa = defensa / 2;
    }

    /**
     * Muestra las estadisticas principales en combate
     *
     * @param registro JLabel que guarda y muestra la informacion.
     */
    public void mostrarAtributosCombate(LabelCombateEvento registro) {
        registro.setText("<html><center><b>"
                + "Fuerza: " + getFuerza()
                + "<br/> Magia: " + getMagia()
                + "<br/> Defensa: " + getDefensa()
                + "<br/> Agilidad: " + getAgilidad()
                + "</b></center></html>");
    }

    /**
     * La salud se iguala con la saludMaxima, es decir realiza una curacion completa.
     */
    public void restablecerSalud() {
        setSalud(getSaludMaxima());
    }

    /**
     * La salud se iguala con la saludMaxima, es decir realiza una curacion completa y a su vez hace lo mismo con el
     * mana.
     */
    public void restablecerCompleto() {
        setSalud(getSaludMaxima());
        setMana(getManaMaximo());
    }

    /**
     * Funcion para calcular el daño realizado por una habilidad, se multiplica el valor de magia por el valor Especial
     * de una habilidad.
     *
     * @param objetivo Indica el enemigo que recibe el daño.
     * @param numero Indica la habilidad seleccionada.
     */
    public void dañoHabilidades(Personaje objetivo, int numero) {
        int dañoHabilidad = getMagia() * getHabilidadesArray().get(numero).getEspecial();
        objetivo.setSalud(objetivo.getSalud() - dañoHabilidad);
    }

    /**
     * Funcion para golpear con ataques fisicos. Dependiendo de la agilidad de ambos cambian los resultados.
     *
     * @param objetivo Es el personaje que recibe el daño.
     * @param registro Guarda la informacion para mostrarla en un JLabelText
     */
    public void atacar(Personaje objetivo, LabelCombateEvento registro) {
        int dañar;
        int aleatorio, aleatorio1;
        if (getAgilidad() > objetivo.getAgilidad()) {
            aleatorio = numeroAleatorio(0, 3);
            if (aleatorio == 0) {
                dañar = getFuerza() * 2;
                objetivo.daño(dañar);
                registro.setText("<html><center><b>¡GOLPE CRITICO!<br>" + getNombre()
                        + " inflige " + getFuerza() * 2 + " puntos de daño.<br>"
                        + objetivo.getNombre() + " bloquea " + objetivo.getDefensa() + " puntos de daño."
                        + "</center></b></html>");
            } else {
                dañar = getFuerza();
                objetivo.daño(dañar);
                registro.setText("<html><center><b>" + getNombre()
                        + " inflige " + getFuerza() + " puntos de daño.<br>"
                        + objetivo.getNombre() + " bloquea " + objetivo.getDefensa() + " puntos de daño."
                        + "</center></b></html>");
            }
        } else if (getAgilidad() == objetivo.getAgilidad()) {
            dañar = getFuerza();
            objetivo.daño(dañar);
            registro.setText("<html><center><b>" + getNombre()
                    + " inflige " + getFuerza() + " puntos de daño.<br>"
                    + objetivo.getNombre() + " bloquea " + objetivo.getDefensa() + " puntos de daño."
                    + "</center></b></html>");
        } else {
            aleatorio1 = numeroAleatorio(0, 3);
            if (aleatorio1 == 0) {
                registro.setText("<html><center><b>¡Ataque Fallado!</center></b></html>");
            } else {
                dañar = getFuerza();
                objetivo.daño(dañar);
                registro.setText("<html><center><b>" + getNombre()
                        + " inflige " + getFuerza() + " puntos de daño.<br>"
                        + objetivo.getNombre() + " bloquea " + objetivo.getDefensa() + " puntos de daño."
                        + "</center></b></html>");
            }
        }
    }

    /*
     * Funcion que nos ayuda a generar numeros aleatorios necesarios para calculos de daño. La incluyo aqui ya que la usan ambos personajes aunque tambien la uso en mas ocasiones.
     */
    public static int numeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }
}
