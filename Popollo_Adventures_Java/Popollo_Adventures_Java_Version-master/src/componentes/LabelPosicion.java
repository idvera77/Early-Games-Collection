package componentes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelPosicion extends JLabel {

    public LabelPosicion() {

        //Al utilizar tantos JLabel de este tipo en el mapa he decidido crear este componente para no repetir tanto codigo.
        setVisible(false);
        setIcon(new ImageIcon("./recursos/completado.png"));
    }
}
