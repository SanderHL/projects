package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GamePanel extends JPanel{

    /* hvor mange korrekte klikk på riktig farge */
    int correctClicks;
    /* antall klikk man kan ha på den riktige fargen */
    int clickAmount;
    /*Hvor mange firkanter spillet skal ha*/
    int ruter = 9;

    private JPanel resultGame;

    /* Hvilke farger det er som har blitt brukt */
    private Color[] usedColors = new Color[ruter];

    /* Fargen som skal bli funnet */
    private Color solutionColor;
    private Random r = new Random();
    public GamePanel(JPanel rg) {
        /* Her er resultatet av Spilet rg */
        resultGame = rg;
        /* Her har jeg satt opp GridLayout */
        setLayout(new GridLayout(3, 0, 3, 4));

        /* for loop for rutene */
        for (int i = 0; i < ruter; i++){

            Color[] usableColors = new Color[]{Color.RED, Color.BLUE, Color.MAGENTA};
            ColoredPanel panel = new ColoredPanel(usableColors[r.nextInt(usableColors.length)]);
            add(panel);
            usedColors[i] = panel.getBackground();
        }
    }


    public void playGame() {
        /* Tilbakestiller klikkene */
        correctClicks = clickAmount = 0;

        /* Hent en tilfeldig farge som brukeren skal finne */
        solutionColor = usedColors[r.nextInt(usedColors.length)];

        /* Her så finner man alle de fargede panelene */

        for(int i = 0; i < getComponentCount(); i++){
            ColoredPanel panel = (ColoredPanel) getComponent(i);
            /* Fargen er den samme som solution color er */
            if(panel.getBackground() == solutionColor) {
                clickAmount++;
            }
            /* legger til mouselistener for solutioncolor */
            panel.setSolutionColor(solutionColor,  new TheMouseListener());
        }

        resultGame.setBackground(solutionColor);
    }

    private class TheMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            ColoredPanel panel = (ColoredPanel) e.getSource();
            panel.reset();
            /* hvis fargen er det samme som brukeren skal finne */
            if(panel.getBackground() == solutionColor){
                /* så æker antalle brukerklikk */
                correctClicks++;
            }else{
                /* game over som viser at brukeren har tapt hvis de velger feil farge */
                correctClicks = 0;
                panel.add(new JLabel("Game over"));
                resultGame.add(new JLabel("Game over"));
            }
            /* hvis alle fargene som er riktige er klikket på så vinner du */
            if(correctClicks == clickAmount){
                panel.add(new JLabel("You won"));
                resultGame.add(new JLabel("You won"));
            }
            /* resultatpanelet som blir oppdatert */
            resultGame.updateUI();
        }
    }
}
