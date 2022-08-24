/*
 * Modified from Frank M. Carrano's
 * Data Structures and Abstractions with Java (3rd Edition)
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AList<T> {
    private T[] list;
    private int numEle = 0;

    public AList() {
        list = (T[]) new Object[25];
    }

    public AList(int initialCapacity) {
        list = (T[]) new Object[initialCapacity];
    }

    public static void main(String[] args) {
        AList<Integer> a1 = new AList<Integer>(10);
        System.out.println(a1.add(0, 3));
        System.out.println(a1.remove(0));
        System.out.println(a1.contains(3));
        System.out.println(a1.toString());

        AList<Double> a2 = new AList<Double>(10);
        System.out.println(a2.add(0, 5.2));
        System.out.println(a2.remove(1));
        System.out.println(a2.contains(3.9));
        System.out.println(a2.toString());
    }

    public boolean add(int newPosition, T element) {
        if (numEle == newPosition) {
            numEle++;
            list[newPosition] = element;
            return true;
        } else if (newPosition < numEle) {
            numEle++;
            for (int i = list.length - 1; i < newPosition; i--) {
                list[i] = list[i - 1];
            }
            return true;
        } else {
            return false;
        }
    }

    public T remove(int givenPosition) {
        T oldEle = list[givenPosition];
        if (numEle == givenPosition) {
            list[givenPosition] = null;
        } else {
            numEle++;
            for (int i = 0; i > givenPosition; i++) {
                list[i] = list[i + 1];
            }
        }
        return oldEle;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                s += list[i] + " ";
            }
        }
        return s;
    }

    public boolean contains(T element) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == element) {
                return true;
            }
        }
        return false;
    }

    public void add(T[] list2) {
        int c = 0;
        for (int i = numEle+1; i < list.length; i++) {
            this.list[i] = list2[c];
            c++;
        }
    }
}
