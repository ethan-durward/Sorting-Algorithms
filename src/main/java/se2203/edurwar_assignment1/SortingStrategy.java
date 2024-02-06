package se2203.edurwar_assignment1;

public interface SortingStrategy extends Runnable{
    public void sort(int[] numbers);
}

interface Runnable{
    public void run();
}
