package com.thoughtworks.cathywu.tools.imagetools;

import java.awt.*;

/**
 * @author lzwu
 * @since 7/2/15
 */
public class ResizeByHeightHandler implements ResizeHandler {

    @Override
    public Measurements calculate(Image image, int destWidth) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int destHeight = (int) (destWidth * ((float) height / (float) width));
        return new Measurements(destWidth, destHeight);
    }
}
