package ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Botao extends JButton {
    private final Color COR_PADRAO = new Color(57, 65, 89);
    private final Color COR_HOVER = new Color(82, 85, 110);
    private Color corAtual;
    private int raio = 15;

    public Botao(String texto) {
        super(texto);

        this.corAtual = COR_PADRAO;
        setText(texto);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                corAtual = COR_HOVER;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
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
