package com.thoughtworks.cathywu.tools.testThread;

public class MainThread implements Runnable {

    private double d = 0.0;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("I am running!");

            for (int i = 0; i < 900000; i++) {
                d = d + (Math.PI + Math.E) / d;
            }
        }

        System.out.println("ATask.run() interrupted!");
    }

    public static void main(String[] args) {
        MainThread mt = new MainThread();
        Thread thread = new Thread(mt);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
