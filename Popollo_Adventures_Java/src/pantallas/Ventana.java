package pantallas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import clases.Enemigo;
import clases.Habilidad;
import clases.Heroe;
import clases.Npc;
import clases.Objeto;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Ventana extends JFrame {

    private static Inicio pantallaInicio;
    private static Galeria pantallaGaleria;
    private static Principal pantallaPrincipal;
    private static Tienda pantallaTienda;
    private static Descanso pantallaDescanso;
    private static Lucha pantallaLucha;
    private static Evento pantallaEvento;
    private static Afinidad pantallaAfinidad;
    private static Creditos pantallaCreditos;
    private Cargar pantallaCarga;
    private Connection connect;
    public Heroe heroe;
    public ArrayList<Enemigo> enemigosArray;
    public ArrayList<Npc> npcsArray;
    public static Clip clip, clip2;

    public Ventana() {
        super();

        //Configuracion de la pantalla inicial.
        setTitle("Popollo Adventure");
        pantallaInicio = new Inicio(this);
        setIconImage(Toolkit.getDefaultToolkit().getImage("./recursos/normal.png"));
        setSize(1014, 566);
        setResizable(false);
        setVisible(true);
        setContentPane(pantallaInicio);
        setLocationRelativeTo(null);

        //Esto nos permite modificar los JOptionPane para cambiar de fuente, tamaño, etc
        UIManager.put("OptionPane.messageFont", new Font("Bahnschrift", Font.BOLD, 14));

        //Usando Toolkit cambiamos el cursor por defecto cuando pulsamos dentro de estos Botones.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("./recursos/normal.png");
        Point point = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(img, point, null);
        setCursor(cursor);

        //Nos aseguramos de cerrar el programa correctamente junto a la conexion si existe.
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                int exit = JOptionPane.showConfirmDialog(null, "¿Estas seguro?", " Cerrar Programa",
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION) {
                    if (connect != null) {
                        try {
                            connect.close();
                        } catch (SQLException e1) {
                            //No importa el error que pueda transmitir en este caso.
                        }
                    }
                    System.exit(0);
                }
            }
        });
    }

    //Getters y Setters
    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

    public static Principal getPantallaPrincipal() {
		return pantallaPrincipal;
	}

	public static void setPantallaPrincipal(Principal pantallaPrincipal) {
		Ventana.pantallaPrincipal = pantallaPrincipal;
	}

	public static Lucha getPantallaLucha() {
		return pantallaLucha;
	}

	public static void setPantallaLucha(Lucha pantallaLucha) {
		Ventana.pantallaLucha = pantallaLucha;
	}
	
	public static Inicio getPantallaInicio() {
		return pantallaInicio;
	}

	public static void setPantallaInicio(Inicio pantallaInicio) {
		Ventana.pantallaInicio = pantallaInicio;
	}

	public static Galeria getPantallaGaleria() {
		return pantallaGaleria;
	}

	public static void setPantallaGaleria(Galeria pantallaGaleria) {
		Ventana.pantallaGaleria = pantallaGaleria;
	}

	public static Tienda getPantallaTienda() {
		return pantallaTienda;
	}

	public static void setPantallaTienda(Tienda pantallaTienda) {
		Ventana.pantallaTienda = pantallaTienda;
	}

	public static Descanso getPantallaDescanso() {
		return pantallaDescanso;
	}

	public static void setPantallaDescanso(Descanso pantallaDescanso) {
		Ventana.pantallaDescanso = pantallaDescanso;
	}

	public static Evento getPantallaEvento() {
		return pantallaEvento;
	}

	public static void setPantallaEvento(Evento pantallaEvento) {
		Ventana.pantallaEvento = pantallaEvento;
	}

	public static Afinidad getPantallaAfinidad() {
		return pantallaAfinidad;
	}

	public static void setPantallaAfinidad(Afinidad pantallaAfinidad) {
		Ventana.pantallaAfinidad = pantallaAfinidad;
	}

	public static Creditos getPantallaCreditos() {
		return pantallaCreditos;
	}

	public static void setPantallaCreditos(Creditos pantallaCreditos) {
		Ventana.pantallaCreditos = pantallaCreditos;
	}

	public Cargar getPantallaCarga() {
		return pantallaCarga;
	}

	public void setPantallaCarga(Cargar pantallaCarga) {
		this.pantallaCarga = pantallaCarga;
	}

	/**
     * Funcion que conecta con la base de base de datos para cargar partida y nos devuelve una conexion si no ocurre
     * ningun error.
     *
     * @return devuelve la conexion o valor null si falla.
     */
    public Connection cargarPartida() {
        try {
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/popollo_adventure"
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Ventana.comenzarSonido("./recursos/sonidos/Login.wav");
            return connect;
        } catch (SQLException ex) {
            Ventana.comenzarSonido("./recursos/sonidos/NoLevel.wav");
            JOptionPane.showMessageDialog(null, "Servidor en mantenimiento.", "Lo sentimos", 1);
            return null;
        }
    }

    /**
     * Funcion que conecta con la base de datos y modifica los valores para dejar un registro de nuestra partida actual.
     *
     * @param habilidadesHeroe Es necesario pasar el array de Habilidades del heroe para modificar sus valores.
     * @param objetosHeroe Es necesario pasar el array de objetos del heroe para modificar sus valores.
     */
    public void guardarPartida(ArrayList<Habilidad> habilidadesHeroe, ArrayList<Objeto> objetosHeroe) {
        try {
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/popollo_adventure"
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

            //Guardando parametros del heroe
            PreparedStatement smt = connect.prepareStatement("UPDATE heroe "
                    + "SET nombre = ?, descripcion = ?, saludMaxima = ?, salud = ?, manaMaximo = ?, mana = ?, fuerza = ?, magia = ?, agilidad = ?"
                    + ", defensa = ?, dinero = ?, reputacion = ?, experiencia = ?, nivel = ?, explorar = ? WHERE ID = 1");
            smt.setString(1, heroe.getNombre());
            smt.setString(2, heroe.getDescripcion());
            smt.setInt(3, heroe.getSaludMaxima());
            smt.setInt(4, heroe.getSalud());
            smt.setInt(5, heroe.getManaMaximo());
            smt.setInt(6, heroe.getMana());
            smt.setInt(7, heroe.getFuerza());
            smt.setInt(8, heroe.getMagia());
            smt.setInt(9, heroe.getAgilidad());
            smt.setInt(10, heroe.getDefensa());
            smt.setInt(11, heroe.getDinero());
            smt.setInt(12, heroe.getReputacion());
            smt.setInt(13, heroe.getExperiencia());
            smt.setInt(14, heroe.getNivel());
            smt.setInt(15, heroe.getExplorar());
            smt.executeUpdate();

            //Solo modificamos la variable que realmente cambia, en este caso la cantidad
            for (int i = 0; i < objetosHeroe.size(); i++) {
                smt = connect.prepareStatement("UPDATE objeto "
                        + "SET cantidad = ? WHERE ID = " + (i + 1) + "");
                smt.setInt(1, heroe.getObjetosArray().get(i).getCantidad());
                smt.executeUpdate();
                smt.close();
            }
            smt.close();
            Ventana.comenzarSonido("./recursos/sonidos/Guardar.wav");
        } catch (SQLException ex) {
            Ventana.comenzarSonido("./recursos/sonidos/NoLevel.wav");
            JOptionPane.showMessageDialog(null, "Servidor en mantenimiento.", "Lo sentimos", 1);
        }
    }

    //Movimiento entre paneles
    /**
     * De pantalla de inicio a pantalla de carga
     */
    public void cargarPantallaCarga() {
        this.pantallaCarga = new Cargar(this);
        this.setTitle("Principal");
        Ventana.pantallaInicio.setVisible(false);
        this.setContentPane(this.pantallaCarga);
        this.pantallaCarga.setVisible(true);
    }

    /**
     * De carga a principal
     */
    public void cargarPantallaPrincipal() {
    	Ventana.pantallaPrincipal = new Principal(this);
        this.setTitle("Principal");
        this.pantallaCarga.setVisible(false);
        this.setContentPane(Ventana.pantallaPrincipal);
        Ventana.pantallaPrincipal.setVisible(true);
    }

    /**
     * Funcion para ir de pantalla origen a pantalla destino
     *
     * @param origen Indica la pantalla actual
     * @param destino Indica la pantalla siguiente
     * @param numero Variable de tipo entero que nos ayuda a indicar el numero de evento o combate
     */
    public static void origenADestino(Ventana v, String origen, String destino, int numero) {
        switch (destino) {
            case "inicio":
                pantallaInicio = new Inicio(v);
                v.setTitle("Popollo Adventure");
                v.setContentPane(pantallaInicio);
                Ventana.pantallaInicio.setVisible(true);
                break;
            case "tienda":
                pantallaTienda = new Tienda(v);
                v.setTitle("Tienda");
                v.setContentPane(Ventana.pantallaTienda);
                Ventana.pantallaTienda.setVisible(true);
                break;
            case "descanso":
                pantallaDescanso = new Descanso(v);
                v.setTitle("Descanso");
                v.setContentPane(Ventana.pantallaDescanso);
                Ventana.pantallaDescanso.setVisible(true);
                break;
            case "evento":
                pantallaEvento = new Evento(v, numero);
                v.setTitle("Evento");
                v.setContentPane(Ventana.pantallaEvento);
                Ventana.pantallaEvento.setVisible(true);
                break;
            case "lucha":
                pantallaLucha = new Lucha(v, numero);
                v.setTitle("Lucha");
                v.setContentPane(Ventana.pantallaLucha);
                Ventana.pantallaLucha.setVisible(true);
                break;
            case "principal":
                pantallaPrincipal = new Principal(v);
                v.setTitle("Principal");
                v.setContentPane(Ventana.pantallaPrincipal);
                Ventana.pantallaPrincipal.setVisible(true);
                break;
            case "galeria":
                pantallaGaleria = new Galeria(v);
                v.setTitle("Galeria");
                v.setContentPane(Ventana.pantallaGaleria);
                Ventana.pantallaGaleria.setVisible(true);
                break;
            case "creditos":
                pantallaCreditos = new Creditos(v);
                v.setTitle("Creditos");
                v.setContentPane(Ventana.pantallaCreditos);
                Ventana.pantallaCreditos.setVisible(true);
                break;
            case "afinidad":
                pantallaAfinidad = new Afinidad(v);
                v.setTitle("Afinidad");
                v.setContentPane(Ventana.pantallaAfinidad);
                Ventana.pantallaAfinidad.setVisible(true);
                break;
        }
        switch (origen) {
            case "inicio":
                pantallaInicio.setVisible(false);
                pantallaInicio.removeAll();
                setPantallaInicio(null);
                break;
            case "tienda":
                pantallaTienda.setVisible(false);
                pantallaTienda.removeAll();
                setPantallaTienda(null);
                break;
            case "descanso":
                pantallaDescanso.setVisible(false);
                pantallaDescanso.removeAll();
                setPantallaDescanso(null);
                break;
            case "evento":
                pantallaEvento.setVisible(false);
                pantallaEvento.removeAll();
                setPantallaEvento(null);
                break;
            case "lucha":
                pantallaLucha.setVisible(false);
                pantallaLucha.removeAll();
                setPantallaLucha(null);
                break;
            case "principal":
                pantallaPrincipal.setVisible(false);
                pantallaPrincipal.removeAll();
                setPantallaPrincipal(null);
                break;
            case "creditos":
                pantallaCreditos.setVisible(false);
                pantallaCreditos.removeAll();
                setPantallaCreditos(null);
                break;
            case "afinidad":
                pantallaAfinidad.setVisible(false);
                pantallaAfinidad.removeAll();
                setPantallaAfinidad(null);
                break;
            case "galeria":
                pantallaGaleria.setVisible(false);
                pantallaGaleria.removeAll();
                setPantallaGaleria(null);
                break;     
        }
    }
    
    //AVISO SUPER IMPORTANTE
    //Al no cerrar el AudioInputStream ocurria un problema de memory leak que 
    //Anotacion, puedo poner la musica guardada en ventana y llamarla por parametro pero he decididio dejarla cada una en su pantalla.
    /**
     * Funcion que nos permite iniciar sonidos.
     *
     * @param rutaSonido Variable de tipo string que indica la ruta del archivo de sonido.
     */
    public static void comenzarSonido(String rutaSonido) {
        try {
            File rutaMusica = new File(rutaSonido);

            if (rutaMusica.exists()) {
                AudioInputStream audioInputSonido = AudioSystem.getAudioInputStream(rutaMusica);
                clip = AudioSystem.getClip();
                clip.open(audioInputSonido);
                clip.start();
            } else {
                System.out.println("No pudo encontrarse el archivo");
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }

    /**
     * Funcion que nos permite parar un sonido.
     */
    public static void pararSonido() {
        clip.stop();
        clip.close();
    }

    /**
     * Funcion que nos permite iniciar musica de Fondo.
     *
     * @param rutaSonido Variable de tipo string que indica la ruta del archivo de sonido.
     */
    public static void comenzarFondo(String rutaSonido) {
        try {
            File rutaMusica = new File(rutaSonido);

            if (rutaMusica.exists()) {
                AudioInputStream audioInputSonido = AudioSystem.getAudioInputStream(rutaMusica);
                clip2 = AudioSystem.getClip();
                clip2.open(audioInputSonido);
                clip2.start();
                clip2.loop(5);
            } else {
                System.out.println("No pudo encontrarse el archivo");
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }

    /**
     * Funcion que nos permite parar la musica de Fondo.
     */
    public static void pararFondo() {
        clip2.stop();
        clip2.close();
    }
}
