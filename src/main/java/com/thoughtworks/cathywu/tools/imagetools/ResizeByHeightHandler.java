package com.thoughtworks.cathywu.tools.imagetools;

import java.awt.*;

/**
 * @author lzwu
 * @since 7/2/15
 */
public class ResizeByHeightHandler extends ResizeHandler {

    public ResizeByHeightHandler(int baseLength) {
        super(baseLength);
    }

    @Override
    protected Measurements calculate(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int destWidth = (int) (this.baseLength * ((float) width / (float) height));
        return new Measurements(destWidth, this.baseLength);
    }
}
