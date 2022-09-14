package com.example.tictactoe_java;

import java.util.ArrayList;

public class GameManager {
    private int turn = 1; //1 -> player 1 | 2 -> player 2

    /**
     * Method invoked when a game button has been pressed and set the right text based on the turn
     * @param btn button pressed
     * @return the turn in which the button has been pressed, if already pressed -1
     */
    public int buttonPress(TTTButton btn, ArrayList<ArrayList<TTTButton>> btnMatrix){
        if(!btn.getText().isEmpty()){
            return -1;
        }

        btn.setClickedBy(turn);
        if(turn == 1){
            btn.setText("X " + btn.getX() + " " + btn.getY() + " " + btn.getClickedBy());
            checkWin(btn.getX(), btn.getY(), btnMatrix);

            turn = 2;
            return 1;
        }else{
            btn.setText("O " + btn.getX() + " " + btn.getY() + " " + btn.getClickedBy());
            checkWin(btn.getX(), btn.getY(), btnMatrix);

            turn = 1;
            return 2;
        }
    }

    private int checkWin(int x, int y, ArrayList<ArrayList<TTTButton>> mtx){
        for(int i = 0; i < mtx.size(); i++){
            if(mtx.get(i).get(x).getClickedBy() != mtx.get(x).get(y).getClickedBy()){
                break;
            }
            mtx.get(i).get(x).setStyle("-fx-background-color: #ff0000;");
        }

        return 0;
    }

    /**
     * @return current turn
     */
    public int getTurn(){
        return turn;
    }
}
