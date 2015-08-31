package com.thoughtworks.cathywu.tools.imagetools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * This is an example for ImageUtils.
 * @author lzwu
 * @since 7/2/15
 */
public class MainExecutor {
    public static void main(String[] args) {
        try {
            ImageUtils imageUtils = ImageUtils.createInstanceFor(200, 200);
            String originImagePath = "/Users/lzwu/development/static/3week_practise/twitters_new_face/images/me.jpg";
            String toPathPrefix = originImagePath.substring(0, originImagePath.lastIndexOf("."));

            Map<String, Integer> imageTypes = getBufferedImageTypes();
            for (String typeName : imageTypes.keySet()) {
                imageUtils.resizeImage(new File(originImagePath), toPathPrefix + "_" + typeName + ".jpg", imageTypes.get(typeName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> getBufferedImageTypes() throws IllegalAccessException {
        Class cls = BufferedImage.class;
        Field[] fields = cls.getDeclaredFields();
        Map<String, Integer> types = new HashMap<String, Integer>();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (field.getType().getName().equals("int") && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers)) {
                if (field.getInt(null) != 0) {
                    types.put(field.getName(), field.getInt(null));
                }
                System.out.println(field.getName() + "," + field.getInt(null));
            }
        }
        return types;
    }
}
