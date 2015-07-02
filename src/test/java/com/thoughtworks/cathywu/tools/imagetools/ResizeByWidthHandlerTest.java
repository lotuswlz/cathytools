package com.thoughtworks.cathywu.tools.imagetools;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author lzwu
 * @since 7/2/15
 */
@RunWith(Parameterized.class)
public class ResizeByWidthHandlerTest {

    private static final int BASE_WIDTH = 200;
    private Image image;
    private int transformedWidth;
    private int transformedHeight;

    private ResizeHandler handler;

    @Parameterized.Parameters
    public static Collection prepareData() {
        Object[][] data = new Object[][] {
                {createImage(500, 250), 200, 100},
                {createImage(400, 800), 200, 400},
                {createImage(100, 50), 200, 100}
        };
        return Arrays.asList(data);
    }

    public ResizeByWidthHandlerTest(Image image, int transformedWidth, int transformedHeight) {
        this.image = image;
        this.transformedWidth = transformedWidth;
        this.transformedHeight = transformedHeight;
    }

    @Before
    public void setUp() throws Exception {
        handler = new ResizeByWidthHandler(BASE_WIDTH);
    }

    @Test
    public void should_calculate_height_by_width() throws Exception {
        Measurements measurements = handler.calculate(image);
        assertEquals(this.transformedWidth, measurements.getWidth());
        assertEquals(this.transformedHeight, measurements.getHeight());
    }

    private static Image createImage(int width, int height) {
        Image image = mock(Image.class);
        when(image.getWidth(null)).thenReturn(width);
        when(image.getHeight(null)).thenReturn(height);
        return image;
    }
}
