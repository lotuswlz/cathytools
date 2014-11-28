package com.thoughtworks.cathywu.tools.forRepairGarbledChars;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by lzwu on 11/26/14.
 */
public class Main {

    private static final byte[] TEST_SI_DATA = intArrayToBytes(
            new int[]{0x00, 0x00, 0x01, 0xc2, 0x40, 0x00, 0x00, 0x0c,
                    0x07, 0xc8, 0x94, 0x82,
                    0x00, 0x00, 0x01, 0xbc, 0x40, 0x00, 0x00, 0x14,
                    0xc3, 0x89, 0xc2, 0xb1, 0xc3, 0xa1, 0x2a, 0x60, 0x32, 0x00, 0x00, 0x00});

    public static void main(String[] args) {
//        try {
//            File in =  new File("/Users/lzwu/development/workspace/Diameter/src/test/java/com/telstra/common/diameter/avps/SubscriptionIdGroupTest.java");
//            InputStreamReader r = new InputStreamReader(new FileInputStream(in));
//            System.out.println(r.getEncoding());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
/*        File testFile = new File("/Users/lzwu/Downloads/testEncoding.File");
        try {
            OutputStream os = new FileOutputStream(testFile);
            os.write(TEST_SI_DATA);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(getCharsetOfFile(testFile));*/

        for (int i = 0; i < TEST_SI_DATA.length; i++) {
            System.out.println(Long.parseLong(String.valueOf(TEST_SI_DATA[i]), 16));
        }

        //printWordsInFile();
    }

    public static byte[] intArrayToBytes(int[] array)
    {
        byte[] newArray = new byte[array.length];

        for(int i = 0; i < array.length; i++)
            newArray[i] = (byte)(array[i] & 0xff);

        return newArray;
    }

    public static String getCharsetOfFile(File file) {
        try {
            InputStreamReader r = new InputStreamReader(new FileInputStream(file));
            return r.getEncoding();
        } catch (FileNotFoundException e) {
            return e.getMessage();
        }
    }

    public static void printWordsInFile() {
        try {
            String str = new String(TEST_SI_DATA, "WINDOWS-1252");
            byte[] bytes = Charset.forName("UTF-8").encode(str).array();
            for (byte bt : bytes) {
                System.out.print(bt);
                System.out.print(",");
            }
            System.out.println();
            System.out.println(new String(bytes, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
