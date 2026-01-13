package componentes;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class LabelCombateEvento extends JLabel {

    public LabelCombateEvento() {

        //Configuracion de los JLabel que uso en la pantalla del mapa (tipo de letra, tamaño, borde, color, etc).
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Bahnschrift", Font.BOLD, 16));
        setBackground(new Color(208, 232, 226));
        setOpaque(true);
        setBorder(new LineBorder(new Color(0, 0, 0), 3));
    }
}
