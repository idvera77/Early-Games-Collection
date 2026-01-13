package pantallas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import componentes.Botones;
import componentes.BotonesTienda;
import componentes.LabelPrincipal;
import componentes.TextoTienda;
import componentes.Paneles;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import clases.Heroe;
import java.awt.Font;

public class Tienda extends Paneles {

    private Ventana ventana;
    private Heroe heroe;

    public Tienda(Ventana v) {
        super();
        this.ventana = v;
        this.heroe = ventana.heroe;

        //Archivos de sonido.
        String sonidoComprar = "./recursos/sonidos/Comprar.wav";
        String sonidoNoMoney = "./recursos/sonidos/NoMoney.wav";

        //JFieldText y JPanels para montar la estructura de la tienda.
        TextoTienda panelPrecioObjeto = new TextoTienda();
        panelPrecioObjeto.setText("Precio");
        panelPrecioObjeto.setBounds(300, 96, 54, 23);
        add(panelPrecioObjeto);

        TextoTienda panelComprar = new TextoTienda();
        panelComprar.setText("Comprar");
        panelComprar.setBounds(176, 96, 76, 23);
        add(panelComprar);

        TextoTienda panelCantidad = new TextoTienda();
        panelCantidad.setText("Cantidad");
        panelCantidad.setBounds(55, 96, 76, 23);
        add(panelCantidad);

        TextoTienda cantidadBombaP = new TextoTienda();
        cantidadBombaP.setText(Integer.toString(heroe.getObjetosArray().get(0).getCantidad()));
        cantidadBombaP.setBounds(81, 130, 41, 23);
        add(cantidadBombaP);

        TextoTienda cantidadBombaG = new TextoTienda();
        cantidadBombaG.setText(Integer.toString(heroe.getObjetosArray().get(1).getCantidad()));
        cantidadBombaG.setBounds(81, 160, 41, 23);
        add(cantidadBombaG);

        TextoTienda cantidadPocion = new TextoTienda();
        cantidadPocion.setText(Integer.toString(heroe.getObjetosArray().get(2).getCantidad()));
        cantidadPocion.setBounds(81, 190, 41, 23);
        add(cantidadPocion);

        TextoTienda precioBombaP = new TextoTienda();
        precioBombaP.setText(Integer.toString(heroe.getObjetosArray().get(0).getPrecio()));
        precioBombaP.setBounds(312, 130, 41, 23);
        add(precioBombaP);

        TextoTienda precioBombaG = new TextoTienda();
        precioBombaG.setText(Integer.toString(heroe.getObjetosArray().get(1).getPrecio()));
        precioBombaG.setBounds(312, 160, 41, 23);
        add(precioBombaG);

        TextoTienda precioPocion = new TextoTienda();
        precioPocion.setText(Integer.toString(heroe.getObjetosArray().get(2).getPrecio()));
        precioPocion.setBounds(312, 190, 41, 23);
        add(precioPocion);

        TextoTienda panelAtributosActual = new TextoTienda();
        panelAtributosActual.setText("Actual");
        panelAtributosActual.setBounds(63, 255, 59, 23);
        add(panelAtributosActual);

        TextoTienda panelMejorar = new TextoTienda();
        panelMejorar.setText("Mejorar");
        panelMejorar.setBounds(180, 255, 65, 23);
        add(panelMejorar);

        TextoTienda panelPrecioAtributo = new TextoTienda();
        panelPrecioAtributo.setText("Precio");
        panelPrecioAtributo.setBounds(300, 255, 54, 23);
        add(panelPrecioAtributo);

        TextoTienda precioFuerza = new TextoTienda();
        precioFuerza.setText("750");
        precioFuerza.setBounds(313, 289, 41, 23);
        add(precioFuerza);

        TextoTienda precioMagia = new TextoTienda();
        precioMagia.setText("1000");
        precioMagia.setBounds(310, 323, 41, 23);
        add(precioMagia);

        TextoTienda precioDefensa = new TextoTienda();
        precioDefensa.setText("750");
        precioDefensa.setBounds(313, 357, 41, 23);
        add(precioDefensa);

        TextoTienda precioAgilidad = new TextoTienda();
        precioAgilidad.setText("750");
        precioAgilidad.setBounds(313, 391, 41, 23);
        add(precioAgilidad);

        TextoTienda fuerzaActual = new TextoTienda();
        fuerzaActual.setText(Integer.toString(heroe.getFuerza()));
        fuerzaActual.setBounds(81, 289, 41, 23);
        add(fuerzaActual);

        TextoTienda magiaActual = new TextoTienda();
        magiaActual.setText(Integer.toString(heroe.getMagia()));
        magiaActual.setBounds(81, 323, 41, 23);
        add(magiaActual);

        TextoTienda defensaActual = new TextoTienda();
        defensaActual.setText(Integer.toString(heroe.getDefensa()));
        defensaActual.setBounds(81, 357, 41, 23);
        add(defensaActual);

        TextoTienda agilidadActual = new TextoTienda();
        agilidadActual.setText(Integer.toString(heroe.getAgilidad()));
        agilidadActual.setBounds(81, 391, 41, 23);
        add(agilidadActual);

        LabelPrincipal mostrarDinero = new LabelPrincipal();
        mostrarDinero.setFont(new Font("Bahnschrift", Font.BOLD, 15));
        mostrarDinero.setBackground(new Color(204, 204, 204));
        mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
        mostrarDinero.setBounds(10, 29, 109, 40);
        add(mostrarDinero);

        //Añadiendo BotonesTienda
        BotonesTienda comprarBombaP = new BotonesTienda("Bomba Pequeña");
        comprarBombaP.setBounds(131, 130, 159, 25);
        add(comprarBombaP);

        BotonesTienda comprarBombaG = new BotonesTienda("Bomba Grande");
        comprarBombaG.setBounds(131, 160, 159, 25);
        add(comprarBombaG);

        BotonesTienda comprarPocion = new BotonesTienda("Poción de Mana");
        comprarPocion.setBounds(131, 190, 159, 25);
        add(comprarPocion);

        BotonesTienda mejorarFuerza = new BotonesTienda("Fuerza +5");
        mejorarFuerza.setBounds(132, 289, 159, 25);
        add(mejorarFuerza);

        BotonesTienda mejorarMagia = new BotonesTienda("Magia +1");
        mejorarMagia.setBounds(132, 323, 159, 25);
        add(mejorarMagia);

        BotonesTienda mejorarAgilidad = new BotonesTienda("Agilidad +2");
        mejorarAgilidad.setBounds(131, 391, 159, 25);
        add(mejorarAgilidad);

        BotonesTienda mejorarDefensa = new BotonesTienda("Defensa +2");
        mejorarDefensa.setBounds(132, 357, 159, 25);
        add(mejorarDefensa);

        Botones botonAtras = new Botones("Volver al mapa");
        botonAtras.setBounds(767, 487, 215, 23);
        add(botonAtras);

        //Eventos de Botones, los cuales nos permiten comprar los objetos o mejorar nuestros atributos
        //gracias a las funciones de la clase heroe.
        comprarBombaP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                v.heroe.comprarObjetos(0, sonidoComprar, sonidoNoMoney);
                mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
                cantidadBombaP.setText(Integer.toString(heroe.getObjetosArray().get(0).getCantidad()));
            }
        });

        comprarBombaG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                heroe.comprarObjetos(1, sonidoComprar, sonidoNoMoney);
                mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
                cantidadBombaG.setText(Integer.toString(heroe.getObjetosArray().get(1).getCantidad()));
            }
        });

        comprarPocion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                heroe.comprarObjetos(2, sonidoComprar, sonidoNoMoney);
                mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
                cantidadPocion.setText(Integer.toString(heroe.getObjetosArray().get(2).getCantidad()));
            }
        });

        mejorarFuerza.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                heroe.mejorarEstadisticas(0, sonidoComprar, sonidoNoMoney);
                mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
                fuerzaActual.setText(Integer.toString(heroe.getFuerza()));
            }
        });

        mejorarMagia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                heroe.mejorarEstadisticas(1, sonidoComprar, sonidoNoMoney);
                mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
                magiaActual.setText(Integer.toString(heroe.getMagia()));
            }
        });

        mejorarDefensa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                heroe.mejorarEstadisticas(2, sonidoComprar, sonidoNoMoney);
                mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
                defensaActual.setText(Integer.toString(heroe.getDefensa()));
            }
        });

        mejorarAgilidad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                heroe.mejorarEstadisticas(3, sonidoComprar, sonidoNoMoney);
                mostrarDinero.setText("Oro: " + Integer.toString(heroe.getDinero()));
                agilidadActual.setText(Integer.toString(heroe.getAgilidad()));
                defensaActual.setText(Integer.toString(heroe.getDefensa()));
            }
        });

        botonAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Ventana.origenADestino(v, "tienda", "principal", 0);
            }
        });

        JLabel cuadro1 = new JLabel("");
        cuadro1.setBackground(new Color(204, 204, 204));
        cuadro1.setOpaque(true);
        cuadro1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        cuadro1.setBounds(39, 86, 339, 145);
        add(cuadro1);

        JLabel cuadro2 = new JLabel("");
        cuadro2.setBackground(new Color(204, 204, 204));
        cuadro2.setOpaque(true);
        cuadro2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        cuadro2.setBounds(39, 242, 339, 191);
        add(cuadro2);

        //Imagen de fondo
        JLabel imagenTienda = new JLabel("");
        imagenTienda.setBounds(0, 0, 1008, 536);
        imagenTienda.setIcon(new ImageIcon("./recursos/imagenes/tienda.jpg"));
        add(imagenTienda);
    }
}
