package pantallas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import clases.Enemigo;
import clases.Habilidad;
import clases.Heroe;
import clases.Npc;
import clases.Objeto;
import componentes.Botones;
import componentes.LabelPosicion;
import componentes.LabelPrincipal;
import componentes.Paneles;
import exceptions.InvalidMoralException;
import exceptions.InvalidTipoException;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class Principal extends Paneles {

    private Ventana ventana;
    private final LabelPrincipal mostrarAtributos, mensajeBienvenida;
    private final LabelPosicion posicion0Mapa, posicion1Mapa, posicion2Mapa, posicion3Mapa, posicion4Mapa, posicion5Mapa,
            posicion6Mapa, posicion7Mapa, posicion8Mapa, posicion9Mapa, posicion10Mapa, posicion11Mapa, posicion12Mapa,
            posicion13Mapa, posicion14Mapa, posicion15Mapa, posicion16Mapa, posicion17Mapa, posicion18Mapa, marcaMapa;
    private final Botones comenzar, finDelJuego, botonCombateAleatorio, botonGuardarPartida, botonDescanso;
    private final JProgressBar barraExperiencia;
    private String sonidoNoLevel, sonidoMover;

    public Principal(Ventana ventana) {
        super();
        this.ventana = ventana;

        //Sonido
        sonidoNoLevel = "./recursos/sonidos/NoLevel.wav";
        sonidoMover = "./recursos/sonidos/mover.wav";

        //Comprobando si existe un heroe.
        if (ventana.getHeroe() == null) {
            //CARGANDO DATOS DEL JUEGO
            //Si detecta una conexion entra en la base de datos y recupera los datos del heroe junto sus habilidades.
            if (ventana.getConnect() != null) {
                try {
                    Statement stm = ventana.getConnect().createStatement();
                    ResultSet rs;

                    //Habilidades
                    rs = stm.executeQuery("SELECT * FROM habilidad ORDER BY ID ASC");
                    ArrayList<Habilidad> habilidadesHeroe = new ArrayList<Habilidad>();
                    while (rs.next()) {
                        habilidadesHeroe.add(new Habilidad(rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("especial"),
                                rs.getInt("manaUtilizado"), rs.getString("tipo")));
                    }

                    //Objetos
                    rs = stm.executeQuery("SELECT * FROM objeto ORDER BY ID ASC");
                    ArrayList<Objeto> objetosHeroe = new ArrayList<>();
                    while (rs.next()) {
                        objetosHeroe.add(new Objeto(rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("poder"), rs.getInt("cantidad"),
                                rs.getString("tipo"), rs.getInt("precio")));
                    }

                    //Creando el heroe
                    rs = stm.executeQuery("SELECT * FROM Heroe");
                    rs.next();
                    String nombreHeroe = rs.getString("nombre");
                    String descripcionHeroe = rs.getString("descripcion");
                    int saludMaxHeroe = rs.getInt("saludMaxima");
                    int saludHeroe = rs.getInt("salud");
                    int manaMaximoHeroe = rs.getInt("manaMaximo");
                    int manaHeroe = rs.getInt("mana");
                    int fuerzaHeroe = rs.getInt("fuerza");
                    int magiaHeroe = rs.getInt("magia");
                    int agilidadHeroe = rs.getInt("agilidad");
                    int defensaHeroe = rs.getInt("defensa");
                    int dineroHeroe = rs.getInt("dinero");
                    int reputacionHeroe = rs.getInt("reputacion");
                    int experienciaHeroe = rs.getInt("experiencia");
                    int nivelHeroe = rs.getInt("nivel");
                    int explorar = rs.getInt("explorar");

                    //Constructor del Heroe
                    if (ventana.getHeroe() == null) {
                        ventana.setHeroe(new Heroe(nombreHeroe, descripcionHeroe, saludMaxHeroe, saludHeroe, manaMaximoHeroe, manaHeroe, fuerzaHeroe, magiaHeroe, agilidadHeroe,
                                defensaHeroe, habilidadesHeroe, objetosHeroe, dineroHeroe, reputacionHeroe, experienciaHeroe, nivelHeroe, explorar));
                    }
                    stm.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (InvalidTipoException ex) {
                    ex.printStackTrace();
                    System.err.println(ex.getMessage());
                }
            } else {
                if (ventana.getPantallaInicio().getDificultad()==1) {
                    try {
                        ArrayList<Habilidad> habilidadesHeroe = new ArrayList<Habilidad>();
                        habilidadesHeroe.add(new Habilidad("Proyectil Magico", "Disparas chispas magicas de tus manos.", 2, 4, "ofensivo"));
                        habilidadesHeroe.add(new Habilidad("Flecha Helada", "Lanzas una flecha que congela todo a su paso.", 3, 6, "ofensivo"));
                        habilidadesHeroe.add(new Habilidad("Curar Heridas", "Sana las heridas superficiales.", 5, 6, "curativo"));

                        ArrayList<Objeto> objetosHeroe = new ArrayList<Objeto>();
                        objetosHeroe.add(new Objeto("Bomba Pequeña", "Inflige 25 puntos de daño.", 25, 2, "ofensivo", 150));
                        objetosHeroe.add(new Objeto("Bomba Grande", "Inflige 60 puntos de daño.", 60, 1, "ofensivo", 400));
                        objetosHeroe.add(new Objeto("Pocion de Mana", "Restablece 30 puntos de mana.", 30, 2, "curativo", 250));
                        ventana.setHeroe(new Heroe("Popollo", "Un adorable popollito comilon.", 30, 30, 20, 20, 10, 5, 5, 5, habilidadesHeroe, objetosHeroe, 500, 0, 0, 1, 0));
                    } catch (InvalidTipoException e1) {
                        e1.printStackTrace();
                    }
                } else if (ventana.getPantallaInicio().getDificultad()==0) {
                    try {
                        ArrayList<Habilidad> habilidadesHeroe = new ArrayList<Habilidad>();
                        habilidadesHeroe.add(new Habilidad("Proyectil Magico", "Disparas chispas magicas de tus manos.", 2, 4, "ofensivo"));
                        habilidadesHeroe.add(new Habilidad("Flecha Helada", "Lanzas una flecha que congela todo a su paso.", 3, 6, "ofensivo"));
                        habilidadesHeroe.add(new Habilidad("Curar Heridas", "Sana las heridas superficiales.", 5, 6, "curativo"));

                        ArrayList<Objeto> objetosHeroe = new ArrayList<Objeto>();
                        objetosHeroe.add(new Objeto("Bomba Pequeña", "Inflige 25 puntos de daño.", 25, 2, "ofensivo", 150));
                        objetosHeroe.add(new Objeto("Bomba Grande", "Inflige 60 puntos de daño.", 60, 1, "ofensivo", 400));
                        objetosHeroe.add(new Objeto("Pocion de Mana", "Restablece 30 puntos de mana.", 30, 2, "curativo", 250));
                        ventana.setHeroe(new Heroe("Popollo", "Un adorable popollito comilon.", 60, 60, 30, 30, 15, 7, 7, 7, habilidadesHeroe, objetosHeroe, 3000, 0, 0, 1, 0));
                    } catch (InvalidTipoException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        //Insertando el resto de datos, como no se modifican en ningun momento no los guardo en base de datos.
        try {
            //Creacion de enemigos y sus habilidades, se comprueba si estan creados para no repetir el proceso.
            if (ventana.enemigosArray == null) {
                ArrayList<Habilidad> habilidadesPoring = new ArrayList<Habilidad>();
                habilidadesPoring.add(new Habilidad("Pedo magico", "Flatulencia rosada.", 2, 4, "ofensivo"));
                habilidadesPoring.add(new Habilidad("Tirar jellopy", "Mejor no digo de donde sale.", 3, 6, "ofensivo"));

                ArrayList<Habilidad> habilidadesNigromante = new ArrayList<Habilidad>();
                habilidadesNigromante.add(new Habilidad("Lanzar maldicion", "Dolor intenso en las entrañas.", 3, 4, "ofensivo"));
                habilidadesNigromante.add(new Habilidad("Flecha acida", "Derrite armaduras y quema la carne.", 4, 6, "ofensivo"));

                ArrayList<Habilidad> habilidadesGolem = new ArrayList<Habilidad>();
                habilidadesGolem.add(new Habilidad("Mina magica", "El suelo a tu alrededor explota.", 3, 4, "ofensivo"));
                habilidadesGolem.add(new Habilidad("Llamarada", "Quema el aire a su alrededor.", 4, 6, "ofensivo"));

                ArrayList<Habilidad> habilidadesGoblin = new ArrayList<Habilidad>();
                habilidadesGoblin.add(new Habilidad("Lanza envenenada", "La punta de lanza brilla con un color extraña.", 3, 4, "ofensivo"));
                habilidadesGoblin.add(new Habilidad("Flecha venenosa", "Es mejor que no te alcance.", 4, 6, "ofensivo"));

                ArrayList<Habilidad> habilidadesPulpoi = new ArrayList<Habilidad>();
                habilidadesPulpoi.add(new Habilidad("Cosquillas", "Flatulencia rosada.", 10, 10, "ofensivo"));
                habilidadesPulpoi.add(new Habilidad("Mirada viciosa", "Te desnuda con la mirada.", 15, 4, "ofensivo"));

                //Guardo los enemigos en un ArrayList por comodidad.
                ventana.enemigosArray = new ArrayList<Enemigo>();
                ventana.enemigosArray.add(new Enemigo("Poring", "Una pequeña bola rosita", 20, 20, 20, 20, 10, 5, 5, 5, habilidadesPoring, 150, 10));
                ventana.enemigosArray.add(new Enemigo("Nigromante", "Da grima verlo", 40, 40, 24, 24, 15, 5, 5, 5, habilidadesNigromante, 200, 30));
                ventana.enemigosArray.add(new Enemigo("Golem", "Un muro enorme de piedra.", 60, 60, 20, 20, 20, 5, 6, 8, habilidadesGolem, 300, 45));
                ventana.enemigosArray.add(new Enemigo("Goblin", "Es muy rapido", 70, 70, 24, 24, 18, 5, 8, 6, habilidadesGoblin, 400, 60));
                ventana.enemigosArray.add(new Enemigo("Deviling", "Un poring malvado", 100, 100, 24, 24, 20, 5, 8, 7, habilidadesPoring, 600, 70));
                ventana.enemigosArray.add(new Enemigo("Pulpoi", "Pulpo pervertido.", 200, 200, 40, 40, 40, 5, 15, 15, habilidadesPulpoi, 1000, 150));
            }

            //Creacion de npcs, se comprueba si estan creados para no repetir el proceso.
            if (ventana.npcsArray == null) {
                //Npcs
                ventana.npcsArray = new ArrayList<Npc>();
                ventana.npcsArray.add(new Npc("Narcyl", "Sacerdotisa novata.", "legal"));
                ventana.npcsArray.add(new Npc("Tomberi", "Demasiado gruñon.", "neutral"));
                ventana.npcsArray.add(new Npc("Mystra", "Hechicera demente.", "caotico"));
            }
        } catch (InvalidTipoException | InvalidMoralException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }

        //Mensaje inicial/final, botones de empezar y finalizar juego completo.
        comenzar = new Botones("Comienza tu aventura");
        comenzar.setVisible(false);
        comenzar.setBounds(384, 318, 235, 48);
        add(comenzar);

        finDelJuego = new Botones("Felicidades!!!");
        finDelJuego.setVisible(false);
        finDelJuego.setBounds(384, 318, 235, 48);
        add(finDelJuego);

        mensajeBienvenida = new LabelPrincipal();
        mensajeBienvenida.setVisible(false);
        mensajeBienvenida.setBounds(150, 104, 705, 216);
        add(mensajeBienvenida);

        //Dependiendo del atributo explorar del heroe estaremos en una casilla o en otro.
        posicion0Mapa = new LabelPosicion();
        posicion0Mapa.setBounds(30, 194, 60, 60);
        add(posicion0Mapa);

        posicion1Mapa = new LabelPosicion();
        posicion1Mapa.setBounds(112, 134, 60, 60);
        add(posicion1Mapa);

        posicion2Mapa = new LabelPosicion();
        posicion2Mapa.setBounds(184, 86, 60, 60);
        add(posicion2Mapa);

        posicion3Mapa = new LabelPosicion();
        posicion3Mapa.setBounds(275, 48, 60, 60);
        add(posicion3Mapa);

        posicion4Mapa = new LabelPosicion();
        posicion4Mapa.setBounds(354, 85, 60, 60);
        add(posicion4Mapa);

        posicion5Mapa = new LabelPosicion();
        posicion5Mapa.setBounds(352, 182, 60, 60);
        add(posicion5Mapa);

        posicion6Mapa = new LabelPosicion();
        posicion6Mapa.setBounds(314, 260, 60, 60);
        add(posicion6Mapa);

        posicion7Mapa = new LabelPosicion();
        posicion7Mapa.setBounds(334, 344, 60, 60);
        add(posicion7Mapa);

        posicion8Mapa = new LabelPosicion();
        posicion8Mapa.setBounds(412, 351, 60, 60);
        add(posicion8Mapa);

        posicion9Mapa = new LabelPosicion();
        posicion9Mapa.setBounds(477, 283, 60, 60);
        add(posicion9Mapa);

        posicion10Mapa = new LabelPosicion();
        posicion10Mapa.setBounds(537, 209, 60, 60);
        add(posicion10Mapa);

        posicion11Mapa = new LabelPosicion();
        posicion11Mapa.setBounds(592, 133, 60, 60);
        add(posicion11Mapa);

        posicion12Mapa = new LabelPosicion();
        posicion12Mapa.setBounds(677, 104, 60, 60);
        add(posicion12Mapa);

        posicion13Mapa = new LabelPosicion();
        posicion13Mapa.setBounds(728, 179, 60, 60);
        add(posicion13Mapa);

        posicion14Mapa = new LabelPosicion();
        posicion14Mapa.setBounds(728, 265, 60, 60);
        add(posicion14Mapa);

        posicion15Mapa = new LabelPosicion();
        posicion15Mapa.setBounds(813, 290, 60, 60);
        add(posicion15Mapa);

        posicion16Mapa = new LabelPosicion();
        posicion16Mapa.setBounds(865, 223, 60, 60);
        add(posicion16Mapa);

        posicion17Mapa = new LabelPosicion();
        posicion17Mapa.setBounds(880, 145, 60, 60);
        add(posicion17Mapa);

        posicion18Mapa = new LabelPosicion();
        posicion18Mapa.setBounds(900, 38, 60, 60);
        add(posicion18Mapa);

        //JLabel que nos permitira movernos por el mapa, este Label se movera de posicion una vez pulsemos sobre el.
        marcaMapa = new LabelPosicion();
        marcaMapa.setIcon(new ImageIcon("./recursos/marcadorMapa.gif"));
        add(marcaMapa);

        botonGuardarPartida = new Botones("Guardar Partida");
        botonGuardarPartida.setVisible(false);
        botonGuardarPartida.setBounds(767, 436, 215, 23);
        add(botonGuardarPartida);

        botonDescanso = new Botones("Punto de descanso");
        botonDescanso.setVisible(false);
        botonDescanso.setBounds(522, 487, 215, 23);
        add(botonDescanso);

        botonCombateAleatorio = new Botones("Avanzar");
        botonCombateAleatorio.setVisible(false);
        botonCombateAleatorio.setText("Combate aleatorio");
        botonCombateAleatorio.setBounds(522, 436, 215, 23);
        add(botonCombateAleatorio);

        Botones botonSalir = new Botones("Volver al menu de inicio");
        botonSalir.setBounds(767, 487, 215, 23);
        add(botonSalir);

        //Boton que nos permite usar la funcion de guardar partida pero antes se abrira un JOptionPane para confirmar la orden.
        botonGuardarPartida.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int guardar = JOptionPane.showConfirmDialog(null, "¿Estás seguro?", " Guardar Partida",
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (guardar == JOptionPane.YES_OPTION) {
                    ventana.guardarPartida(ventana.heroe.getHabilidadesArray(), ventana.heroe.getObjetosArray());
                }
            }
        });

        //Nos llevara a la sala de descanso.
        botonDescanso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Ventana.origenADestino(ventana, "principal", "descanso", 0);
            }
        });

        //Llama a la funcion que nos permite luchar aleatoriamente.
        botonCombateAleatorio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                combateAleatorio();
            }
        });

        //Boton que nos permite salir del juego pero antes se abrira un JOptionPane para confirmar la orden, ademas cerrara las conexiones si detecta alguna.
        botonSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int exit = JOptionPane.showConfirmDialog(null, "¿Estas seguro?", " Volver a Inicio",
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION) {
                	ventana.origenADestino(ventana, "principal", "inicio", 0);
            		//Ponemos el heroe a nulo, asi cuando volvamos a la pantalla de inicio no exista ninguno y podamos volver a crearlo.
                    ventana.setHeroe(null);
                    ventana.setConnect(null);
                }         
            }
        });

        //Nos lleva a la pantalla de creditos.
        finDelJuego.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Ventana.origenADestino(ventana, "principal", "creditos", 0);
              //Archivo escrito simplemente para ver que funciona. Ademas de desbloquear una nueva galeria guiño guiño.
                try {
                    FileWriter archivoOculto = new FileWriter("./recursos/agradecimientos/texto.txt");
                    archivoOculto.write("Muchas gracias por terminar el juego, espero que no encontaras muchos bugs ^_^ ");
                    archivoOculto.write(System.getProperty( "line.separator" ));
                    archivoOculto.write("Al pasarte el juego has desbloqueado unas imagenes extras de futuros proyectos. ");
                    archivoOculto.write(System.getProperty( "line.separator" ));
                    archivoOculto.write("No mirar en el trabajo ni delante de la pareja. ");
                    archivoOculto.write(System.getProperty( "line.separator" ));
                    archivoOculto.flush();
                    archivoOculto.close();
                    ArrayList <File> guiño = new ArrayList<File>();
                    guiño.add(new File("./recursos/agradecimientos/imagenes/1"));
                    guiño.add(new File("./recursos/agradecimientos/imagenes/2"));
                    guiño.add(new File("./recursos/agradecimientos/imagenes/3"));
                    
                    for(int i=0; i<guiño.size();i++) {
                    	guiño.get(i).renameTo( new File( guiño.get(i) + ".png" ));
                    }
                    
                    //quitando el modo oculto a archivos o carpetas, guiño guiño.
                    Runtime.getRuntime().exec("attrib -H " + "./recursos/agradecimientos/imagenes");
                } catch (IOException e1) {
                }
            }
        });

        //Usa dos funciones relacionadas con el movimiento en el mapa, explicacion en ellas.
        marcaMapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Ventana.comenzarSonido(sonidoMover);
                avanzarCasilla();
                movimientoMapa();
            }
        });

        //Quita el mensaje de bienvenida y empieza el juego.
        comenzar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mensajeBienvenida.setVisible(false);
                comenzar.setVisible(false);
            }
        });

        //Muestra el dinero y la reputacion obtenida.
        LabelPrincipal mostrarDineroReputacion = new LabelPrincipal();
        mostrarDineroReputacion.setText("<html><b>Oro: " + Integer.toString(ventana.heroe.getDinero())
                + "<br/>Reputacion: " + Integer.toString(ventana.heroe.getReputacion())
                + "</b></html>");
        mostrarDineroReputacion.setBounds(10, 29, 130, 51);
        add(mostrarDineroReputacion);
        
        //Nos muestra la experiencia total de nuestro heroe.
        barraExperiencia = new JProgressBar(0, (ventana.heroe.getNivel() * 30));
        barraExperiencia.setFont(new Font("Bahnschrift", Font.BOLD, 15));
        barraExperiencia.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        barraExperiencia.setStringPainted(true);
        barraExperiencia.setString(ventana.heroe.getExperiencia() + "/" + (ventana.heroe.getNivel() * 30));
        barraExperiencia.setValue(ventana.heroe.getExperiencia());
        barraExperiencia.setBounds(10, 344, 130, 31);
        add(barraExperiencia);

        //Nos muestra todos los atributos del heroe.
        mostrarAtributos = new LabelPrincipal();
        ventana.heroe.pantallaGeneralEstadisticas(mostrarAtributos);
        mostrarAtributos.setBounds(10, 370, 130, 155);
        add(mostrarAtributos);

        //Nos muestra todos los usos de las habilidades.
        LabelPrincipal mostrarHabilidades = new LabelPrincipal();
        ventana.heroe.mostrarHabilidades(mostrarHabilidades);
        mostrarHabilidades.setBounds(150, 436, 174, 89);
        add(mostrarHabilidades);

        //Nos muestra la cantidad de objetos restantes.
        LabelPrincipal mostrarObjetos = new LabelPrincipal();
        ventana.heroe.mostrarObjetos(mostrarObjetos);
        mostrarObjetos.setBounds(334, 436, 174, 89);
        add(mostrarObjetos);

        //Imagen de fondo
        JLabel imagenMapa = new JLabel("");
        imagenMapa.setBounds(0, 0, 1008, 536);
        imagenMapa.setIcon(new ImageIcon("./recursos/imagenes/mapa.png"));
        add(imagenMapa);

        //Llamando a la funcion, explicacion de esta abajo.
        movimientoMapa();
    }

    /**
     * Funcion que suma un +1 a explorar y dependiendo del valor de este entraremos en algun tipo de evento
     * (representado con un dibujo en el mapa). Algunas casillas son especiales y si no tenemos el suficiente nivel no
     * nos dejaran entrar.
     */
    public void avanzarCasilla() {
        ventana.heroe.setExplorar(ventana.heroe.getExplorar() + 1);
        if (ventana.heroe.getExplorar() == 1) {
            JOptionPane.showMessageDialog(null, "Has dado tu primer paso, ahora empieza lo divertido.", "Animo ^_^", 1);
        }
        if (ventana.heroe.getExplorar() == 2) {
            Ventana.origenADestino(ventana, "principal", "lucha", 0);
        }
        if (ventana.heroe.getExplorar() == 3) {
            Ventana.origenADestino(ventana, "principal", "evento", 0);
        }
        if (ventana.heroe.getExplorar() == 4) {
            int entrar = JOptionPane.showConfirmDialog(null, "¿Quieres comprar algo?", " Entrar en Tienda",
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (entrar == JOptionPane.YES_OPTION) {
                Ventana.origenADestino(ventana, "principal", "tienda", 0);
            }
        }
        if (ventana.heroe.getExplorar() == 5) {
            if (ventana.heroe.getNivel() >= 2) {
                Ventana.origenADestino(ventana, "principal", "lucha", 1);
            } else {
                Ventana.comenzarSonido(sonidoNoLevel);
                JOptionPane.showMessageDialog(null, "Retirada, necesitas mínimo nivel 2 para continuar.", "Blandengue", 1);
                ventana.heroe.setExplorar(ventana.heroe.getExplorar() - 2);
                posicion3Mapa.setVisible(false);
            }
        }
        if (ventana.heroe.getExplorar() == 6) {
            Ventana.origenADestino(ventana, "principal", "evento", 1);
        }
        if (ventana.heroe.getExplorar() == 7) {
            int entrar = JOptionPane.showConfirmDialog(null, "¿Quieres comprar algo?", " Entrar en Tienda",
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (entrar == JOptionPane.YES_OPTION) {
                Ventana.origenADestino(ventana, "principal", "tienda", 0);
            }
        }
        if (ventana.heroe.getExplorar() == 8) {
            Ventana.origenADestino(ventana, "principal", "afinidad", 1);
        }
        if (ventana.heroe.getExplorar() == 9) {
            Ventana.origenADestino(ventana, "principal", "lucha", 2);
        }
        if (ventana.heroe.getExplorar() == 10) {
            Ventana.origenADestino(ventana, "principal", "evento", 2);
        }
        if (ventana.heroe.getExplorar() == 11) {
            int entrar = JOptionPane.showConfirmDialog(null, "¿Quieres comprar algo?", " Entrar en Tienda",
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (entrar == JOptionPane.YES_OPTION) {
                Ventana.origenADestino(ventana, "principal", "tienda", 0);
            }
        }
        if (ventana.heroe.getExplorar() == 12) {
            if (ventana.heroe.getNivel() >= 4) {
                Ventana.origenADestino(ventana, "principal", "lucha", 3);
            } else {
                JOptionPane.showMessageDialog(null, "Retirada, necesitas mínimo nivel 4 para continuar.", "Blandengue", 1);
                ventana.heroe.setExplorar(ventana.heroe.getExplorar() - 2);
                posicion10Mapa.setVisible(false);
            }
        }
        if (ventana.heroe.getExplorar() == 13) {
            Ventana.origenADestino(ventana, "principal", "evento", 3);
        }
        if (ventana.heroe.getExplorar() == 14) {
            Ventana.origenADestino(ventana, "principal", "tienda", 0);
        }
        if (ventana.heroe.getExplorar() == 15) {
            Ventana.origenADestino(ventana, "principal", "afinidad", 1);
        }
        if (ventana.heroe.getExplorar() == 16) {
            Ventana.origenADestino(ventana, "principal", "lucha", 4);
        }
        if (ventana.heroe.getExplorar() == 17) {
            Ventana.origenADestino(ventana, "principal", "evento", 4);
        }
        if (ventana.heroe.getExplorar() == 18) {
            int entrar = JOptionPane.showConfirmDialog(null, "¿Quieres comprar algo?", " Entrar en Tienda",
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (entrar == JOptionPane.YES_OPTION) {
                Ventana.origenADestino(ventana, "principal", "tienda", 0);
            }
        }
        if (ventana.heroe.getExplorar() == 19) {
            if (ventana.heroe.getNivel() >= 7) {
                Ventana.origenADestino(ventana, "principal", "lucha", 5);
                ventana.heroe.setExplorar(20);
            } else {
                JOptionPane.showMessageDialog(null, "Retirada, necesitas mínimo nivel 7 para continuar.", "Blandengue", 1);
                ventana.heroe.setExplorar(ventana.heroe.getExplorar() - 2);
                posicion17Mapa.setVisible(false);
            }
        }
    }

    /**
     * Funcion que nos permite avanzar paso a paso mirando el valor explorar. Cada paso mueve el JLabel que nos permite
     * avanzar al siguiente destino. Igualmente cada paso marca la zona anterior con una X roja.
     */
    public void movimientoMapa() {
        ventana.heroe.getExplorar();
        if (ventana.heroe.getExplorar() >= 0) {
            marcaMapa.setBounds(39, 182, 60, 60);
            marcaMapa.setVisible(true);
            comenzar.setVisible(true);
            mensajeBienvenida.setVisible(true);
            mensajeBienvenida.setText("<html><b><center>Una malvada criatura está robando toda la comida.<br><br> "
                    + "Un voraz pollo de granja se alza entre todos los habitantes del reino "
                    + "<br>para hacer frente al vil enemigo que le priva de sus chuletitas.<br><br>"
                    + "Tres aliados le acompañarán por el camino, aunque cada uno tiene <br>una"
                    + " opinión distinta de lo que nuestro héroe debiera hacer.<br><br>"
                    + "*Para avanzar en el juego necesitas pulsar en la flecha de color azul*<br>"
                    + "*Guarda partida siempre que puedas y utiliza la tienda sabiamente*</center></b></html>");
        }
        if (ventana.heroe.getExplorar() >= 1) {
            marcaMapa.setBounds(114, 118, 60, 60);
            posicion0Mapa.setVisible(true);
            marcaMapa.setVisible(true);
            botonGuardarPartida.setVisible(true);
            botonDescanso.setVisible(true);
            comenzar.setVisible(false);
            mensajeBienvenida.setVisible(false);
        }
        if (ventana.heroe.getExplorar() >= 2) {
            marcaMapa.setBounds(190, 67, 60, 60);
            posicion1Mapa.setVisible(true);
            botonCombateAleatorio.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 3) {
            marcaMapa.setBounds(280, 29, 60, 60);
            posicion2Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 4) {
            marcaMapa.setBounds(362, 67, 60, 60);
            posicion3Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 5) {
            marcaMapa.setBounds(358, 150, 60, 60);
            posicion4Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 6) {
            marcaMapa.setBounds(319, 234, 60, 60);
            posicion5Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 7) {
            marcaMapa.setBounds(340, 314, 60, 60);
            posicion6Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 8) {
            marcaMapa.setBounds(419, 329, 60, 60);
            posicion7Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 9) {
            marcaMapa.setBounds(483, 253, 60, 60);
            posicion8Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 10) {
            marcaMapa.setBounds(540, 182, 60, 60);
            posicion9Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 11) {
            marcaMapa.setBounds(596, 111, 60, 60);
            posicion10Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 12) {
            marcaMapa.setBounds(680, 67, 60, 60);
            posicion11Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 13) {
            marcaMapa.setBounds(732, 150, 60, 60);
            posicion12Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 14) {
            marcaMapa.setBounds(743, 234, 60, 60);
            posicion13Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 15) {
            marcaMapa.setBounds(814, 267, 60, 60);
            posicion14Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 16) {
            marcaMapa.setBounds(870, 196, 60, 60);
            posicion15Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 17) {
            marcaMapa.setBounds(889, 118, 60, 60);
            posicion16Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 18) {
            marcaMapa.setBounds(909, 11, 60, 60);
            posicion17Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 19) {
            marcaMapa.setVisible(false);
            posicion18Mapa.setVisible(true);
        }
        if (ventana.heroe.getExplorar() >= 20) {
            //Al llegar a 20 aparece un mensaje final y nos da la opcion de ir a la pantalla de creditos.
            finDelJuego.setVisible(true);
            mensajeBienvenida.setText("<html><center><b>¡Popollo ha conseguido derrotar al infame Pulpoi!</center><br><br> Todos en el reino recordarán la hazaña y cantarán odas en tu honor, <br>"
                    + "pero ahora lo más importante es... Que a Popollo le ruge el estómago.<br><br>* Has desbloqueado material adicional, puedes encontrarlo en /recursos/agradecimientos *</b></html>");
            mensajeBienvenida.setVisible(true);
            botonCombateAleatorio.setVisible(false);
            botonDescanso.setVisible(false);
            botonGuardarPartida.setVisible(false);
        }
    }

    /**
     * Funcion que nos permite realizar combates aleatorios contra enemigos para subir de nivel. Los enemigos se
     * desbloquean en funcion de nuestra posicion en el mapa.
     */
    public void combateAleatorio() {
        int aleatorio;
        if (ventana.heroe.getExplorar() < 5) {
            Ventana.origenADestino(ventana, "principal", "lucha", 0);
        } else if (ventana.heroe.getExplorar() >= 5 && ventana.heroe.getExplorar() < 9) {
            aleatorio = clases.Personaje.numeroAleatorio(0, 1);
            Ventana.origenADestino(ventana, "principal", "lucha", aleatorio);
        } else if (ventana.heroe.getExplorar() >= 9 && ventana.heroe.getExplorar() < 12) {
            aleatorio = clases.Personaje.numeroAleatorio(0, 2);
            Ventana.origenADestino(ventana, "principal", "lucha", aleatorio);
        } else if (ventana.heroe.getExplorar() >= 12 && ventana.heroe.getExplorar() < 16) {
            aleatorio = clases.Personaje.numeroAleatorio(0, 3);
            Ventana.origenADestino(ventana, "principal", "lucha", aleatorio);
        } else if (ventana.heroe.getExplorar() >= 16) {
            aleatorio = clases.Personaje.numeroAleatorio(0, 4);
            Ventana.origenADestino(ventana, "principal", "lucha", aleatorio);
        }
    }
}
