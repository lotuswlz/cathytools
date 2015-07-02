package com.thoughtworks.cathywu.tools.imagetools;

import java.io.IOException;

/**
 * @author lzwu
 * @since 7/2/15
 */
public class MainExecutor {
    public static void main(String[] args) {
        try {
            ImageUtils imageUtils = ImageUtils.createInstanceFor(400, 400);
            imageUtils.resizeImages("/Users/lzwu/Downloads/test_image_transform/test1", "/Users/lzwu/Downloads/test_image_transform/output");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
