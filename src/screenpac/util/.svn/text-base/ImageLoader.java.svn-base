package screenpac.util;

import screenpac.model.Maze;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;
import java.io.File;

public class ImageLoader {

    public static void main(String[] args) {
        ImageLoader il = new ImageLoader();
        il.loadImages();
        System.out.println("Loaded all images");
    }

    public static BufferedImage[] loadImages() {
        images = new BufferedImage[4];
        images[0] = getImage("maze-a.png");
        images[1] = getImage("maze-b.png");
        images[2] = getImage("maze-c.png");
        images[3] = getImage("maze-d.png");
        return images;
    }

    static BufferedImage[] images;

    static {
//        images = new BufferedImage[4];
//        images[0] = getImage("level1-a.png");
//        images[1] = getImage("maze-b.png");
//        images[2] = getImage("maze-c.png");
//        images[3] = getImage("maze-d.png");
    }

    public static BufferedImage getImage(Maze maze) {
        return images[maze.getNumber()];
    }

//    public static String path = "/standalone/pacman/images/";
    public static String path = "images/";

    public static BufferedImage getImage(String file) {
        BufferedImage image = null;
        try {
//            URL url = ImageLoader.class.getResource(path + file);
//            url = new URL(path + file);
//            image = ImageIO.read(url);
            image = ImageIO.read(new File(path+file));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(file);
        }
        return image;
    }

}
