
/**
 * Assignment 1: Image Inversion
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import java.io.*;

public class ImageInversion {
    public ImageResource invertImage(ImageResource in) {
        ImageResource out = new ImageResource(in.getWidth(), in.getHeight());
        for (Pixel outP : out.pixels()) {
            Pixel inP = in.getPixel(outP.getX(), outP.getY());
            outP.setRed(255 - inP.getRed());
            outP.setGreen(255 - inP.getGreen());
            outP.setBlue(255 - inP.getBlue());
        }
        return out;
    }
    
    public void invertManyImages() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String name = image.getFileName();
            String newName = "inverted-" + name;
            ImageResource invImage = invertImage(image);
            invImage.setFileName(newName);
            invImage.save();
        }
    }
}
