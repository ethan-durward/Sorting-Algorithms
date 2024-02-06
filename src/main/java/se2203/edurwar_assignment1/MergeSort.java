package se2203.edurwar_assignment1;
import javafx.application.Platform;

public class MergeSort implements SortingStrategy{
    //instantiating private fields list and controller
    private int[] list;
    private SortingHubController controller;

    public void sort(int[] numbers){
        mergeSort(numbers,0,numbers.length -1);
    }

    @Override
    public void run(){
        sort(list);
    }
    //above are the two private fields and two methods outlined in the UML diagram



    public MergeSort(SortingHubController contr , int[] intArr){
        this.controller = contr;
        list = intArr;
    }

    //recursive method that will divide the array into 2 and keep doing this until each sub array is sorted, then merge the sorted small arrays together
    public void mergeSort(int[] num, int l ,int r){
        if (l < r){
            int middle = l + (r - l) /2;
            mergeSort(num,l,middle);
            mergeSort(num, middle+1,r);
            merge(num,l,middle,r);
        }
    }

    //making the method that will merge the sorted arrays together
    public void merge(int[] num, int start, int mid, int end){
        int start2 = mid + 1;

        if(num[mid] <= num[start2]){
        }

        while (start <= mid && start2 <= end){
            if (num[start] <= num[start2]){
                start++;
            }
            else{
                int value = num[start2];
                int index = start2;
                while (index != start){
                    num[index] = num[index -1];
                    index --;
                }
                num[start] =value;
                start++;
                start2++;
                mid++;

                Platform.runLater(() -> controller.updateGraph(num));

                //try catch to handle errors that can occur during this step
                try{
                    Thread.sleep(50);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
