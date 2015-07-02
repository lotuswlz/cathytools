package com.thoughtworks.cathywu.tools.imagetools;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;

/**
 * @author lzwu
 * @since 7/2/15
 */
public class ImageUtils {

    private static final Logger LOGGER = Logger.getLogger(ImageUtils.class);

    private int baseWidth;
    private int baseHeight;
    private BigDecimal baseRate;

    private ImageUtils(int baseWidth, int baseHeight) throws IOException {
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
        this.baseRate = new BigDecimal(this.baseWidth).divide(new BigDecimal(this.baseHeight)).setScale(2, BigDecimal.ROUND_HALF_UP);
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

    public void resizeImages(String imageFolder, String toFolder) {
        File directory = new File(imageFolder);
        File toDirectory = new File(toFolder);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new RuntimeException("No such a directory: " + imageFolder);
        }
        if (!toDirectory.exists() || !toDirectory.isDirectory()) {
            throw new RuntimeException("No such a directory: " + toDirectory);
        }
        File[] files = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String suffix = name.substring(name.lastIndexOf(".") + 1);
                return suffix.matches("^(?i)(jpg|jpeg|png|gif)$");
            }
        });
        for (File imageFile : files) {
            boolean isSuccess = resizeImage(imageFile, toFolder);
            if (!isSuccess) {
                System.out.println("Failure: " + imageFile.getPath());
            }
        }
        System.out.println("Complete");
    }

    private ResizeHandler chooseCorrectHandler(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BigDecimal currentRate = new BigDecimal(width).divide(new BigDecimal(height)).setScale(2, BigDecimal.ROUND_HALF_UP);

        if (currentRate.compareTo(baseRate) > 0) { // turn thin, use handlerByWidth and use base width
            return new ResizeByWidthHandler(this.baseWidth);
        } else {
            return new ResizeByHeightHandler(this.baseHeight);
        }
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
