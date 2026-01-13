package pantallas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import componentes.Botones;
import componentes.LabelPrincipal;
import componentes.Paneles;
import clases.Heroe;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Descanso extends Paneles {

    private Ventana ventana;
    private Heroe heroe;
    private final JLabel fraseNarcyl;
    private final LabelPrincipal mostrarDinero;

    public Descanso(Ventana ventana) {
        super();
        this.ventana = ventana;
        this.heroe = ventana.heroe;

        //Archivos de sonido
        String sonidoCuracion = "./recursos/sonidos/Curaciones.wav";
        String sonidoNoMoney = "./recursos/sonidos/NoMoney.wav";

        Random r = new Random();
        String[] consejoFrase = {"Aquí estudie yo", "Diles que vas de mi parte", "Me encanta este sitio"};

        fraseNarcyl = new JLabel();
        fraseNarcyl.setHorizontalAlignment(SwingConstants.CENTER);
        fraseNarcyl.setHorizontalTextPosition(SwingConstants.CENTER);
        fraseNarcyl.setOpaque(false);
        fraseNarcyl.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        fraseNarcyl.setText(consejoFrase[r.nextInt(consejoFrase.length)]);
        fraseNarcyl.setBounds(231, 114, 238, 40);
        add(fraseNarcyl);

        mostrarDinero = new LabelPrincipal();
        mostrarDinero.setFont(new Font("Bahnschrift", Font.BOLD, 15));
        mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
        mostrarDinero.setBounds(10, 29, 109, 40);
        add(mostrarDinero);

        //Añadiendo botones
        Botones botonCuracion = new Botones("Curar heridas - 250");
        botonCuracion.setBounds(374, 377, 262, 23);
        add(botonCuracion);

        Botones botonRecuperacionCompleta = new Botones("Recuperación completa - 500");
        botonRecuperacionCompleta.setBounds(374, 411, 262, 23);
        add(botonRecuperacionCompleta);

        Botones botonAtras = new Botones("Volver al mapa");
        botonAtras.setBounds(767, 487, 215, 23);
        add(botonAtras);

        //Eventos de boton. Llaman a la funcion puntoDescanso de la clase Heroe y una vez realizada vuelve a mostrar el dinero restante.
        botonCuracion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                heroe.puntoDescanso(0, sonidoCuracion, sonidoNoMoney);
                pulsarDescanso();
            }
        });

        botonRecuperacionCompleta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                heroe.puntoDescanso(1, sonidoCuracion, sonidoNoMoney);
                pulsarDescanso();
            }
        });

        //Nos permite ir hacia la pantalla principal.
        botonAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ventana.origenADestino(ventana, "descanso", "principal", 0);
            }
        });

        //Imagen de fondo
        JLabel imagenDescanso = new JLabel("");
        imagenDescanso.setBounds(0, 0, 1008, 536);
        imagenDescanso.setIcon(new ImageIcon("./recursos/imagenes/descanso.png"));
        add(imagenDescanso);
    }

    /**
     * Funcion que cambia el dialogo de nuestra querida Narcyl y modifica el dinero.
     */
    public void pulsarDescanso() {
        mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
        fraseNarcyl.setText("Ahora toca continuar ^_^");
    }
}
