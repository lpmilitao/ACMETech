package ui.components;

import javax.swing.*;

import java.awt.*;

public class Texto extends JLabel {
    private final Color ROXO_TEXTO = new Color(43, 45, 66);
    private final Font FONTE_PADRAO = new Font("SansSerif", Font.PLAIN, 16);
    private final Font FONTE_TITULO = new Font("SansSerif", Font.BOLD, 26);

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
