package ui.components;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class CampoTexto extends JTextField {
    private final Color ROXO = new Color(125, 135, 172);
    private final Color ROXO_CLARO = new Color(82, 85, 110);
    private final Font FONTE_PADRAO = new Font("SansSerif", Font.PLAIN, 16);
    private final int RAIO = 15;

    private Color corAtual;

    public CampoTexto(int colunas) {
        super(colunas);
        ajustarLayout();
    }

    public CampoTexto() {
        super();
        ajustarLayout();
    }

    private void ajustarLayout() {
        this.corAtual = ROXO;

        setOpaque(false);
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(11, 10, 11, 10));
        setFont(FONTE_PADRAO);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                corAtual = ROXO_CLARO;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                corAtual = ROXO;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(corAtual);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), RAIO, RAIO);

        super.paintComponent(g);
    }
}
