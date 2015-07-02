package com.thoughtworks.cathywu.tools.imagetools;

import java.awt.*;

/**
 * @author lzwu
 * @since 7/2/15
 */
public class ResizeByWidthHandler extends ResizeHandler {

    public ResizeByWidthHandler(int baseLength) {
        super(baseLength);
    }

    @Override
    protected Measurements calculate(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int destHeight = (int) (this.baseLength * ((float) height / (float) width));
        return new Measurements(baseLength, destHeight);
    }
}
