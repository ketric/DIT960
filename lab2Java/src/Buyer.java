/**
 * Created by BenJ on 2017-05-05.
 */
public class Buyer extends BinaryHeap{
    private String[] BuyerNames;
    public Buyer(int initCapacity) {
        super(initCapacity);
        BuyerNames = new String[initCapacity];
    }

    @Override
    public void add(int elem) {
        super.add(elem);

    }


    @Override
    public void heapifyDown(int initIndex) {
        int index = initIndex;
        while (hasLeftChild(index)) {
            int biggerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChildData(index) > getLeftChildData(index)) {
                biggerChildIndex = getRightChildIndex(index);
            }

            if (arr[index] > arr[biggerChildIndex]){
                break;
            }else {
                swap(index, biggerChildIndex);
            }
            index = biggerChildIndex;
        }
    }

    @Override
    public void heapifyUp(int initIdex) {
        int index = initIdex;
        while (hasParent(index) && getParentData(index) < arr[index]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }


    }

    @Override
    public void changeElemValue(int index, int newValue) {
        int currentValue = arr[index];
        if (newValue > currentValue) {
            heapifyUp(index);
        }else{
            heapifyDown(index);
        }
    }
}
