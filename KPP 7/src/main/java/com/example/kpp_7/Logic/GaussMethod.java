package com.example.kpp_7.Logic;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Stack;

public class GaussMethod {

    public StringProperty timeTaken = new SimpleStringProperty();

    public void solve(Stack<BlockMatrix> blocks, long startTime) {
        BlockMatrix block;

        while (!blocks.empty()) {
            synchronized (blocks) {
                block = blocks.pop();
            }
            if (!forward(block)) {
                block.resultX.setValue("Zero Error");
                block.resultY.setValue("Zero Error");
            }
            if (!backward(block)) {
                block.resultX.setValue("Zero Error");
                block.resultY.setValue("Zero Error");
            }
            BlockMatrix finalBlock = block;
            Platform.runLater(() -> {
                finalBlock.resultX.setValue(String.valueOf(Math.round(finalBlock.x)));
                finalBlock.resultY.setValue(String.valueOf(Math.round(finalBlock.y)));
                timeTaken.setValue(String.valueOf(System.currentTimeMillis() - startTime));
            });
        }
    }

    private boolean forward(BlockMatrix block) {
        double multiplier = 0;
        try {
            multiplier = -(block.x2 / block.x1);
        } catch (Exception exception) {
            System.out.println("Error in Gauss method #1: " + exception.getMessage());
            return false;
        }

        block.x2 += block.x1 * multiplier;
        block.y2 += block.y1 * multiplier;
        block.b2 += block.b1 * multiplier;

        return true;
    }

    private boolean backward(BlockMatrix block) {
        try {
            if (block.x2 == 0)
                block.y = block.b2 / block.y2;
            else {
                throw new Exception();
            }
        } catch (Exception exception) {
            System.out.println("Error in Gauss method #2: " + exception.getMessage());
            return false;
        }

        block.x = (block.b1 - block.y1 * block.y) / block.x1;
        return true;
    }
}
