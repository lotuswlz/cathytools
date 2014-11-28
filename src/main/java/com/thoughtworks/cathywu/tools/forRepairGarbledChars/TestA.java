package com.thoughtworks.cathywu.tools.forRepairGarbledChars;

import java.io.*;

/**
 * Created by lzwu on 11/27/14.
 */
public class TestA {
    private static final int[] baseBytes = new int[]{0x00, 0x00, 0x01, 0xc2, 0x40, 0x00, 0x00, 0x0c,
            0x07, 0xc8, 0x94, 0x82,
            0x00, 0x00, 0x01, 0xbc, 0x40, 0x00, 0x00, 0x14,
            0xc3, 0x89, 0xc2, 0xb1, 0xc3, 0xa1, 0x2a, 0x60, 0x32, 0x00, 0x00, 0x00};

    public void test() {

    }

    public static void main(String[] args) {
        byte bt;
        int num;
        for (int i = 0; i < baseBytes.length; i++) {
            bt = (byte) baseBytes[i];
            System.out.print(bt);
            System.out.println(",");
        }

        System.out.println("~~~~~~~~~~~~~~");
//        justTestCode();

        try {
            testAnother();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int bytesToUnsignedInt(byte[] bytes, int pos, int len)

    {
        int val = (int) bytesToUnsignedInteger(bytes, 4, "int", pos, len);

        if (val < 0)
            throw new RuntimeException("Unsigned value too large for signed java int: " + val);

        return val;
    }

    private static long bytesToUnsignedInteger(byte[] bytes, int precision,
                                               String type, int position, int length) {
        if (length > precision)
            throw new RuntimeException("Too many bytes for a " + type + ": " + length);
        if (position + length > bytes.length)
            throw new RuntimeException("Requested value goes past end of array");

        long val = 0;
        int pos = 0;

        // Start at least significant bit and work up from there
        for (int i = position + length - 1; i >= position; i--) {
            val |= ((long) (bytes[i] & 0xff)) << pos;
            pos += 8;
        }

        if (val < 0)
            throw new RuntimeException("Unsigned value too large for signed java long: " + val);

        return val;
    }

    public static byte[] intArrayToBytes(int[] array)
    {
        byte[] newArray = new byte[array.length];

        for(int i = 0; i < array.length; i++)
            newArray[i] = (byte)(array[i] & 0xff);

        return newArray;
    }

    public static void justTestCode() {
        for (int n = 127; n < 256; n++) {
            byte b = (byte) n;
            System.out.println(n + "=" + b);
        }
    }

    public static void testAnother() throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream dout = new DataOutputStream(bout);

        dout.writeUTF("á");
        dout.flush();

        byte[] dataOut = bout.toByteArray();
        for (int i = 0; i < dataOut.length; i++) {
            System.out.println(dataOut[i]);
        }
        ////////////////////////

        byte[] bb = new byte[]{0, 2, 97, 0};
        ByteArrayInputStream bin = new ByteArrayInputStream(bb);
        DataInputStream din = new DataInputStream(bin);

        System.out.println(din.readUTF());
    }
}
