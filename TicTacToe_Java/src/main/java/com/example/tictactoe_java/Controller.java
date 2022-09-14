package com.example.tictactoe_java;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class Controller {

    @FXML
    private GridPane grid;

    private ArrayList<ArrayList<TTTButton>> btnMatrix = new ArrayList<>();
    private GameManager gManager;

    @FXML
    public void initialize() {
        gManager = new GameManager();
    }

    @FXML
    protected void onHelloButtonClick() {
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
            btnMatrix.get(i/3).add(createNewButton(i%3, i/3));

            grid.add(btnMatrix.get(i/3).get(i%3), i/3, i%3);
        }
    }


    private TTTButton createNewButton(int x, int y){
        final TTTButton newBtn = new TTTButton(x, y);
        final int j = x;
        newBtn.setOnAction(e -> actionHandler(newBtn, j));
        newBtn.setMaxHeight(Double.MAX_VALUE);
        newBtn.setMaxWidth(Double.MAX_VALUE);

        //newButton.fontProperty().bind(newButton.);
        //newButton.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.45 * 70)));
        return newBtn;
    }

    private void actionHandler(TTTButton btn, int i){
        gManager.buttonPress(btn, btnMatrix);
    }
}
