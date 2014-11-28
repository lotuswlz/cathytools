package com.thoughtworks.cathywu.tools.forRepairGarbledChars;

import java.io.*;

public class FileTranslator {

    private static final byte[] TEST_SI_DATA = intArrayToBytes(
            new int[]{0x00, 0x00, 0x01, 0xc2, 0x40, 0x00, 0x00, 0x0c,
                    0x07, 0xc8, 0x94, 0x82,
                    0x00, 0x00, 0x01, 0xbc, 0x40, 0x00, 0x00, 0x14,
                    0xc3, 0x89, 0xc2, 0xb1, 0xc3, 0xa1, 0x2a, 0x60, 0x32, 0x00, 0x00, 0x00});

    private File file;

    public FileTranslator(File file) {
        this.file = file;
    }

    public String readContentFromFile() throws IOException {
        StringBuffer content = new StringBuffer();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
        String lineContent;
        while ((lineContent = bufferedReader.readLine()) != null) {
            content.append(lineContent).append("\n");
        }
        return content.toString();
    }

    public String replaceWrongWords(String content, String replacement) {
        String toReplaceRegex = "private static final String TEST_SI_VALUE = \"([^;]+);";
        content = content.replaceFirst(toReplaceRegex, "private static final String TEST_SI_VALUE = \"" + replacement + "\";");
        return content;
    }

    public static void main(String[] args) {
        FileTranslator translator = new FileTranslator(new File("/Users/lzwu/development/workspace/Diameter/src/test/java/com/telstra/common/diameter/avps/SubscriptionIdGroupTest.java"));
        try {
            String content = translator.readContentFromFile();
            String str = new String(TEST_SI_DATA, "WINDOWS-1252");
            content = translator.replaceWrongWords(content, str);

            OutputStream out = new FileOutputStream("/Users/lzwu/Downloads/SubscriptionIdGroupTest2.java");
            out.write(content.getBytes("WINDOWS-1252"));
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static byte[] intArrayToBytes(int[] array)
    {
        byte[] newArray = new byte[array.length];

        for(int i = 0; i < array.length; i++)
            newArray[i] = (byte)(array[i] & 0xff);

        return newArray;
    }
}
