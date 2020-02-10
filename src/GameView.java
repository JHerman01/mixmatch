import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 *
 * @author Jeremy Herman
 *
 * This is the GameView.
 * It is responsible for:
 * - showing the current puzzle board
 * - binding the appropriate actions through the controller.
 *
 */

class GameView extends JFrame {

    private MixMatchModules mmm = new MixMatchModules();
    private JButton closeButton = new JButton("Close");
    private JButton instructionsButton = new JButton("Instructions");
    private JPanel gamePanel = new JPanel();
    private Dimension buttonDimensions = new Dimension(65, 65);
    private final ImageIcon hidden = new ImageIcon(mmm.getImage("hidden_icon.png"));
    private int size;
    private int numSelected;
    private MenuBar mb = new MenuBar();
    private JToggleButton[] selectedButtons = new JToggleButton[2];


    /**
     * GameView constructor sets top-level layout.
     * Adds header title and footer buttons that are constant across board sizes.
     */
    GameView() {

        gamePanel.setLayout(new BorderLayout());

        gamePanel.add(getFooterPanel(), BorderLayout.SOUTH);
        JPanel title = MixMatchModules.getTitlePanel(Fonts.header, "Find the Matching Pairs!");
        title.setBorder(new EmptyBorder(10, 0, 30, 0));
        gamePanel.add(title, BorderLayout.NORTH);

        this.add(gamePanel);

    }

    /**
     * This reveal function is used by the controller to basically refresh the game window.
     * It is useful since the window sizes and tiles will change depending on size of game.
     *
     * - Returns an array of JToggleButtons which are the tiles to be matched.
     * - Initializes those with a "hidden" icon.
     * - Uses a swing timer to display the tiles icon, then hides them after a time based off of the size of the game.
     * -
     */
    JToggleButton[] reveal(int size) {
        this.size = size;
        int len = (int) Math.pow(size, 2);
        int[] boxes = getBoxes(len);
        JToggleButton[] tiles = new JToggleButton[len];
        for (int i = 0; i < len; i++) {
            ImageIcon buttonIcon = MixIcons.values()[boxes[i]].image;
            tiles[i] = new JToggleButton(hidden);
            tiles[i].setSelectedIcon(buttonIcon);
            tiles[i].setActionCommand((MixIcons.values()[boxes[i]]).toString() + "-" + i);
            tiles[i].setPreferredSize(buttonDimensions);
            tiles[i].setSelected(true);
        }
        int windowSize = size*115;

        if (size == 4) {
            this.setSize(windowSize, windowSize+30);
        }
        else {
            this.setSize(windowSize, windowSize);
        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("MixMatch " + size + " x " + size + " Game");
        numSelected = 16;
        javax.swing.Timer timer = new javax.swing.Timer(size*800 , new TilePreviewListener(tiles));
        timer.setRepeats(false);
        timer.start();
        gamePanel.add(generateBoard(tiles), BorderLayout.CENTER);
        this.setJMenuBar(mb.getMenuBarPanel());
        return tiles;
    }

    /**
     *
     * These are all of the listeners for the menu buttons/footer buttons
     *
     */

    void addCloseListener(ActionListener listenerForClose) {
        closeButton.addActionListener(listenerForClose);
        mb.exitMenuButton.addActionListener(listenerForClose);
    }

    void addInstructionsListener(ActionListener listenerForInstructions) {
        instructionsButton.addActionListener(listenerForInstructions);
        mb.instructionsMenuButton.addActionListener(listenerForInstructions);
    }

    void addNew4x4PuzzleListener(ActionListener listenerForNew4x4Puzzle) {
        mb.fourBy.addActionListener(listenerForNew4x4Puzzle);
    }

    void addNew6x6PuzzleListener(ActionListener listenerForNew6x6Puzzle) {
        mb.sixBy.addActionListener(listenerForNew6x6Puzzle);
    }

    void addNew8x8PuzzleListener(ActionListener listenerForNew8x8Puzzle) {
        mb.eightBy.addActionListener(listenerForNew8x8Puzzle);
    }

    void addAboutListener(ActionListener listenerForAbout) {
        mb.aboutMenuButton.addActionListener(listenerForAbout);
    }

    /**
     *
     * Returns the footer buttons (instructions and close).
     *
     */

    private JPanel getFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        footerPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        closeButton.setFont(Fonts.body);
        instructionsButton.setFont(Fonts.body);

        closeButton.setToolTipText("Close the Game Window");
        instructionsButton.setToolTipText("Open Instructions Window");

        footerPanel.add(instructionsButton);
        footerPanel.add(closeButton);

        return footerPanel;
    }

    /**
     *
     * Returns a jpanel that contains the grid of tiles to be matched.
     *
     */

    private JPanel generateBoard(JToggleButton[] tiles) {
        JPanel board = new JPanel();
        board.add(Box.createVerticalGlue());
        board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
        int count = 0;

        for (int j = 0; j < size; j++) {
            JPanel row = new JPanel();
            row.add(Box.createHorizontalGlue());
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            for (int i = 0; i < size; i++) {
                row.add(tiles[count]);
                if (i < size - 1)
                    row.add(Box.createHorizontalStrut(15));
                count++;
            }
            row.add(Box.createHorizontalGlue());
            board.add(row);
        }
        board.add(Box.createVerticalGlue());

        return board;
    }

    /**
     *
     * Returns and int array of indexes to be used to choose a specific ImageIcon for a tile
     *
     */

    private static int[] getBoxes(int len) {
        int[] boxes = new int[len];
        for (int i = 0; i < len; i++) {
            boxes[i] = i%(len/2);
        }

        shuffle(boxes);
        return boxes;
    }

    /**
     *
     * Shuffles an array of ints to represent randomly organizes indexes for each tile.
     *
     */

    private static void shuffle(int[] arr) {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < arr.length; i++) {
            int index = rand.nextInt(i + 1);
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }

    /**
     *
     * tilePressed is a function that handles all of the user input for matching tiles.
     * It will recognize how many current tiles are selected and if it is anything other than 0 or 1 selected it will immediately ignore an input.
     * This way when showing two selected tiles, those reset on their own, and this function ignores a third selection.
     *
     */

    JToggleButton[] tilePressed(ActionEvent ae) {
        JToggleButton current = (JToggleButton) ae.getSource();
        if (numSelected == 0) {
            numSelected++;
            selectedButtons[0] = current;
            selectedButtons[1] = null;
        }
        else if (numSelected == 1) {
            if (selectedButtons[0].getActionCommand().equals(current.getActionCommand())) {
                numSelected = 0;
            }
            else {
                numSelected++;
                selectedButtons[1] = current;
            }
        }
        else {
            ((JToggleButton) ae.getSource()).setSelected(false);
        }

        return selectedButtons;
    }

    /**
     * Custom ActionListener for resetting two selected tiles that do not match.
     */

    private ActionListener resetTask = new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            if (selectedButtons[0] != null) {
                selectedButtons[0].setSelected(false);
                selectedButtons[0] = null;
            }
            if (selectedButtons[1] != null) {
                selectedButtons[1].setSelected(false);
                selectedButtons[1] = null;
            }
            numSelected = 0;
        }
    };

    /**
     * Handles the beginning game tile preview.
     * After the specified timer in "reveal", this action hides all of the tiles.
     */

    private class TilePreviewListener implements ActionListener {
        private JToggleButton[] tiles;

        TilePreviewListener(JToggleButton[] tiles) {
            this.tiles = tiles;
        }

        public void actionPerformed(ActionEvent e) {
            for (JToggleButton t : tiles) {
                t.setSelected(false);
            }
            numSelected = 0;
        }
    }

    /**
     * This function permits the user to see two non-matching tiles before they are hidden again.
     */

    void resetTiles() {
        javax.swing.Timer timer = new javax.swing.Timer(900 , resetTask);
        timer.setRepeats(false);
        timer.start();
        numSelected = -1;
    }

    /**
     * When a match is made, this will disable the  matching tiles and reset the stored selected tiles.
     */

    void recordMatch() {
        selectedButtons[0].setEnabled(false);
        selectedButtons[0] = null;
        selectedButtons[1].setEnabled(false);
        selectedButtons[1] = null;
        numSelected = 0;
    }

    /**
     * Renders the popup for the completed game message.
     */

    void endGame(int numClicks) {
        String title = "Puzzle Complete";
        String clicks = "Puzzle completed in " + numClicks + " clicks!";
        ImageIcon icon = new ImageIcon(mmm.getImage("checked.png"));
        JLabel msg = new JLabel(clicks);
        msg.setFont(Fonts.header);
        int input =  JOptionPane.showOptionDialog(
                null,
                msg,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon,
                null,
                null);
        if(input == JOptionPane.OK_OPTION)
        {
            this.setVisible(false);
        }
    }

}
