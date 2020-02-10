import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Jeremy Herman
 * For CS 338 GUI Class
 *
 * This is the main MixMatch View for the entire application.
 * It is responsible for managing the main menu and opening other screens.
 *
 * About and Instructions will not function in the final product as they do here.
 *
 */

class MixMatchView extends JFrame {

    private JButton instructionsButton = new JButton("Instructions");
    private MixMatchModules mmm = new MixMatchModules();
    private JButton exitButton = new JButton("Exit");
    private Dimension buttonDimensions = new Dimension(200, 150);
    private JButton[] puzzleButtons = generatePuzzleButtons();
    private MenuBar mb = new MenuBar();

    /**
     * Initializes main game window.
     */
    MixMatchView() {

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("MixMatch");

        this.setJMenuBar(mb.getMenuBarPanel());

        mainMenuPanel.add(getMenuPanel(), BorderLayout.CENTER);

        this.add(mainMenuPanel);

    }

    void setVisible() {
        this.setVisible(true);
    }

    /**
     * Menu panel that houses the puzzle select buttons panel and the secondary buttons panel.
     */
    private JPanel getMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        menuPanel.add(MixMatchModules.getTitlePanel(Fonts.titleFont, "Welcome to MixMatch"));
        menuPanel.add(getGameSelectPanel());
        menuPanel.add(getSecondaryButtonPanel());


        menuPanel.add(Box.createVerticalGlue());
        return menuPanel;
    }

    /**
     * Inner panel that houses the puzzle select buttons
     */
    private JPanel getGameSelectPanel() {
        JPanel gameSelectPanel = new JPanel();
        gameSelectPanel.add(Box.createHorizontalGlue());
        gameSelectPanel.setLayout(new BoxLayout(gameSelectPanel, BoxLayout.X_AXIS));

        for (JButton b : puzzleButtons) {
            gameSelectPanel.add(b);
        }

        gameSelectPanel.add(Box.createHorizontalGlue());
        return gameSelectPanel;
    }

    /**
     * Secondary button panel holds the instructions and exit button on main window.
     */
    private JPanel getSecondaryButtonPanel() {
        JPanel secondaryButtonPanel = new JPanel();
        secondaryButtonPanel.add(Box.createHorizontalGlue());
        secondaryButtonPanel.setLayout(new BoxLayout(secondaryButtonPanel, BoxLayout.X_AXIS));

        instructionsButton.setFont(Fonts.body);
        exitButton.setFont(Fonts.body);

        instructionsButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);

        instructionsButton.setPreferredSize(buttonDimensions);
        exitButton.setPreferredSize(buttonDimensions);

        instructionsButton.setToolTipText("Open Instructions Window");
        exitButton.setToolTipText("Exit Application");

        secondaryButtonPanel.add(instructionsButton);
        secondaryButtonPanel.add(exitButton);
        secondaryButtonPanel.add(Box.createHorizontalGlue());

        return secondaryButtonPanel;
    }

    /**
     * Returns the three buttons for selecting a puzzle to open.
     */
    private JButton[] generatePuzzleButtons() {
        String[] puzzleSize = {"4 x 4", "6 x 6", "8 x 8"};
        JButton[] buttons = new JButton[3];

        for (int i = 0; i < 3; i++) {
            JLabel play = new JLabel("Play");
            JLabel puzzle = new JLabel("Puzzle");
            JLabel str = new JLabel(puzzleSize[i]);
            play.setFont(Fonts.body);
            str.setFont(Fonts.body);
            puzzle.setFont(Fonts.body);
            play.setAlignmentX(CENTER_ALIGNMENT);
            str.setAlignmentX(CENTER_ALIGNMENT);
            puzzle.setAlignmentX(CENTER_ALIGNMENT);
            JButton b = new JButton();
            b.add(Box.createVerticalGlue());
            b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS));

            b.setToolTipText("Begin a " + puzzleSize[i] + " puzzle.");
            b.add(play);
            b.add(str);
            b.add(puzzle);
            b.add(Box.createVerticalGlue());
            buttons[i] = b;
        }


        return buttons;
    }

    /**
     *
     * Each of these listeners handle the buttons in the window and their respective menu bar options.
     *
     */

    void addNew4x4PuzzleListener(ActionListener listenerForNew4x4Puzzle) {
        puzzleButtons[0].addActionListener(listenerForNew4x4Puzzle);
        mb.fourBy.addActionListener(listenerForNew4x4Puzzle);
    }

    void addNew6x6PuzzleListener(ActionListener listenerForNew6x6Puzzle) {
        puzzleButtons[1].addActionListener(listenerForNew6x6Puzzle);
        mb.sixBy.addActionListener(listenerForNew6x6Puzzle);
    }

    void addNew8x8PuzzleListener(ActionListener listenerForNew8x8Puzzle) {
        puzzleButtons[2].addActionListener(listenerForNew8x8Puzzle);
        mb.eightBy.addActionListener(listenerForNew8x8Puzzle);
    }

    void addInstructionsListener(ActionListener listenerForInstructions) {
        instructionsButton.addActionListener(listenerForInstructions);
        mb.instructionsMenuButton.addActionListener(listenerForInstructions);
    }

    void addAboutListener(ActionListener listenerForAbout) {
        mb.aboutMenuButton.addActionListener(listenerForAbout);
    }

    void addExitListener(ActionListener listenerForExit) {
        exitButton.addActionListener(listenerForExit);
        mb.exitMenuButton.addActionListener(listenerForExit);
    }

    /**
     * Two functions below are responsible for displaying the about and instructions windows.
     */
    void displayInstructions() {
        String str = getInstructionsHTML();
        String title = "MixMatch Instructions";
        String filename = "instruction_64.png";
        accessoryWindow(title,  str, filename, Fonts.body);
    }

    void displayAbout() {
        String str = getAboutHTML();
        String title = "MixMatch About";
        String filename = "about_64.png";
        accessoryWindow(title, str, filename, Fonts.header);
    }

    /**
     * Renders a JOptionPane for about and instructions.
     */
    private void accessoryWindow(String title, String msgStr, String filename, Font f) {
        JLabel msg = new JLabel(msgStr);
        msg.setFont(f);
        ImageIcon icon = new ImageIcon(mmm.getImage(filename));
        JOptionPane.showOptionDialog(
                null,
                msg,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon,
                null,
                null);
    }

    /**
     * Returns information as HTML in a String.
     * I do this because HTML is a good way to organize text in a JLabel.
     * For example, using "\n" does not work in a JLabel, so using "<br/>" in HTML achieves a newline.
     */
    private String getAboutHTML() {
        return "<html>" +
                "MixMatch is a memory matching tile game.<br/><br/>" +
                "Created by Jeremy Herman<br/><br/>" +
                "For Instructions on How to Play:<br/>" +
                "<i><b>Game->Instructions</b></i> or <i><b>CTRL+I</b></i><br/><br/>" +
                "Tile Icons Created by Jeremy Herman<br/><br/>" +
                "Other Icons Borrowed from \"flaticon.com\"" +
                "</html>";
    }

    private String getInstructionsHTML() {
        return "<html>" +
                "MixMatch is easy to learn, just follow these steps:<br/><br/><ol>" +
                "<li>Pay attention to the beginning of a game, because the <br/>" +
                "tiles will be visible for a short period of time.</li><br/>" +
                "<li>Select two tiles to find matching pairs.<br/>" +
                "If the two you select did not match, just try again!</li><br/>" +
                "<li>When matches are found, the tiles will be disabled.</li><br/>" +
                "<li>Reveal all of the matches to win!</li></ol><br/>" +
                "</html>";
    }

}