import javax.swing.*;

/**
 *
 * @author Jeremy Herman
 * For CS 338 GUI Class
 *
 * This is the Model for the entire application.
 * It handles logic so the view doesn't have to.
 *
 * I originally had a MixMatchModel,
 * but since the MixMatchView does not require any logic,
 * I decided I should match the GameView with a "GameModel".
 *
 */

class GameModel {

    private int numMatches;
    private int recordedMatches;
    private int recordedClicks;

    /**
     *
     * Checks to see if two selected tiles contain matching images.
     * Returns an array of bools.
     * First value in array is the truth value of the current match.
     * Second value in array is if the game is over.
     *
     */

    boolean[] checkMatch(JToggleButton[] selectedButtons){

        boolean[] isMatch = {false, false};

        String typeOne = selectedButtons[0].getActionCommand().split("-")[0];
        String typeTwo = selectedButtons[1].getActionCommand().split("-")[0];

        if (typeOne.equals(typeTwo)) {
            recordedMatches++;
            isMatch[0] = true;
            isMatch[1] = numMatches == recordedMatches;
        }
        return isMatch;
    }


    /**
     * Initialization of the game model to reset recorded clicks, recorded matches, and total number of matches.
     */
    void setNumMatches(int num) {
        recordedClicks = 0;
        recordedMatches = 0;
        numMatches = num;
    }

    /**
     * Records the number of tiles selected by the user in a specific game.
     */
    void recordClick() {
        recordedClicks++;
    }

    /**
     * Getter for the number of recorded clicks.
     */
    int getNumClicks() {
        return recordedClicks;
    }

}