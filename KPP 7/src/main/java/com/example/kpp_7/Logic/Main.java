package com.example.kpp_7.Logic;

import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public int numOfThreads = 1;
    public Stack<BlockMatrix> blocks = new Stack<>();

    public void run(GaussMethod gaussMethod, ThreadMonitor threadMonitor) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[numOfThreads];

        if (!blocks.empty()) {
            for (int i = 0; i < threads.length && i < 3; i++) {
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        gaussMethod.solve(blocks, startTime);
                    }
                };
                threads[i] = new Thread(task);
                threads[i].setDaemon(true);
            }

            Runnable task = new Runnable() {
                @Override
                public void run() {
                    threadMonitor.monitor(threads[0]);
                }
            };
            Thread monitoring = new Thread(task);
            monitoring.setDaemon(true);
            monitoring.start();

            for (int i = 0; i < threads.length && i < 3; i++) {
                threads[i].start();
            }
        }
    }

    private void sleep (long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean setNumOfThreads (StringProperty numOfThreads) {
        try {
            this.numOfThreads = Integer.parseInt(numOfThreads.getValue());
            if (this.numOfThreads < 1)
                throw new NumberFormatException("Value must be positive");
        } catch (NumberFormatException exception) {
            System.out.println("Input threads number is wrong: " + exception.getMessage());
            return false;
        }
        return true;
    }
}
