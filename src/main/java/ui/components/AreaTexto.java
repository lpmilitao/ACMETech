package ui.components;

import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import static ui.components.Padroes.*;

public class AreaTexto extends JTextPane {

    public AreaTexto() {
        super();
        configurarDesign();
    }

    private void configurarDesign() {
        setOpaque(false);
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setFont(FONTE_PADRAO);
        setCaretColor(ROXO);
        setEditable(false);

        StyledDocument doc = this.getStyledDocument();
        SimpleAttributeSet estilo = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(estilo, 0.4f);
        doc.setParagraphAttributes(0, doc.getLength(), estilo, false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }


    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(ROXO);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), RAIO, RAIO);

        super.paintComponent(g);
    }
}