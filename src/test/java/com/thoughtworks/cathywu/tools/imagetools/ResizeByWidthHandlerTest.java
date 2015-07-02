package com.thoughtworks.cathywu.tools.imagetools;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author lzwu
 * @since 7/2/15
 */
@RunWith(Parameterized.class)
public class ResizeByWidthHandlerTest {

    private Image widthImage;
    private Image heightImage;

    @Before
    public void setUp() throws Exception {
        widthImage = mock(Image.class);
        heightImage = mock(Image.class);
        when(widthImage.getHeight(null)).thenReturn(300);
        when(widthImage.getWidth(null)).thenReturn(500);
        when(heightImage.getHeight(null)).thenReturn(500);
        when(heightImage.getWidth(null)).thenReturn(300);
    }

    @Test
    public void should_calculate_height_by_width() throws Exception {
        final int width = 200;
        ResizeHandler handler = new ResizeByWidthHandler(width);

    }
}
