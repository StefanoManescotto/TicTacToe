package com.example.tictactoe_java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class Controller {

    @FXML
    private GridPane grid;

    private ArrayList<ArrayList<Button>> btnMatrix = new ArrayList<>();

    @FXML
    protected void onHelloButtonClick() {
        grid.setMaxHeight(400);
        grid.setMaxWidth(400);

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(33);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(33);

        for(int i = 0; i < 3; i++){
            grid.getColumnConstraints().addAll(column);
            grid.getRowConstraints().addAll(row);
        }

        for(int i = 0; i < 9; i++){
            final Button newButton = new Button();
            final int j = i;
            newButton.setOnAction(e -> actionHandler(newButton, j));
            newButton.setMaxHeight(Double.MAX_VALUE);
            newButton.setMaxWidth(Double.MAX_VALUE);

            if(btnMatrix.size() <= i/3){
                btnMatrix.add(new ArrayList<>());
            }
            btnMatrix.get(i/3).add(newButton);

            grid.add(btnMatrix.get(i/3).get(i%3), i/3, i%3);
        }
    }

    private void actionHandler(Button btn, int i){
        btn.setText("Clicked " + i);
    }
}
