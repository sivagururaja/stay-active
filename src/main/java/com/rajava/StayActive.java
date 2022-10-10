package com.rajava;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Stay active - a small utility to make your system active and prevent auto locking after X amount of ideal time
 * <p>
 * Just to make it little more realistic, we can use different key codes in random interval with in 5 minutes
 */
public class StayActive implements Runnable {

    private Robot robot;
    private Random random;
    public static final int[] KEY_CODES = {KeyEvent.VK_ESCAPE, KeyEvent.VK_CAPS_LOCK};

    public StayActive() throws AWTException {
        robot = new Robot();
        random = new Random();
    }

    @Override
    public void run() {
        while (Boolean.TRUE) {
            try {
                int nextInt = random.nextInt(5);
                int keyCode = KEY_CODES[nextInt % 2];

                System.out.println("Next key event (" + (keyCode == KeyEvent.VK_CAPS_LOCK ? "CAPS LOCK" : "ESCAPE") + ") will happen in " + nextInt + " minutes");
                Thread.sleep(nextInt * 1000 * 60);

                sendKeyEvents(keyCode);
            } catch (InterruptedException ignore) {
                // Ignore
            }
        }
    }

    /**
     * To send key events
     *
     * @param keyCode key code
     */
    private void sendKeyEvents(int keyCode) {
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);

        // If it's a CAPS LOCK key code, then we need to send the same key code again to make it OFF
        if (keyCode == KeyEvent.VK_CAPS_LOCK) {
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }
    }

    public static void main(String[] args) throws AWTException {
        Thread stayActive = new Thread(new StayActive());
        stayActive.start();
    }
}
