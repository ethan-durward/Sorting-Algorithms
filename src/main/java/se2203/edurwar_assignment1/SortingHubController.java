package se2203.edurwar_assignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Random;
//import java.util.*;


public class SortingHubController {
    @FXML
    private Label label;
    @FXML
    private Pane pane;
    @FXML
    private Slider slider;
    @FXML
    private ComboBox comboBox;



    private int[] intArray;
    private SortingStrategy sortingMethod;

    public void setSortStrategy(){
        if (comboBox.getValue().equals("Selection Sort")){
            sortingMethod = new SelectionSort(this,intArray);
        }
        else{
            sortingMethod = new MergeSort(this,intArray);
        }
    }

    //method to update the graph on the screen in order to show steps/progress of the sorting algorithms
    public void updateGraph(int[] data){
        pane.getChildren().clear();
        ArrayList<Rectangle> arrList = new ArrayList<>();

        for (int i = 0; i < 128; i++) {
            Rectangle rec = new Rectangle();
            arrList.add(rec);
            pane.getChildren().add(arrList.get(i));
        }
        double pWid = pane.getPrefWidth();
        double pHei = pane.getPrefHeight();
        double rWid = ((pWid / Math.floor(slider.getValue())) -2);


        double rHei, x, y;
        for (int i = 0; i < (int) slider.getValue(); i++) {
            rHei = (data[i] * pHei) / slider.getValue();
            x=i * (rWid+2);
            y=pHei - rHei;

            arrList.get(i).setHeight(rHei);
            arrList.get(i).setWidth(rWid);
            arrList.get(i).setY(y);
            arrList.get(i).setX(x+1);
            arrList.get(i).setFill(Color.color(1,0,0));
        }
    }


    //making a randomized, unsorted array
    public void randomArr(){
        int arraySizeNum = intArray.length;
        Random random = new Random();

        for (int i = 0; i < arraySizeNum; i++) {
            intArray[i] = i+1;
        }

        for (int i = 0; i < slider.getValue(); i++) {
            int index = random.nextInt((int) slider.getValue());
            int temp = intArray[index];
            intArray[index] = intArray[i];
            intArray[i] = temp;
        }
    }

    //this method is to initialize the applications parameters
    public void initialize(){
        comboBox.getItems().setAll("Merge Sort", "Selection Sort");
        comboBox.getSelectionModel().select(0);
        label.setText("64");
        slider.setValue(64);
        intArray = new int[(int) slider.getValue()];
        randomArr();
        updateGraph(intArray);
    }


    @FXML
    void resetArr(ActionEvent aEvent){
        initialize();
    }//calls the initialize method rather than manually resetting it


    @FXML
    void changeSize(MouseEvent mEvent){
        String num = String.format("%.0f", slider.getValue());
        label.setText(num);
        intArray = new int[(int) slider.getValue() + 1];
        randomArr();
        updateGraph(intArray);
    }


    @FXML
    void runSort(ActionEvent aEvent){
        setSortStrategy();
        new Thread(() -> {sortingMethod.sort(intArray);
        }).start();
    }
}