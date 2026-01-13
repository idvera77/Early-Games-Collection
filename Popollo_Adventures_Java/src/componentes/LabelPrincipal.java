package componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class LabelPrincipal extends JLabel {

    public LabelPrincipal() {

        //Configuracion de los JLabel que uso en la pantalla del mapa (tipo de letra, tamaño, borde, color, etc).
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        setBackground(SystemColor.menu);
        setOpaque(true);
        setBorder(new LineBorder(new Color(0, 0, 0), 3));
    }
}
