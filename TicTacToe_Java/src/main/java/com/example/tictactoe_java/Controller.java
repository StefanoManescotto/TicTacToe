package com.example.tictactoe_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class Controller {

    @FXML
    private GridPane grid;

    private ArrayList<ArrayList<Button>> btnMatrix = new ArrayList<>();

    @FXML
    protected void onHelloButtonClick() {
        //double sizeX = grid.getScaleX()/3, sizeY = grid.getScaleY()/3;
        grid.setPrefHeight(300);
        grid.setPrefWidth(300);

        //grid.setScaleX(1);
        //grid.setScaleY(1);

        for(int i = 0; i < 9; i++){
            final Button newButton = new Button();
            //newButton.setShape(new Polygon(1,1,1,1));
           //newButton.setStyle("-fx-base: purple;");
            //newButton.setScaleY(1);
            //newButton.setPrefWidth(100);
            //newButton.setPrefHeight(100);
           // newButton.setMaxWidth(Double.MAX_VALUE);
           // newButton.setMaxHeight(Double.MAX_VALUE);


            RowConstraints rowConstraint = new RowConstraints();
            rowConstraint.setPercentHeight(300);

            ColumnConstraints colConstraint = new ColumnConstraints();
            colConstraint.setPercentWidth(300);

                grid.getColumnConstraints().add(colConstraint);
                grid.getRowConstraints().add(rowConstraint);


            newButton.setOnAction(e -> actionHandler(e, newButton));

            if(btnMatrix.size() <= i/3){
                btnMatrix.add(new ArrayList<>());
            }
            btnMatrix.get(i/3).add(newButton);

            grid.add(btnMatrix.get(i/3).get(i%3), i/3, i%3);
        }
    }

    private void actionHandler(ActionEvent e, Button btn){
        btn.setText("Clicked");
    }
}