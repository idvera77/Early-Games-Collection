package componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JTextPane;

public class TextoTienda extends JTextPane {

    public TextoTienda() {

        //Usando Toolkit cambiamos el cursor por defecto cuando pulsamos dentro de las cajas de texto.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("./recursos/normal.png");
        Point point = new Point(0, 0);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        cursor = toolkit.createCustomCursor(img, point, "./recursos/normal.png");
        setCursor(cursor);

        //Indicando la fuente y el color de fondo de los bloques de texto.
        setFont(new Font("Bahnschrift", Font.BOLD, 15));
        setBackground(new Color(204, 204, 204));
    }
}
