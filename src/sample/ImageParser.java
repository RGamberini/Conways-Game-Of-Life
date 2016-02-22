package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rudy Gamberini on 1/25/2016.
 */
public class ImageParser {
    public static boolean[][] parseImage(String imagename) {
        return parseImage(new File("resources\\" + imagename));
    }

    public static boolean[][] parseImage(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR: INVALID IMAGENAME");
        }

        boolean[][] result = new boolean[image.getWidth()][image.getHeight()];

        for (int x = 0; x < result.length; x++) {
            for (int y = 0; y < result[x].length; y++) {
                result[x][y] = image.getRGB(x, y) == Color.BLACK.getRGB();
            }
        }
        return result;
    }

    public static void saveImage(File file, boolean[][] state) {
        BufferedImage image = new BufferedImage(state.length, state.length, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[x].length; y++) {
                if (state[x][y])
                    image.setRGB(x, y, Color.BLACK.getRGB());
                else
                    image.setRGB(x, y, Color.WHITE.getRGB());
            }
        }
        try {
            ImageIO.write(image, "BMP", file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: INVALID IMAGE FILE TO SAVE");
        }
    }
}
