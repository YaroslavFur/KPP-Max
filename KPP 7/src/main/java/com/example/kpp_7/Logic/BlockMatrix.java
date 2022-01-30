package com.example.kpp_7.Logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BlockMatrix {
    public double x1, y1, b1;
    public double x2, y2, b2;
    public double x, y;
    public StringProperty resultX = new SimpleStringProperty("0");
    public StringProperty resultY = new SimpleStringProperty("0");

    public BlockMatrix() {}

    public BlockMatrix(String x1, String y1, String b1, String x2, String y2, String b2) {
        this.x1 = Integer.parseInt(x1);
        this.x2 = Integer.parseInt(x2);
        this.y1 = Integer.parseInt(y1);
        this.y2 = Integer.parseInt(y2);
        this.b1 = Integer.parseInt(b1);
        this.b2 = Integer.parseInt(b2);
    }
}
