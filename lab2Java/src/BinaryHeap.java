import java.util.Arrays;

/**
 * Created by BenJ on 2017-05-05.
 */
public abstract class BinaryHeap {
    protected int capacity;
    protected int size = 0;

    int[] arr = new int[capacity];
    int[] owner = new int [capacity];

    // Constructor
    public BinaryHeap(int initCapacity){
        capacity = initCapacity;
    }

    // Getters
    protected int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    protected int getRightChildIndex(int parentIndex) {
        return getLeftChildIndex(parentIndex) + 1;
    }

    protected int getParentIndex (int childIndex) {
        return (childIndex - 1) / 2;
    }

    protected int getLeftChildData(int parentIndex){
        return arr[getLeftChildIndex(parentIndex)];
    }

    protected int getRightChildData(int parentIndex){
        return arr[getRightChildIndex(parentIndex)];
    }

    protected int getParentData(int childIndex) {
        return arr[getLeftChildIndex(childIndex)];
    }

    //  Validators
    protected boolean hasLeftChild(int parentIndex) {
        return getLeftChildIndex(parentIndex) < size;
    }

    protected boolean hasRightChild(int parentIndex){
        return getRightChildIndex(parentIndex) < size;
    }

    protected boolean hasParent(int childIndex) {
        return childIndex > 0;
    }

    // Swap
    protected void swap(int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    private void extendArrIfFull() {
        if (size == capacity) {
            arr = Arrays.copyOf(arr, capacity*2);
            capacity *= 2;
        }
    }

    // Binary heap methods
    public int peek() {
        if (size < 1) throw new IllegalStateException("The array is empty");
        return arr[0];
    }

    public int poll() {
        if (size < 1) throw new IllegalStateException("The array is empty");
        int output = arr[0];
        arr[0] = arr[size - 1];
        size --;
        heapifyDown(0);
        return output;
    }

    public void add(int elem) {
        extendArrIfFull();
        arr[size] = elem;
        size++;
        heapifyUp(size - 1);
    }

    public abstract  void changeElemValue(int index, int newValue);

    public abstract void heapifyDown(int index);

    public abstract  void heapifyUp(int index);

}
