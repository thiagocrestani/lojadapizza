/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class will resize all the images in a given folder
 *
 * @author pankaj
 *
 */
public class JavaImageResizer {

    public void resize(String arquivo) throws IOException {
        Image img = null;
        BufferedImage tempPNG = null;
        BufferedImage tempJPG = null;
        File newFilePNG = null;
        File newFileJPG = null;
        img = ImageIO.read(new File(arquivo));
        if(img.getWidth(null) > 700){
            float p1 = (100*(img.getWidth(null)-700))/img.getWidth(null);
            float p2 = img.getHeight(null)*p1/100;
            float p3 = img.getHeight(null)-p2;       
            tempPNG = resizeImage(img, 700, (int) p3);
            tempJPG = resizeImage(img, 700, (int) p3);
            newFilePNG = new File(arquivo);
            newFileJPG = new File(arquivo);
            ImageIO.write(tempPNG, "png", newFilePNG);
            ImageIO.write(tempJPG, "jpg", newFileJPG);
        }
    }

    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
}
