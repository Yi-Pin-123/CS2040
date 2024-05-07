import java.util.ArrayList;

public class teque {
    private static final int MAX_SIZE = 500000; // 10^6/2
    private ArrayList<Integer> array1, array2;
    private int head, tail, tailStart;

    public teque() {
        array1 = new ArrayList<>(MAX_SIZE);
        array2 = new ArrayList<>();
        head = MAX_SIZE - 1;
        //tail = 0;
        tailStart = MAX_SIZE - 1;

        // Initialize array1 with null values
        //so that you can add to the end of array1
        for (int i = 0; i < MAX_SIZE; i++) {
            array1.add(null);
            array2.add(null);
        }
    }

    private void push_front(int x) {
        //if array1 is longer than array 2, 
        if (array1.size() - head - 1 > array2.size() - tailStart - 1) {
            //remove the last element from the array1
            int lastElementOfArray1 = array1.remove(array1.size() - 1);
            //add last element of array 1 to the start of array2 
            array2.set(tailStart, lastElementOfArray1);
            tailStart--;
            array1.set(head, x);
            head--;
            
        //else: add to head of array1 and adjust head pointer
        } else {
            array1.set(head, x);
            head--;
        }
    }

    private void push_back(int x) {
        /***both arrays are empty:
        then can add to head of the array1 without shifting tailStart or adding to the array2***/
        if (array1.size() - head - 1 == 0 && array2.size() - tailStart - 1 == 0) {
            array1.set(head, x);
            head--;
        } else {
            //if array2 >=  array 1 length
            if (array2.size() - tailStart - 1 >= array1.size() - head - 1) {
                //add the first element of array 2 to end of array1
                //shift tailStart back
                array1.add(array2.get(tailStart + 1));
                tailStart++;
            }
            //add new element to tail
            array2.add(x);
        }
    }

    private void push_middle(int x) {
        //if both array same length, add to end of array1
        if (array1.size() - head - 1 == array2.size() - tailStart - 1) {
            array1.add(x);
        //if not same length add to array 2 accordingly
        } else {
            array2.set(tailStart, x);
            tailStart-=1;
        }
    }

    private int get(int x) {
        if (x < array1.size() - head - 1) {
            return array1.get(head + 1 + x);
        } else {
            return array2.get(x - (array1.size() - head - 1) + tailStart + 1);
        }
    }
    

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int N = io.getInt();
        teque Teque = new teque();

        for (int i = 0; i < N; i++) {
            String operation = io.getWord();
            int x = io.getInt();

            switch (operation) {
                case "push_back":
                    Teque.push_back(x);
                    //Teque.checker();
                    break;
                case "push_front":
                    Teque.push_front(x);
                    //Teque.checker();
                    break;
                case "push_middle":
                    Teque.push_middle(x);
                    //Teque.checker();
                    break;
                case "get":
                    io.println(Teque.get(x));
                    break;
            }
        }

        io.close();
    }
}
