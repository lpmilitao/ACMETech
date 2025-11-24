package ui.components;

import javax.swing.*;
import java.awt.*;

import static ui.components.Padroes.*;

public class Texto extends JLabel {
    public Texto(String texto, boolean titulo) {
        super(texto);
        configurarEstilo(titulo);
    }

    public Texto(boolean titulo) {
        super();
        configurarEstilo(titulo);
    }

    private void configurarEstilo(boolean titulo) {
        setForeground(ROXO_TEXTO);
        setFont(FONTE_PADRAO);

        if (titulo) setFont(FONTE_TITULO);
    }
}
