package com.example.tictactoe_java;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class Controller {

    @FXML
    private GridPane grid;
    @FXML
    private TextField winTxt;

    private ArrayList<ArrayList<TTTButton>> btnMatrix = new ArrayList<>();
    private GameManager gManager;

    @FXML
    public void initialize() {
        winTxt.setStyle("-fx-background-color: -fx-control-inner-background;");
        gManager = new GameManager();
        createGame();
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
        if(r > 2){
            winTxt.setText("Player " + r/2 + " won");
            endGame();
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
        for(int i = 0; i < 9; i++){
            btnMatrix.get(i/3).get(i%3).setDisable(false);
            btnMatrix.get(i/3).get(i%3).setClickedBy(-1);
            btnMatrix.get(i/3).get(i%3).setText("");
        }
    }
}
