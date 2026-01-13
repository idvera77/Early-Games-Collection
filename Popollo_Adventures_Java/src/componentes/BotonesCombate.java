package componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import pantallas.Ventana;

public class BotonesCombate extends JButton {

    public BotonesCombate(String txt) {
        super(txt);

        //Usando Toolkit cambiamos el cursor por defecto cuando pulsamos dentro de estos Botones.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("./recursos/batalla.png");
        Point point = new Point(0, 0);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        cursor = toolkit.createCustomCursor(img, point, null);
        setCursor(cursor);

        //Indicando la fuente y el borde de los botones.
        setBackground(new Color(208, 232, 226));
        setBorder(new LineBorder(new Color(0, 0, 0), 3));

        //Cuando entramos en un boton debe cambiar de color, engordar el borde y al salir debe volver a su estado original.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(204, 255, 255));
                setBorder(new LineBorder(new Color(0, 0, 0), 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(null);
                setBackground(new Color(208, 232, 226));
                setBorder(new LineBorder(new Color(0, 0, 0), 3));
            }
        });
    }
}
