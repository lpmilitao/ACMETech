package ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.List;

public class ComboBox<E> extends JComboBox<E> {
    private Color corNormal = new Color(125, 135, 172);
    private Color corFoco = new Color(82, 85, 110);
    private Color corAtual;
    private int raio = 15;

    public ComboBox(List<E> items) {
        super();
        for (E item : items) {
            addItem(item);
        }
        inicializar();
    }

    private void inicializar() {
        corAtual = corNormal;
        setOpaque(false);
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(5, 10, 5, 10));

        setUI(new ComboBoxUI());
        setRenderer(new Renderizador());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(corAtual);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), raio, raio);

        super.paintComponent(g);
    }

    private class ComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton botaoSeta = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    int w = getWidth();
                    int h = getHeight();
                    int tamanhoSeta = 8;

                    int[] xPoints = {w / 2 - tamanhoSeta / 2, w / 2 + tamanhoSeta / 2, w / 2};
                    int[] yPoints = {h / 2 - tamanhoSeta / 4, h / 2 - tamanhoSeta / 4, h / 2 + tamanhoSeta / 4};

                    g2.setColor(Color.WHITE);
                    g2.fillPolygon(xPoints, yPoints, 3);
                }
            };

            botaoSeta.setBorder(null);
            botaoSeta.setContentAreaFilled(false);
            botaoSeta.setFocusPainted(false);
            botaoSeta.setOpaque(false);
            return botaoSeta;
        }

        // Remove o fundo quadrado azul feio que aparece ao clicar
        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            // Não faz nada intencionalmente
        }
    }

    private class Renderizador extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            setForeground(Color.WHITE);
            setBorder(new EmptyBorder(5, 10, 5, 10));

            if (index == -1) {
                setOpaque(false);
                setBackground(new Color(0,0,0,0));
            } else {
                setOpaque(true);
                if (isSelected) {
                    setBackground(corFoco);
                } else {
                    setBackground(corNormal);
                }
            }

            return this;
        }
    }
}