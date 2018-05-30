/**
 * Created by BenJ on 2017-05-05.
 */
public class Seller extends BinaryHeap{
    private String[] SellerNames;
    public Seller(int initCapacity) {
        super(initCapacity);
        SellerNames = new String[];
    }

    @Override
    public void heapifyDown(int initindex) {
        int index = initindex;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChildData(index) < getLeftChildData(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (arr[index] < arr[smallerChildIndex]){
                break;
            }else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    } // heapifyDown root

    @Override
    public void heapifyUp(int initIndex) {
        int index = initIndex;
        while (hasParent(index) && getParentData(index) > arr[index]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    } // heapifyUp last elem

    @Override
    public void changeElemValue(int index, int newValue) {
        int currentValue = arr[index];
        if (newValue < currentValue) {
            heapifyUp(index);
        }else{
            heapifyDown(index);
        }
    }

}
