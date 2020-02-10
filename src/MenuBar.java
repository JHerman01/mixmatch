import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Jeremy Herman
 * For CS 338 GUI Class
 *
 * This class creates the menu bar for any screen that sets one.
 * Basic swing stuff for style and actions.
 *
 */

class MenuBar {
    private MixMatchModules mmm = new MixMatchModules();
    private ImageIcon iconExit = new ImageIcon(mmm.getImage("exit.png"));
    private ImageIcon iconInstructions = new ImageIcon(mmm.getImage("instruction.png"));
    private ImageIcon iconAbout = new ImageIcon(mmm.getImage("about_12.png"));

    JMenuItem fourBy = new JMenuItem("4 x 4");
    JMenuItem sixBy = new JMenuItem("6 x 6");
    JMenuItem eightBy = new JMenuItem("8 x 8");
    JMenuItem instructionsMenuButton = new JMenuItem("Instructions", iconInstructions);
    JMenuItem aboutMenuButton = new JMenuItem("About", iconAbout);
    JMenuItem exitMenuButton = new JMenuItem("Exit", iconExit);

    JMenuBar getMenuBarPanel() {
        JMenuBar mb = new JMenuBar();
        mb.setFont(Fonts.body);
        JMenu game = new JMenu("Game");
        game.setFont(Fonts.body);
        JMenu help = new JMenu("Help");
        help.setFont(Fonts.body);

        ImageIcon iconPuzzle = new ImageIcon(mmm.getImage("jigsaw.png"));

        JMenu playNewPuzzle = new JMenu("Play New Puzzle");
        playNewPuzzle.setIcon(iconPuzzle);
        playNewPuzzle.setFont(Fonts.body);

        fourBy.setFont(Fonts.body);
        sixBy.setFont(Fonts.body);
        eightBy.setFont(Fonts.body);

        playNewPuzzle.add(fourBy);
        playNewPuzzle.add(sixBy);
        playNewPuzzle.add(eightBy);


        instructionsMenuButton.setFont(Fonts.body);
        exitMenuButton.setFont(Fonts.body);

        game.add(playNewPuzzle);
        game.add(instructionsMenuButton);
        game.addSeparator();
        game.add(exitMenuButton);

        aboutMenuButton.setFont(Fonts.body);
        help.add(aboutMenuButton);

        exitMenuButton.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        instructionsMenuButton.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        fourBy.setAccelerator(KeyStroke.getKeyStroke('4', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        sixBy.setAccelerator(KeyStroke.getKeyStroke('6', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        eightBy.setAccelerator(KeyStroke.getKeyStroke('8', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        aboutMenuButton.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));

        mb.add(game);
        mb.add(help);

        fourBy.setToolTipText("Begin New 4x4 Puzzle");
        sixBy.setToolTipText("Begin New 6x6 Puzzle");
        eightBy.setToolTipText("Begin New 8x8 Puzzle");
        instructionsMenuButton.setToolTipText("Open Instructions Window");
        exitMenuButton.setToolTipText("Exit Application");
        aboutMenuButton.setToolTipText("Open About");

        return mb;
    }

}
