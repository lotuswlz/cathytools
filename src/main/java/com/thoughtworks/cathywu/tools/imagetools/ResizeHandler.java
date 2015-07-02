package com.thoughtworks.cathywu.tools.imagetools;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author lzwu
 * @since 7/2/15
 */
public abstract class ResizeHandler {

    protected int baseLength;

    public ResizeHandler(int baseLength) {
        this.baseLength = baseLength;
    }

    public BufferedImage process(Image image) {
        Measurements measurements = calculate(image);
        return resize(image, measurements);
    }

    protected abstract Measurements calculate(Image image);

    private BufferedImage resize(Image image, Measurements measurements) {
        BufferedImage bufferedImage = new BufferedImage(measurements.getWidth(), measurements.getHeight(), BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(image, 0, 0, measurements.getWidth(), measurements.getHeight(), null);
        return bufferedImage;
    }
}
