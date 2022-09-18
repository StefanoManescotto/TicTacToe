package com.example.tictactoe_java;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    @FXML
    private GridPane grid;
    @FXML
    private TextField winTxt;
    private boolean againstAI = false;

    private ArrayList<ArrayList<TTTButton>> btnMatrix = new ArrayList<>();
    private GameManager gManager;
    private int playerTurn = 1; //1 if player makes first move | 2 if player makes second move | 0 if both are player

    @FXML
    public void initialize() {
        winTxt.setStyle("-fx-background-color: -fx-control-inner-background;");
        createGame();
        gManager = new GameManager();
    }

    @FXML
    protected void PvAI1ButtonClick() {
        playerTurn = 2;
        resetGame();
        Random r = new Random();
        int firstMove = r.nextInt(9);
        actionHandler(btnMatrix.get(firstMove/3).get(firstMove%3));
        againstAI = true;
    }

    @FXML
    protected void PvAI2ButtonClick() {
        playerTurn = 1;
        resetGame();
        againstAI = true;
    }

    @FXML
    protected void duoButtonClick() {
        playerTurn = 0;
        resetGame();
        againstAI = false;
    }

    @FXML
    protected void onResetButtonClick() {
        resetGame();
    }

    private void createGame(){
        grid.prefHeightProperty().bind(grid.prefWidthProperty()); //Used for have better proportions when resizing the window

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(33);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(33);

        for(int i = 0; i < 3; i++){
            grid.getColumnConstraints().addAll(column);
            grid.getRowConstraints().addAll(row);
        }

        for(int i = 0; i < 9; i++){
            if(btnMatrix.size() <= i/3){
                btnMatrix.add(new ArrayList<>());
            }
            btnMatrix.get(i/3).add(createNewButton(i/3, i%3));

            grid.add(btnMatrix.get(i/3).get(i%3), i/3, i%3);
        }
    }


    private TTTButton createNewButton(int x, int y){
        final TTTButton newBtn = new TTTButton(x, y);
        //final int j = x;
        newBtn.setOnAction(e -> actionHandler(newBtn));
        newBtn.setMaxHeight(Double.MAX_VALUE);
        newBtn.setMaxWidth(Double.MAX_VALUE);

        newBtn.setStyle("-fx-font-size: 50");
        //newButton.fontProperty().bind(newButton.);
        //newButton.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.45 * 70)));
        return newBtn;
    }

    private void actionHandler(TTTButton btn){
        int r = gManager.buttonPress(btn, btnMatrix);
        gManager.incrementBtnPressed();

        if(r > 2){
            winTxt.setText("Player " + r/2 + " won");
            endGame();
        }else if(againstAI && gManager.getTurn() != playerTurn && gManager.getnBtnPressed() < 9){
            AIMove();
        }
    }

    private void endGame(){
        for(int i = 0; i < 9; i++){
            btnMatrix.get(i/3).get(i%3).setDisable(true);
        }
    }

    private void resetGame(){
        winTxt.setText("");
        gManager.setTurn(1);
        gManager.resetBtnPressed();
        for(int i = 0; i < 9; i++){
            btnMatrix.get(i/3).get(i%3).setDisable(false);
            btnMatrix.get(i/3).get(i%3).setClickedBy(-1);
            btnMatrix.get(i/3).get(i%3).setText("");
        }
    }

    private void AIMove(){
        MinMax_TTT m = new MinMax_TTT(gManager);
        Pair<Integer, Integer> move = m.findMove(btnMatrix);
        actionHandler(btnMatrix.get(move.getKey()).get(move.getValue()));
    }
}
