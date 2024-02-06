package se2203.edurwar_assignment1;

import javafx.application.Platform;
public class SelectionSort implements SortingStrategy {
    private int[] list;
    private SortingHubController controller;

    public void sort(int[] numbers){
        int numLen = numbers.length;

        for(int i = 0; i < numLen -1; i++) {
            int nextSmall = i;
            for (int j = i+1; j < numLen; j++) {
                if(numbers[j] < numbers[nextSmall])
                    nextSmall = j;
            }
            int temp = numbers[nextSmall];
            numbers[nextSmall] = numbers[i];
            numbers[i] = temp;

            Platform.runLater(() -> controller.updateGraph(numbers));

            try{
                Thread.sleep(50);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void run(){
        sort(list);
    }
    //above are the two private fields and two methods outlined in the UML diagram


    public SelectionSort(SortingHubController contr, int[] intArr){
        this.controller =contr;
        list =intArr;
    }
}
