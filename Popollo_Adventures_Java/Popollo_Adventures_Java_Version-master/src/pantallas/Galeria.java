package pantallas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import componentes.Botones;
import componentes.Paneles;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Galeria extends Paneles {

    private Ventana ventana;

    public Galeria(Ventana ventana) {
        super();
        this.ventana = ventana;

        //Array de imagenes para mostrar, se puede hacer igualmente con un ArrayList.
        ArrayList<ImageIcon> imagenes = new ArrayList<ImageIcon>();
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/popollo.png"));
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/mystra.png"));
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/narcyl.png"));
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/tomberi.png"));
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/deviling.png"));
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/poring.png"));
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/nigromante.png"));
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/dlc.png"));
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/update.png"));
        imagenes.add(new ImageIcon("./recursos/imagenes/galeria/pulpoi.png"));

        //Añadiendo Botones
        Botones botonPopollo = new Botones("Popollo");
        botonPopollo.setBounds(548, 100, 165, 23);
        add(botonPopollo);

        Botones botonMystra = new Botones("Mystra");
        botonMystra.setBounds(548, 176, 165, 23);
        add(botonMystra);

        Botones botonNarcyl = new Botones("Narcyl");
        botonNarcyl.setBounds(548, 255, 165, 23);
        add(botonNarcyl);

        Botones botonTomberi = new Botones("Tomberi");
        botonTomberi.setBounds(548, 329, 165, 23);
        add(botonTomberi);

        Botones botonDeviling = new Botones("Deviling");
        botonDeviling.setBounds(548, 400, 165, 23);
        add(botonDeviling);

        Botones botonPoring = new Botones("Poring");
        botonPoring.setBounds(759, 100, 165, 23);
        add(botonPoring);

        Botones botonNigromante = new Botones("Nigromante");
        botonNigromante.setBounds(759, 176, 165, 23);
        add(botonNigromante);

        Botones botonGolem = new Botones("Golem");
        botonGolem.setBounds(759, 255, 165, 23);
        add(botonGolem);

        Botones botonGoblin = new Botones("Goblin");
        botonGoblin.setBounds(759, 329, 165, 23);
        add(botonGoblin);

        Botones botonPulpoi = new Botones("Pulpoi");
        botonPulpoi.setBounds(759, 400, 165, 23);
        add(botonPulpoi);

        Botones botonAtras = new Botones("Volver al inicio");
        botonAtras.setBounds(657, 459, 165, 23);
        add(botonAtras);

        //JLabel donde se muestra las imagenes de la galeria
        JLabel imagenGaleria = new JLabel("");
        imagenGaleria.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        imagenGaleria.setBounds(69, 33, 406, 466);
        imagenGaleria.setIcon(imagenes.get(0));
        add(imagenGaleria);

        //Eventos de boton. Cambian la imagen que esta dentro del JLabel imagenGaleria.
        botonPopollo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(0));
            }
        });

        botonMystra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(1));
            }
        });

        botonNarcyl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(2));
            }
        });

        botonTomberi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(3));
            }
        });

        botonDeviling.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(4));
            }
        });

        botonPoring.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(5));
            }
        });

        botonNigromante.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(6));
            }
        });

        botonGolem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(7));
            }
        });

        botonGoblin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(8));
            }
        });

        botonPulpoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagenGaleria.setIcon(imagenes.get(9));
            }
        });

        //Nos permite ir hacia la pantalla de inicio.
        botonAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Ventana.origenADestino(ventana, "galeria", "inicio", 0);
            }
        });
    }
}
