/**
 *
 * @author Jeremy Herman
 * For CS 338 GUI Class
 *
 * This is the Main driver for the entire application.
 * It is responsible for:
 * - initializing views/models
 * - constructing the controller
 * - revealing the initial GUI
 *
 */

public class MixMatch {

    public static void main(String[] args) {

        MixMatchView theView = new MixMatchView();

        GameModel theModel = new GameModel();

        GameView theGameView = new GameView();

        MixMatchController theController = new MixMatchController(theView, theModel, theGameView);

        theController.showGame();

    }

}