package ui.components;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CampoTexto extends JTextField {
    private final Color COR_PADRAO = new Color(125, 135, 172);
    private final Color COR_FOCO = new Color(82, 85, 110);
    private Color corAtual;
    private int raio = 15;

    public CampoTexto(int colunas) {
        super(colunas);
        setSize(colunas,20);
        ajustarLayout();
    }

    private void ajustarLayout() {
        this.corAtual = COR_PADRAO;

        setOpaque(false);
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(11, 10, 11, 10));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                corAtual = COR_FOCO;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                corAtual = COR_PADRAO;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(corAtual);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), raio, raio);

        super.paintComponent(g);
    }
}
