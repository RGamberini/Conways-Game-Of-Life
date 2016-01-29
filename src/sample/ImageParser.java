package sample;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rudy Gamberini on 1/25/2016.
 */
public class ImageParser {
    public static boolean[][] parseImage(String imagename) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resources\\" + imagename));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR: INVALID IMAGENAME");
        }

        boolean[][] result = new boolean[image.getWidth()][image.getHeight()];

        for (int x = 0; x < result.length; x++) {
            for (int y = 0; y < result[x].length; y++) {
                result[x][y] = image.getRGB(x, y) != -1;
            }
        }
        return result;
    }
}
