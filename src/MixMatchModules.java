import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Jeremy Herman
 * For CS 338 GUI Class
 *
 * This is the MixMatchModules.
 * It is the location for any code modules that can be reused instead of reimplemented.
 * It may grow, it may go away. We'll see.
 *
 */

class MixMatchModules {

    /**
     * Static function for returning a title label for windows.
     */
    static JPanel getTitlePanel(Font f, String s) {
        JPanel titlePanel = new JPanel();
        titlePanel.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        JLabel titleLabel = new JLabel(s);
        titleLabel.setFont(f);
        titleLabel.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    /**
     * Useful for grabbing an image as a resource.
     * Since it would be repeated a lot across the application,
     * I decided to make a reusable module.
     */
    Image getImage(String filename) {
        Image myImage = null;
        try {
            myImage = ImageIO.read(getClass().getResourceAsStream(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myImage;
    }

}
