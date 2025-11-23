package ui.components;

import javax.swing.*;
import java.awt.*;

public class Texto extends JLabel {
    private final Font FONTE_PADRAO = new Font("SansSerif", Font.PLAIN, 14);
    private final Font FONTE_TITULO = new Font("SansSerif", Font.BOLD, 20);
    private final Color COR_PADRAO = new Color(43, 45, 66);

    public Texto(String texto, boolean titulo) {
        super(texto);
        configurarEstilo(titulo);
    }

    public Texto(boolean titulo) {
        super();
        configurarEstilo(titulo);
    }

    private void configurarEstilo(boolean titulo) {
        setForeground(COR_PADRAO);
        if (titulo) {
            setFont(FONTE_TITULO);
            return;
        }
        setFont(FONTE_PADRAO);
    }
}
