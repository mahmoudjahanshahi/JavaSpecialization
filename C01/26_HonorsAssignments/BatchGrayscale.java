
/**
 * Assignment 1: Batch Grayscale
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import java.io.*;

public class BatchGrayscale {
    public ImageResource convertToGrey(ImageResource in) {
        ImageResource out = new ImageResource(in.getWidth(), in.getHeight());
        for (Pixel outP : out.pixels()) {
            Pixel inP = in.getPixel(outP.getX(), outP.getY());
            int average = (inP.getRed() + inP.getGreen() + inP.getBlue()) / 3;
            outP.setRed(average);
            outP.setGreen(average);
            outP.setBlue(average);
        }
        return out;
    }
    
    public void convertManyToGrey() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String name = image.getFileName();
            String newName = "grey-" + name;
            ImageResource geryImage = convertToGrey(image);
            geryImage.setFileName(newName);
            geryImage.save();
        }
    }
}
