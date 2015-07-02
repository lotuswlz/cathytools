package com.thoughtworks.cathywu.tools.imagetools;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author lzwu
 * @since 7/2/15
 */
public class ImageUtils {

    private static final Logger LOGGER = Logger.getLogger(ImageUtils.class);

    private int baseWidth;
    private int baseHeight;

    private ImageUtils(int baseWidth, int baseHeight) throws IOException {
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    public static ImageUtils createInstanceFor(int baseWidth, int baseHeight) throws IOException {
        return new ImageUtils(baseWidth, baseHeight);
    }

    public boolean resizeImage(File imageFile, String toPath) {
        try {
            Image image = ImageIO.read(imageFile);
            ResizeHandler handler = chooseCorrectHandler(image);
            BufferedImage bufferedImage = handler.process(image);

            File destImageFile = createNewImageFile(imageFile.getName(), toPath);
            OutputStream imageOutPutStream = new FileOutputStream(destImageFile);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(imageOutPutStream);
            encoder.encode(bufferedImage);
            imageOutPutStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    private ResizeHandler chooseCorrectHandler(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        if (width > height) {

        }
        return null;
    }

    private File createNewImageFile(String name, String toPath) {
        File file = new File(toPath);
        if (file.exists() && file.isDirectory()) {
            return new File(file.getPath() + File.separator + name);
        }
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        if (!toPath.endsWith(suffix)) {
            toPath += "." + suffix;
        }
        return new File(toPath);
    }

}
