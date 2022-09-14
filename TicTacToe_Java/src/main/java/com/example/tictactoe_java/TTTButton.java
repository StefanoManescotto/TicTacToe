package com.example.tictactoe_java;

import javafx.scene.control.Button;

public class TTTButton extends Button {
    private int x, y, clickedBy = -1;

    public TTTButton(int x, int y){
        this.x = x;
        this.y = y;
    }

    public TTTButton(String txt, int x, int y){
        super(txt);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getClickedBy() {
        return clickedBy;
    }

    public void setClickedBy(int clickedBy) {
        if(clickedBy <= 0 || clickedBy >= 3){
            return;
        }
        this.clickedBy = clickedBy;
    }
}
