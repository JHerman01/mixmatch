import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jeremy Herman
 * For CS 338 GUI Class
 *
 * This is the Controller for the entire application.
 * It handles all communication/actions for views and model.
 *
 */

class MixMatchController {

    private MixMatchView theView;
    private GameView theGameView;
    private GameModel theModel;

    MixMatchController(MixMatchView theView, GameModel theModel, GameView theGameView) {
        this.theView = theView;
        this.theModel = theModel;
        this.theGameView = theGameView;

        this.theView.addNew4x4PuzzleListener(new New4x4PuzzleListener());
        this.theView.addNew6x6PuzzleListener(new New6x6PuzzleListener());
        this.theView.addNew8x8PuzzleListener(new New8x8PuzzleListener());
        this.theView.addInstructionsListener(new InstructionsListener());
        this.theView.addAboutListener(new AboutListener());
        this.theView.addExitListener(new ExitListener());

        initializeGame();
    }

    /**
     * Helper function that loops through all game tiles.
     * Each tile is a  JToggleButton.
     * For each tile, add action listener.
     */
    private void tileController(JToggleButton[] tiles) {
        for (JToggleButton jtb : tiles) {
            jtb.addActionListener(new TileListener());
        }
    }

    private int getNumMatches(int n) {
        return (n*n)/2;
    }

    /**
     * Does not reveal game view. Reveals MixMatch main menu view.
     */
    void showGame() {
        this.theView.setVisible();
    }

    /**
     * Initialize game re-initializes any game view information. Useful for starting a new game and closing a game.
     */
    private void initializeGame() {
        theGameView.setVisible(false);
        theGameView.getContentPane().removeAll();
        theGameView = new GameView();
        theGameView.addCloseListener(new CloseListener());
        theGameView.addNew4x4PuzzleListener(new New4x4PuzzleListener());
        theGameView.addNew6x6PuzzleListener(new New6x6PuzzleListener());
        theGameView.addNew8x8PuzzleListener(new New8x8PuzzleListener());
        theGameView.addInstructionsListener(new InstructionsListener());
        theGameView.addAboutListener(new AboutListener());
        theModel.setNumMatches(0);
    }

    /**
     * Listeners for each new game action.
     * They re-initialize the game view by calling "initializeGame()".
     * Use theGameView to get the tiles.
     * Sends tiles over to tileController to map action listeners for each of them.
     */
    class New4x4PuzzleListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                initializeGame();
                JToggleButton[] tiles = theGameView.reveal(4);
                theModel.setNumMatches(getNumMatches(4));
                tileController(tiles);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class New6x6PuzzleListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                initializeGame();
                JToggleButton[] tiles = theGameView.reveal(6);
                theModel.setNumMatches(getNumMatches(6));
                tileController(tiles);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class New8x8PuzzleListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                initializeGame();
                JToggleButton[] tiles = theGameView.reveal(8);
                theModel.setNumMatches(getNumMatches(8));
                tileController(tiles);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Action listeners for pulling up instructions/about windows.
     */
    class InstructionsListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                theView.displayInstructions();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class AboutListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                theView.displayAbout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * CloseListener actually uses initializeGame() to render default game window which is hidden.
     */
    class CloseListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                initializeGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Exit quits the application.
     */
    class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This TileListener is the function that communicates between the view and model.
     * Handles tile selection, tile matching, and endgame.
     */

    class TileListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                JToggleButton[] selectedButtons = theGameView.tilePressed(ae);
                theModel.recordClick();
                if (selectedButtons[0] != null && selectedButtons[1] != null) {
                    boolean[] isMatch = theModel.checkMatch(selectedButtons);
                    if (isMatch[0]) {
                        theGameView.recordMatch();
                        if (isMatch[1]) {
                            theGameView.endGame(theModel.getNumClicks());
                        }
                    }
                    else {
                        theGameView.resetTiles();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
