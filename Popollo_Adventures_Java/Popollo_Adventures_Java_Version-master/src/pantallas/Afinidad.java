package pantallas;

import componentes.Botones;
import componentes.LabelCombateEvento;
import componentes.Paneles;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Afinidad extends Paneles {

    private Ventana ventana;
    private final Timer timer;
    private JProgressBar barraCarga;
    private final Botones botonSalir;
    private final JLabel dialogoNarcyl, dialogoTomberi, dialogoMystra, estadoNarcyl, estadoTomberi, estadoMystra;
    public LabelCombateEvento recompensaHeroe;

    public Afinidad(Ventana ventana) {
        super();
        this.ventana = ventana;

        //Sonido
        Ventana.comenzarFondo("./recursos/sonidos/Afinidad.wav");

        //Boton
        botonSalir = new Botones("Volver al mapa");
        botonSalir.setVisible(false);
        botonSalir.setBounds(396, 283, 215, 23);
        add(botonSalir);

        recompensaHeroe = new LabelCombateEvento();
        recompensaHeroe.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        recompensaHeroe.setVisible(false);
        recompensaHeroe.setBounds(191, 103, 614, 226);
        add(recompensaHeroe);

        dialogoNarcyl = new JLabel("New label");
        dialogoNarcyl.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        dialogoNarcyl.setHorizontalTextPosition(SwingConstants.CENTER);
        dialogoNarcyl.setHorizontalAlignment(SwingConstants.CENTER);
        dialogoNarcyl.setIcon(new ImageIcon("./recursos/imagenes/globo.png"));
        dialogoNarcyl.setVisible(false);
        dialogoNarcyl.setBounds(94, 0, 498, 122);
        add(dialogoNarcyl);

        dialogoTomberi = new JLabel("New label");
        dialogoTomberi.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        dialogoTomberi.setHorizontalTextPosition(SwingConstants.CENTER);
        dialogoTomberi.setHorizontalAlignment(SwingConstants.CENTER);
        dialogoTomberi.setIcon(new ImageIcon("./recursos/imagenes/globo.png"));
        dialogoTomberi.setVisible(false);
        dialogoTomberi.setBounds(191, 120, 498, 122);
        add(dialogoTomberi);

        dialogoMystra = new JLabel("New label");
        dialogoMystra.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        dialogoMystra.setHorizontalTextPosition(SwingConstants.CENTER);
        dialogoMystra.setHorizontalAlignment(SwingConstants.CENTER);
        dialogoMystra.setIcon(new ImageIcon("./recursos/imagenes/globo.png"));
        dialogoMystra.setVisible(false);
        dialogoMystra.setBounds(359, 177, 498, 122);
        add(dialogoMystra);

        estadoNarcyl = new JLabel("New label");
        estadoNarcyl.setVisible(false);
        estadoNarcyl.setBounds(688, 0, 70, 75);
        add(estadoNarcyl);

        estadoTomberi = new JLabel("New label");
        estadoTomberi.setVisible(false);
        estadoTomberi.setBounds(779, 127, 70, 75);
        add(estadoTomberi);

        estadoMystra = new JLabel("New label");
        estadoMystra.setVisible(false);
        estadoMystra.setBounds(928, 146, 70, 75);
        add(estadoMystra);

        //Imagen de fondo
        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(0, 0, 1008, 536);
        imagenFondo.setIcon(new ImageIcon("./recursos/imagenes/afinidad.png"));
        add(imagenFondo);

        barraCarga = new JProgressBar();
        add(barraCarga);

        //Dependiendo del % mostrara u ocultara los siguientes mensajes.
        //Dependiendo de la reputacion de nuestro heroe llamara a un evento u otro.
        ActionListener updateBarraCargar = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                barraCarga.setValue((barraCarga.getValue() + 1));
                if (ventana.heroe.getReputacion() > 25 && ventana.heroe.getReputacion() <= 50) {
                    eventoLegal();
                } else if (ventana.heroe.getReputacion() > 50) {
                    eventoLegalMas();
                } else if (ventana.heroe.getReputacion() <= 25 && ventana.heroe.getReputacion() >= -25) {
                    eventoNeutral();
                } else if (ventana.heroe.getReputacion() < -25 && ventana.heroe.getReputacion() >= -50) {
                    eventoCaotico();
                } else {
                    eventoCaoticoMas();
                }
            }
        };

        timer = new Timer(300, updateBarraCargar);
        timer.start();

        //Evento Boton
        botonSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Ventana.pararFondo();
                Ventana.origenADestino(ventana, "afinidad", "principal", 0);
            }
        });
    }

    /**
     * Funcion en la que muestra el dialogo en un determinado orden y nos da una recompensa final.
     */
    public void eventoCaotico() {
        if (barraCarga.getValue() == 10) {
            estadoMystra.setVisible(true);
            estadoMystra.setIcon(new ImageIcon("./recursos/imagenes/corazon.png"));
            dialogoMystra.setVisible(true);
            dialogoMystra.setText("<html><center>Algo me dice que esto se va a poner interesante.</center></html>");
        }
        if (barraCarga.getValue() == 30) {
            dialogoMystra.setVisible(false);
            estadoTomberi.setVisible(true);
            estadoTomberi.setIcon(new ImageIcon("./recursos/imagenes/neutral.png"));
            dialogoTomberi.setVisible(true);
            dialogoTomberi.setText("<html><center>Pensaba que a ti solo te importaba la comida, "
                    + "<br>pero parece que también tienes sentimientos."
                    + "<br> No me gusta.</center></html>");
        }
        if (barraCarga.getValue() == 50) {
            dialogoTomberi.setVisible(false);
            estadoNarcyl.setVisible(true);
            estadoNarcyl.setIcon(new ImageIcon("./recursos/imagenes/mosqueo.png"));
            dialogoNarcyl.setVisible(true);
            dialogoNarcyl.setText("<html><center>Cuando nos conocimos pensaba que eras <br>de otra manera. "
                    + "Esfuerzate por favor.</center></html>");
        }
        if (barraCarga.getValue() == 70) {
            dialogoNarcyl.setVisible(false);
            dialogoMystra.setVisible(true);
            dialogoMystra.setText("<html><center>Pasa de estos estúpidos, toma te lo has ganado.</center></html>");
        }
        if (barraCarga.getValue() == 90) {
            dialogoMystra.setVisible(false);
            recompensaHeroe.setVisible(true);
            clases.Npc.recompensasNpc(ventana.npcsArray.get(2), ventana.heroe, recompensaHeroe);
        }
        if (barraCarga.getValue() == 100) {
            timer.stop();
            botonSalir.setVisible(true);
        }
    }

    /**
     * Funcion en la que muestra el dialogo en un determinado orden y nos da una recompensa final.
     */
    public void eventoCaoticoMas() {
        if (barraCarga.getValue() == 10) {
            estadoMystra.setVisible(true);
            estadoMystra.setIcon(new ImageIcon("./recursos/imagenes/corazon.png"));
            dialogoMystra.setVisible(true);
            dialogoMystra.setText("<html><center>¡Ja,Ja,Ja! Me encanta, te dejare ser <br>mi mascota, pollo de granja.</center></html>");
        }
        if (barraCarga.getValue() == 30) {
            dialogoMystra.setVisible(false);
            estadoTomberi.setVisible(true);
            estadoTomberi.setIcon(new ImageIcon("./recursos/imagenes/neutral.png"));
            dialogoTomberi.setVisible(true);
            dialogoTomberi.setText("<html><center>Tenias potencial, pero lo has echado todo a perder.</center></html>");
        }
        if (barraCarga.getValue() == 50) {
            dialogoTomberi.setVisible(false);
            estadoNarcyl.setVisible(true);
            estadoNarcyl.setIcon(new ImageIcon("./recursos/imagenes/mosqueo.png"));
            dialogoNarcyl.setVisible(true);
            dialogoNarcyl.setText("<html><center>Por favor, no me hagas daño.</center></html>");
        }
        if (barraCarga.getValue() == 70) {
            dialogoNarcyl.setVisible(false);
            dialogoMystra.setVisible(true);
            dialogoMystra.setText("<html><center>Pasa de estos estúpidos, toma te lo has ganado.</center></html>");
        }
        if (barraCarga.getValue() == 90) {
            dialogoMystra.setVisible(false);
            recompensaHeroe.setVisible(true);
            clases.Npc.recompensasNpc(ventana.npcsArray.get(2), ventana.heroe, recompensaHeroe);
        }
        if (barraCarga.getValue() == 100) {
            timer.stop();
            botonSalir.setVisible(true);
        }
    }

    /**
     * Funcion en la que muestra el dialogo en un determinado orden y nos da una recompensa final.
     */
    public void eventoNeutral() {
        if (barraCarga.getValue() == 10) {
            estadoTomberi.setVisible(true);
            estadoTomberi.setIcon(new ImageIcon("./recursos/imagenes/corazon.png"));
            dialogoTomberi.setVisible(true);
            dialogoTomberi.setText("<html><center>A mi solo me importa el dinero y a ti"
                    + " solo<br>recuperar la comida. Eres como yo.</center></html>");
        }
        if (barraCarga.getValue() == 30) {
            dialogoTomberi.setVisible(false);
            estadoNarcyl.setVisible(true);
            estadoNarcyl.setIcon(new ImageIcon("./recursos/imagenes/neutral.png"));
            dialogoNarcyl.setVisible(true);
            dialogoNarcyl.setText("<html><center>Creo que un héroe debería hacer <br>más por ayudar a los demás.</center></html>");
        }
        if (barraCarga.getValue() == 50) {
            dialogoNarcyl.setVisible(false);
            estadoMystra.setVisible(true);
            estadoMystra.setIcon(new ImageIcon("./recursos/imagenes/neutral.png"));
            dialogoMystra.setVisible(true);
            dialogoMystra.setText("<html><center>¿Te vas de viaje y no aprovechas en buscar<br> algo de diversión?&nbsp;"
                    + "Que aburrido.</center></html>");
        }
        if (barraCarga.getValue() == 70) {
            dialogoMystra.setVisible(false);
            dialogoTomberi.setVisible(true);
            dialogoTomberi.setText("<html><center>Ni caso, no saben de la vida. Toma esto.</center></html>");
        }
        if (barraCarga.getValue() == 90) {
            dialogoTomberi.setVisible(false);
            recompensaHeroe.setVisible(true);
            clases.Npc.recompensasNpc(ventana.npcsArray.get(1), ventana.heroe, recompensaHeroe);
        }
        if (barraCarga.getValue() == 100) {
            timer.stop();
            botonSalir.setVisible(true);
        }
    }

    /**
     * Funcion en la que muestra el dialogo en un determinado orden y nos da una recompensa final.
     */
    public void eventoLegal() {
        if (barraCarga.getValue() == 10) {
            estadoNarcyl.setVisible(true);
            estadoNarcyl.setIcon(new ImageIcon("./recursos/imagenes/corazon.png"));
            dialogoNarcyl.setVisible(true);
            dialogoNarcyl.setText("<html><center>Eres compasivo y leal. Me alegro de haberte seguido.</center></html>");
        }
        if (barraCarga.getValue() == 30) {
            dialogoNarcyl.setVisible(false);
            estadoTomberi.setVisible(true);
            estadoTomberi.setIcon(new ImageIcon("./recursos/imagenes/neutral.png"));
            dialogoTomberi.setVisible(true);
            dialogoTomberi.setText("<html><center>Pensaba que a ti solo te importaba la comida, "
                    + "<br>pero parece que también tienes sentimientos."
                    + "<br> No me gusta.</center></html>");
        }
        if (barraCarga.getValue() == 50) {
            dialogoTomberi.setVisible(false);
            estadoMystra.setVisible(true);
            estadoMystra.setIcon(new ImageIcon("./recursos/imagenes/mosqueo.png"));
            dialogoMystra.setVisible(true);
            dialogoMystra.setText("<html><center>¿Desde cuándo  un pollo de granja se dedica a <br>salvar humanos? "
                    + "Ellos esclavizan a los tuyos.</center></html>");
        }
        if (barraCarga.getValue() == 70) {
            dialogoMystra.setVisible(false);
            dialogoNarcyl.setVisible(true);
            dialogoNarcyl.setText("<html><center>No les hagas caso, toma te lo has ganado ^_^</center></html>");
        }
        if (barraCarga.getValue() == 90) {
            dialogoNarcyl.setVisible(false);
            recompensaHeroe.setVisible(true);
            clases.Npc.recompensasNpc(ventana.npcsArray.get(0), ventana.heroe, recompensaHeroe);
        }
        if (barraCarga.getValue() == 100) {
            timer.stop();
            botonSalir.setVisible(true);
        }
    }

    /**
     * Funcion en la que muestra el dialogo en un determinado orden y nos da una recompensa final.
     */
    public void eventoLegalMas() {
        if (barraCarga.getValue() == 10) {
            estadoNarcyl.setVisible(true);
            estadoNarcyl.setIcon(new ImageIcon("./recursos/imagenes/corazon.png"));
            dialogoNarcyl.setVisible(true);
            dialogoNarcyl.setText("<html><center>No sabía que un pollo de granja pudiera <br>ser un héroe. "
                    + "Estoy muy orgullosa.</center></html>");
        }
        if (barraCarga.getValue() == 30) {
            dialogoNarcyl.setVisible(false);
            estadoTomberi.setVisible(true);
            estadoTomberi.setIcon(new ImageIcon("./recursos/imagenes/neutral.png"));
            dialogoTomberi.setVisible(true);
            dialogoTomberi.setText("<html><center>Tenias potencial, pero lo has echado todo a perder.</center></html>");
        }
        if (barraCarga.getValue() == 50) {
            dialogoTomberi.setVisible(false);
            estadoMystra.setVisible(true);
            estadoMystra.setIcon(new ImageIcon("./recursos/imagenes/mosqueo.png"));
            dialogoMystra.setVisible(true);
            dialogoMystra.setText("<html><center>Tienes el poder necesario para hacer lo que quieras,<br> pero "
                    + "eliges la senda del bien. Decepcionante.</center></html>");
        }
        if (barraCarga.getValue() == 70) {
            dialogoMystra.setVisible(false);
            dialogoNarcyl.setVisible(true);
            dialogoNarcyl.setText("<html><center>No les hagas caso, toma te lo has ganado ^_^</center></html>");
        }
        if (barraCarga.getValue() == 90) {
            dialogoNarcyl.setVisible(false);
            recompensaHeroe.setVisible(true);
            clases.Npc.recompensasNpc(ventana.npcsArray.get(0), ventana.heroe, recompensaHeroe);
        }
        if (barraCarga.getValue() == 100) {
            timer.stop();
            botonSalir.setVisible(true);
        }
    }
}
