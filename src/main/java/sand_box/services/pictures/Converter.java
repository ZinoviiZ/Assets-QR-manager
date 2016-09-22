package sand_box.services.pictures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Zinoviy on 9/13/16.
 */
public class Converter extends Thread {
    private BufferedImage img;
    private int width;
    private int height;
    private int size;
    private String path;
    private String name;
    private String format;

    public Converter(BufferedImage img, int size, String path,String name, String format) {
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.size = size;
        this.path = path;
        this.name = name;
        this.format = format;
    }
    public void run() {
        int max = Math.max(height,width);
        int newWidth = (int)((double)width / max * size);
        int newHeight = (int)((double)height / max * size);
        BufferedImage convertedImage = new BufferedImage(newWidth,newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = convertedImage.createGraphics();
        graphics2D.drawImage(img,0,0,newWidth,newHeight,null);
        graphics2D.dispose();

        File dir = new File(path);
        dir.mkdirs();
        File file = new File(dir,name  +"."+ format);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(convertedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
