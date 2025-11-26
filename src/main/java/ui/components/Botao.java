package ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ui.components.Padroes.*;

public class Botao extends JButton {
    private Color corAtual;

    public Botao(String texto) {
        super(texto);

        this.corAtual = ROXO_ESCURO;
        setText(texto);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.BOLD, 16));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled()) { //evento de hover apenas se o botao está habilitado
                    corAtual = ROXO_CLARO;
                    repaint();
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                corAtual = ROXO_ESCURO;
                repaint();
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

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
