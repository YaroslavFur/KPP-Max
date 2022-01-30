package com.example.kpp_7.Logic;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThreadMonitor {
    private final long updateTime = 10;

    public StringProperty threadName = new SimpleStringProperty();
    public StringProperty threadState = new SimpleStringProperty();
    public StringProperty threadPriority = new SimpleStringProperty();
    public StringProperty threadIsOn = new SimpleStringProperty();

    private String tmpThreadName;
    private Thread.State tmpThreadState;
    private int tmpThreadPriority;
    private boolean tmpThreadIsOn;

    public void monitor(Thread thread) {

        tmpThreadState = thread.getState();

        while (!tmpThreadState.equals(Thread.State.TERMINATED)) {
            tmpThreadName = thread.getName();
            tmpThreadState = thread.getState();
            tmpThreadPriority = thread.getPriority();
            tmpThreadIsOn = thread.isAlive();

            report();

            sleep(updateTime);
        }
    }

    private void sleep (long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void report() {
        Platform.runLater(() -> {
            threadName.setValue(tmpThreadName);
            threadState.setValue(tmpThreadState.toString());
            threadPriority.setValue(tmpThreadPriority + "");
            threadIsOn.setValue(tmpThreadIsOn ? "True" : "False");
        });
    }
}
