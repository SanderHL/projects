package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.DARK_GRAY;
import static java.lang.System.*;

public class MemoryGameWindow extends JFrame implements ActionListener {
    private JPanel resultpanel = new JPanel();

    private GamePanel gamepanel = null;
    private JMenuItem newGame = null;
    private JMenuItem playGame = null;
    private JMenuItem endprogram = null;

    public MemoryGameWindow() {
        /* tittelen på spillet */
        setTitle("Rute Spill");

        /* hvor stort vinduet skal være */
        setSize(800, 800);
        GameMenubar menu = new GameMenubar(this);
        setJMenuBar(menu);

        /* ny border layout som blir satt som synlig */
        setLayout(new BorderLayout());
        setVisible(true);

        /* farge på resultatpanelet */
        resultpanel.setBackground(DARK_GRAY);
        gamepanel = new GamePanel(resultpanel);

        /* legger til gamepanel og resultpanel */
        add(gamepanel);
        add(resultpanel,
                BorderLayout.NORTH);
    }

    public void startNewBoard() {
        if (resultpanel == null && gamepanel == null) {
        } else {
            remove(resultpanel);
            remove(gamepanel);
        }
        /* posisjon og farge til resultatpanelet */
        resultpanel = new JPanel();
        resultpanel.setBackground(DARK_GRAY);
        gamepanel = new GamePanel(resultpanel);
        add(resultpanel, BorderLayout.NORTH);
        add(gamepanel);

        /* oppdaterer user interface */
        gamepanel.updateUI();
        resultpanel.updateUI();

    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object sr = actionEvent.getSource();
        if (newGame == sr) {
            startNewBoard();
            /* Aktiverer funksjonen play game*/
            playGame.setEnabled(true);
        }
        if (playGame == sr) {
            gamepanel.playGame();
            /* Dette deaktiverer play game til man trykker på nytt board*/
            playGame.setEnabled(false);
            gamepanel.updateUI();
        }//
        if (endprogram == sr) exit(0);
    }

    public class GameMenubar extends JMenuBar {
        /* legger til menyen */
        public GameMenubar(MemoryGameWindow window) {
            JMenu Meny = new JMenu("Meny");
            add(Meny);

            /* Legger til nye valg i menyen til apllikasjonen med actionlistener */
            Meny.add(newGame = new JMenuItem("New board"));
            newGame.addActionListener(window);

            Meny.add(playGame = new JMenuItem("Play"));
            playGame.addActionListener(window);

            Meny.add(endprogram = new JMenuItem("End Program"));
            endprogram.addActionListener(window);

        }
    }
}

