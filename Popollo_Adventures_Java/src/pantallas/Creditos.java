package pantallas;

import componentes.Paneles;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;

public class Creditos extends Paneles {

    private Ventana ventana;
    private JLabel mensajeStaff, mensajeNombre;
    private Timer timer;
    private JProgressBar barraCarga;

    public Creditos(Ventana ventana) {
        super();
        setBackground(Color.BLACK);
        this.ventana = ventana;

        //Archivo de sonido.
        String sonidoCreditos = "./recursos/sonidos/Katz.wav";
        String sonidoPopollo = "./recursos/sonidos/Premio.wav";

        Ventana.comenzarSonido(sonidoCreditos);

        JLabel pollo1 = new JLabel();
        pollo1.setVisible(false);
        pollo1.setIcon(new ImageIcon("./recursos/pollo1.gif"));
        pollo1.setBounds(129, 290, 142, 144);
        add(pollo1);

        JLabel pollo2 = new JLabel();
        pollo2.setVisible(false);
        pollo2.setIcon(new ImageIcon("./recursos/pollo2.gif"));
        pollo2.setBounds(774, 273, 200, 200);
        add(pollo2);

        JLabel pollo3 = new JLabel();
        pollo3.setVisible(false);
        pollo3.setIcon(new ImageIcon("./recursos/pollo1.gif"));
        pollo3.setBounds(396, 355, 142, 144);
        add(pollo3);

        JLabel pollo4 = new JLabel();
        pollo4.setVisible(false);
        pollo4.setIcon(new ImageIcon("./recursos/pollo1.gif"));
        pollo4.setBounds(32, 57, 142, 144);
        add(pollo4);

        mensajeStaff = new JLabel();
        mensajeStaff.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
        mensajeStaff.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeStaff.setForeground(Color.WHITE);
        mensajeStaff.setBounds(101, 121, 790, 104);
        add(mensajeStaff);

        mensajeNombre = new JLabel();
        mensajeNombre.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        mensajeNombre.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeNombre.setForeground(Color.WHITE);
        mensajeNombre.setBounds(101, 190, 790, 127);
        add(mensajeNombre);

        JButton botonSalir = new JButton("Salir del Juego");
        botonSalir.setBounds(767, 487, 215, 23);
        botonSalir.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        add(botonSalir);

        barraCarga = new JProgressBar();
        add(barraCarga);

        //Dependiendo del % mostrara u ocultara los siguientes mensajes.
        ActionListener updateBarraCargar = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                barraCarga.setValue((barraCarga.getValue() + 1));
                if (barraCarga.getValue() == 5) {
                    mensajeStaff.setText("Director");
                }
                if (barraCarga.getValue() == 7) {
                    pollo1.setVisible(true);
                    Ventana.comenzarSonido(sonidoPopollo);
                    mensajeNombre.setText("Iván Díaz Vera");
                }
                if (barraCarga.getValue() == 12) {
                    mensajeStaff.setVisible(false);
                    mensajeNombre.setVisible(false);
                }
                if (barraCarga.getValue() == 16) {
                    mensajeStaff.setVisible(true);
                    mensajeStaff.setText("Productor");
                }
                if (barraCarga.getValue() == 18) {
                    mensajeNombre.setVisible(true);
                    pollo3.setVisible(true);
                    Ventana.comenzarSonido(sonidoPopollo);
                    mensajeNombre.setText("Iván Díaz Vera");
                }
                if (barraCarga.getValue() == 23) {
                    mensajeStaff.setVisible(false);
                    mensajeNombre.setVisible(false);
                }
                if (barraCarga.getValue() == 27) {
                    mensajeStaff.setVisible(true);
                    mensajeStaff.setText("Dirección Artística");
                }
                if (barraCarga.getValue() == 29) {
                    mensajeNombre.setVisible(true);
                    pollo4.setVisible(true);
                    Ventana.comenzarSonido(sonidoPopollo);
                    mensajeNombre.setText("Ana Belén Molina González");
                }
                if (barraCarga.getValue() == 34) {
                    mensajeStaff.setVisible(false);
                    mensajeNombre.setVisible(false);
                }
                if (barraCarga.getValue() == 36) {
                    mensajeNombre.setVisible(true);
                    mensajeNombre.setText("Basado en hechos MUY reales.");
                }
                if (barraCarga.getValue() == 41) {
                    mensajeNombre.setVisible(false);
                }
                if (barraCarga.getValue() == 45) {
                    mensajeNombre.setVisible(true);
                    mensajeNombre.setText("<html>Adaptación de la novela autobiográfica<br> <center>\"Un pollo con mucha hambre\"</center></html>");
                }
                if (barraCarga.getValue() == 51) {
                    mensajeNombre.setVisible(false);
                }
                if (barraCarga.getValue() == 55) {
                    mensajeNombre.setVisible(true);
                    mensajeNombre.setText("<html>Ningún pollo fue herido durante el desarrollo de este juego.</html>");
                }
                if (barraCarga.getValue() == 61) {
                    mensajeNombre.setVisible(false);
                }
                if (barraCarga.getValue() == 65) {
                    mensajeNombre.setVisible(true);
                    pollo2.setVisible(true);
                    Ventana.comenzarSonido(sonidoPopollo);
                    mensajeNombre.setText("<html><center>Muchas gracias por jugar.</center><br>"
                            + "Nos vemos en el siguiente juego ^_^</html>");
                }
                if (barraCarga.getValue() == 100) {

                    timer.stop();
                }
            }
        };

        timer = new Timer(500, updateBarraCargar);
        timer.start();

        //Esta funcion de boton nos permite salir del programa pero antes nos preguntara si deseamos salir.
        botonSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int exit = JOptionPane.showConfirmDialog(null, "¿Estas seguro?", " Cerrar Programa",
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION) {
                    if (ventana.getConnect() != null) {
                        try {
                            ventana.getConnect().close();
                        } catch (SQLException e1) {
                            //No importa el error que pueda transmitir en este caso.
                        }
                    }
                    System.exit(0);
                }
            }
        });
    }
}
