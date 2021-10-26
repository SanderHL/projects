package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class ColoredPanel extends JPanel {
    /* setter fargene på panelene */
    Color panelColor;
    Color solutionColor;
    public ColoredPanel(Color farge) {
        panelColor = farge;
        setBackground(farge);
    }
    public void setSolutionColor(Color farge, MouseListener ml){
       /* bakgrunnen til rutene før de er valgt */
        setBackground(Color.GRAY);
        solutionColor = farge;
        addMouseListener(ml);
    }
    public void reset() {
        setBackground(panelColor); }
}

