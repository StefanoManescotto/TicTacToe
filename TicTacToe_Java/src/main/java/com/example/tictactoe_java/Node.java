package com.example.tictactoe_java;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int score, x, y;
    boolean isMax, isWin = false;
    List<Node> children = new ArrayList<>();
    Node bestChoice;
    ArrayList<ArrayList<TTTButton>> gameState;

    public Node(boolean isMax){
        this.isMax = isMax;
        setInitialScore();
    }

    public Node(boolean isMax, ArrayList<ArrayList<TTTButton>> gameState){
        this.isMax = isMax;
        this.gameState = gameState;
        setInitialScore();
    }

    public Node(boolean isMax, ArrayList<ArrayList<TTTButton>> gameState, int x, int y){
        this.isMax = isMax;
        this.gameState = gameState;
        this.x = x;
        this.y = y;
        setInitialScore();
    }

    private void setInitialScore(){
        if(isMax){
            score = -10000;
        }else{
            score = 10000;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMax() {
        return isMax;
    }

    public void setMax(boolean max) {
        isMax = max;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChildren(Node children) {
        this.children.add(children);
    }

    public ArrayList<ArrayList<TTTButton>> getGameState() {
        return gameState;
    }

    public void setGameState(ArrayList<ArrayList<TTTButton>> gameState) {
        this.gameState = gameState;
    }

    public Node getBestChoice() {
        return bestChoice;
    }

    public void setBestChoice(Node bestChoice) {
        this.bestChoice = bestChoice;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }
}
