package com.example.tictactoe_java;

import java.util.ArrayList;

public class GameManager {
    private int turn = 1; //1 -> player 1 | 2 -> player 2

    /**
     * Method invoked when a game button has been pressed and set the right text based on the turn
     * @param btn button pressed
     * @return the turn in which the bu tton has been pressed, if already pressed -1
     */
    public int buttonPress(TTTButton btn, ArrayList<ArrayList<TTTButton>> btnMatrix){
        if(!btn.getText().isEmpty()){
            return -1;
        }

        btn.setClickedBy(turn);
        if(turn == 1){
            btn.setText("X");
            turn = 2;

            return checkWin(btn.getX(), btn.getY(), btnMatrix) ? 3 : 1;
        }else{
            btn.setText("O");

            turn = 1;
            return checkWin(btn.getX(), btn.getY(), btnMatrix) ? 4 : 2;
        }
    }

    private boolean checkWin(int x, int y, ArrayList<ArrayList<TTTButton>> mtx){
        return checkWinH(x, y, mtx) || checkWinV(x, y, mtx) || checkWinDiagonal1(x, y, mtx) || checkWinDiagonal2(x, y, mtx);
    }

    private boolean checkWinH(int x, int y, ArrayList<ArrayList<TTTButton>> m){
        for(int i = 0; i < m.size(); i++){
            if(m.get(i).get(y).getClickedBy() != m.get(x).get(y).getClickedBy()){
                return false;
            }
        }
        return true;
    }

    private boolean checkWinV(int x, int y, ArrayList<ArrayList<TTTButton>> m){
        for(int i = 0; i < m.size(); i++){
            if(m.get(x).get(i).getClickedBy() != m.get(x).get(y).getClickedBy()){
                return false;
            }
        }
        return true;
    }

    private boolean checkWinDiagonal1(int x, int y, ArrayList<ArrayList<TTTButton>> m){
        for(int i = 0; i < m.size(); i++){
            if(m.get(i).get(i).getClickedBy() < 0 ||
                    m.get(i).get(i).getClickedBy() != m.get(x).get(y).getClickedBy()){
                return false;
            }
        }
        return true;
    }

    private boolean checkWinDiagonal2(int x, int y, ArrayList<ArrayList<TTTButton>> m){
        for(int i = 0; i < m.size(); i++){
            if(m.get(m.size() - 1 - i).get(i).getClickedBy() < 0 ||
                    m.get(m.size() - 1 - i).get(i).getClickedBy() != m.get(x).get(y).getClickedBy()){
                return false;
            }
        }
        return true;
    }

    /**
     * @return current turn
     */
    public int getTurn(){
        return turn;
    }

    public void setTurn(int turn) {
        if(turn <= 0 && turn >= 3){
            return;
        }
        this.turn = turn;
    }
}
