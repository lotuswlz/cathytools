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

    public BufferedImage process(Image image, int type) {
        Measurements measurements = calculate(image);
        return resize(image, measurements, type);
    }

    private BufferedImage resize(Image image, Measurements measurements, int type) {
        BufferedImage bufferedImage = new BufferedImage(measurements.getWidth(), measurements.getHeight(), type);
        bufferedImage.getGraphics().drawImage(image, 0, 0, measurements.getWidth(), measurements.getHeight(), null);
        return bufferedImage;
    }

    protected abstract Measurements calculate(Image image);
}
