package com.example.tictactoe_java;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MinMax_TTT {
    private GameManager gManager;
    private int myTurn, oppTurn;

    public MinMax_TTT(GameManager gm){
        gManager = gm;
        myTurn = gm.getTurn();
        if(myTurn == 1){
            oppTurn = 2;
        }else{
            oppTurn = 1;
        }
    }

    public Pair<Integer, Integer> findMove(ArrayList<ArrayList<TTTButton>> gameState){
        Node root = contructTree(gameState);
        minMax(root);
        return new Pair<>(root.getBestChoice().getX(), root.getBestChoice().getY());
    }

    private int minMax(Node n){
        if(n.isMax){
            if(n.getChildren().size() == 0){
                if(n.isWin){
                    n.setBestChoice(n);
                    n.setScore(-10);
                    return -10;
                }else{
                    n.setScore(0);
                    return 0;
                }
            }
            n.getChildren().forEach(c -> {
                int s = minMax(c);
                if(n.score <  s){
                    n.score = s;
                    n.setBestChoice(c);
                }
            });
        }else{
            if(n.getChildren().size() == 0){
                if(n.isWin){
                    n.setBestChoice(n);
                    n.setScore(10);
                    return 10;
                }else{
                    n.setScore(0);
                    return 0;
                }
            }
            n.getChildren().forEach(c -> {
                int s = minMax(c);
                if(n.score > s){
                    n.score = s;
                    n.setBestChoice(c);
                }
            });
        }
        return n.score;
    }

    private Node contructTree(ArrayList<ArrayList<TTTButton>> gameState){
        ArrayList<ArrayList<TTTButton>> newState = new ArrayList<>(copyState(gameState));
        Node root = new Node(true, newState);

        contructTree(root, 0);
        return root;
    }

    private void contructTree(Node parent, int i){
        List<Pair<Integer, Integer>> possibleMoves = getPossibleMoves(parent.gameState);

        possibleMoves.forEach(p -> {
            ArrayList<ArrayList<TTTButton>> newState = new ArrayList<>(copyState(parent.gameState));

            newState.get(p.getKey()).get(p.getValue())
                    .setClickedBy(parent.isMax ? myTurn : oppTurn);

            Node newNode = new Node(!parent.isMax, newState, p.getKey(), p.getValue());
            if(!gManager.checkWin(p.getKey(), p.getValue(), newState) && i < 6){
                contructTree(newNode, i + 1);
            }else{
                newNode.setWin(true);
            }
            parent.addChildren(newNode);
        });
    }

    private List<Pair<Integer, Integer>> getPossibleMoves(ArrayList<ArrayList<TTTButton>> gameState){
        List<Pair<Integer, Integer>> moves = new ArrayList<>();

        for(int i = 0; i < gameState.size(); i++){
            gameState.get(i).forEach(btn -> {
                if(btn.getClickedBy() < 0){
                    moves.add(new Pair<>(btn.getX(), btn.getY()));
                }
            });
        }

        return moves;
    }

    private ArrayList<ArrayList<TTTButton>> copyState(ArrayList<ArrayList<TTTButton>> state){
        ArrayList<ArrayList<TTTButton>> newState = new ArrayList<>();
        newState.add(new ArrayList<>());
        newState.add(new ArrayList<>());
        newState.add(new ArrayList<>());

        for(int i = 0; i < 9; i++){
            newState.get(i/3).add(new TTTButton(i/3, i%3));
            newState.get(i/3).get(i%3).setClickedBy(state.get(i/3).get(i%3).getClickedBy());
        }

        return newState;
    }
}
