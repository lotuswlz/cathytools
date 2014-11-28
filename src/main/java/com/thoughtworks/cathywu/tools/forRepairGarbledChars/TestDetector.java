package com.thoughtworks.cathywu.tools.forRepairGarbledChars;

import org.mozilla.universalchardet.UniversalDetector;

public class TestDetector {
    public static void main(String[] args) throws java.io.IOException {
        byte[] buf = new byte[4096];
//        String fileName = "/Users/lzwu/Downloads/testEncoding.File";
        String fileName = "/Users/lzwu/development/workspace/Diameter/src/test/java/com/telstra/common/diameter/avps/SubscriptionIdGroupTest.java";
        java.io.FileInputStream fis = new java.io.FileInputStream(fileName);

        // (1)
        UniversalDetector detector = new UniversalDetector(null);

        // (2)
        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        // (3)
        detector.dataEnd();

        // (4)
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.println("Detected encoding = " + encoding);
        } else {
            System.out.println("No encoding detected.");
        }

        // (5)
        detector.reset();
    }
}