import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

/**
 * A class that implements a list of objects by using an array.
 * Entries in a list have positions that begin with 1.
 * Duplicate entries are allowed.
 *
 * Modified from Frank M. Carrano's
 * Data Structures and Abstractions with Java (3rd Edition)
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 */

public class BList<T extends Comparable> {
    private T[] list;   // Array of list entries; ignore list[0]
    private int numberOfEntries;
    private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public BList() {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public BList(int initialCapacity) {
        integrityOK = false;

        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else // Is initialCapacity too big?
            checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Comparable[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        integrityOK = true;
    } // end constructor

    public void add(T newEntry) {
        add(numberOfEntries + 1, newEntry);
/*    // Alternate code
		checkIntegrity();
      list[numberOfEntries + 1] = newEntry;
      numberOfEntries++;
      ensureCapacity();
*/
    } // end add

    public void add(int givenPosition, T newEntry) {
        checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1)) {
            if (givenPosition <= numberOfEntries)
                makeRoom(givenPosition);
            list[givenPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity(); // Ensure enough room for next add
        } else
            throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
    } // end add

    public void add(T[] addList) {
        if (addList.length == 0)
            return;
        for (int i = 0; i < addList.length; i++) {
            add(addList[i]);
        }
    } // end add

    public T remove(int givenPosition) {
        checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            // Assertion: The list is not empty
            T result = list[givenPosition]; // Get entry to be removed

            // Move subsequent entries towards entry to be removed,
            // unless it is last in list
            if (givenPosition < numberOfEntries)
                removeGap(givenPosition);
            list[numberOfEntries] = null;
            numberOfEntries--;
            return result;                  // Return reference to removed entry
        } else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    } // end remove

    public void clear() {
        checkIntegrity();

        // Clear entries but retain array; no need to create a new array
        for (int index = 1; index <= numberOfEntries; index++) // Loop is part of Q4
            list[index] = null;

        numberOfEntries = 0;
    } // end clear

    public T replace(int givenPosition, T newEntry) {
        checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            // Assertion: The list is not empty
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        } else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    } // end replace

    public T getEntry(int givenPosition) {
        checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            // Assertion: The list is not empty
            return list[givenPosition];
        } else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    } // end getEntry

    public T[] toArray() {
        checkIntegrity();

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Comparable[numberOfEntries]; // Unchecked cast
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = list[index + 1];
        } // end for

        return result;
    } // end toArray

    public boolean contains(T anEntry) {
        checkIntegrity();
        boolean found = false;
        int index = 1;
        while (!found && (index <= numberOfEntries)) {
            if (anEntry.equals(list[index]))
                found = true;
            index++;
        } // end while
        return found;
    } // end contains

    public int getLength() {
        return numberOfEntries;
    } // end getLength

    public boolean isEmpty() {
        return numberOfEntries == 0; // Or getLength() == 0
    } // end isEmpty

    // Doubles the capacity of the array list if it is full.
    // Precondition: checkIntegrity has been called.
    private void ensureCapacity() {
        int capacity = list.length - 1;
        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity); // Is capacity too big?
            list = Arrays.copyOf(list, newCapacity + 1);
        } // end if
    } // end ensureCapacity

    // Makes room for a new entry at newPosition.
    // Precondition: 1 <= newPosition <= numberOfEntries + 1;
    //               numberOfEntries is list's length before addition;
    //               checkIntegrity has been called.
    private void makeRoom(int givenPosition) {
        // Assertion: (newPosition >= 1) && (newPosition <= numberOfEntries + 1)
        int newIndex = givenPosition;
        int lastIndex = numberOfEntries;

        // Move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--)
            list[index + 1] = list[index];
    }  // end makeRoom

    // Shifts entries that are beyond the entry to be removed to the
    // next lower position.
    // Precondition: 1 <= givenPosition < numberOfEntries;
    //               numberOfEntries is list's length before removal;
    //               checkIntegrity has been called.
    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition;
        for (int index = removedIndex; index < numberOfEntries; index++)
            list[index] = list[index + 1];
    } // end removeGap

    // Throws an exception if this object is corrupt.
    private void checkIntegrity() {
        if (!integrityOK)
            throw new SecurityException("AList object is corrupt.");
    } // end checkIntegrity

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a list " +
                    "whose capacity exceeds " +
                    "allowed maximum.");
    } // end checkCapacity

    // Creates a string representation of the list.
    public String toString() {
        String toReturn = "[";
        for (int i = 1; i < numberOfEntries; i++) {
            toReturn += list[i].toString() + ", ";
        }
        toReturn += list[numberOfEntries].toString() + "]";

        return toReturn;
    } // end toString

    // Converts a given file into a list.
    public static BList<String> fileToAList(File input) {
        Scanner s;
        try {
            s = new Scanner(input);
        } catch (Exception e) {
            System.out.println("File not found");
            return null;
        }
        BList<String> pre = new BList();
        BList<Integer> ind = new BList();
        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(",");
            pre.add(line[1]);
            ind.add(Integer.valueOf(line[0]));
        }
        String swap; //Value to swap
        int min; //Index value
        int mini; //Min index we are looking at
        for (int i = 1; i < ind.getLength() - 1; i++) {
            swap = pre.getEntry(i);
            mini = i;
            min = ind.getEntry(i);
            for (int j = i + 1; j < ind.getLength() + 1; j++) {
                if (min > ind.getEntry(j)) {
                    mini = j;
                    swap = pre.getEntry(j);
                    min = ind.getEntry(j);
                }
            }
            pre.remove(mini);
            ind.remove(mini);
            pre.add(mini,pre.getEntry(i));
            ind.add(mini,ind.getEntry(i));
            pre.remove(i);
            ind.remove(i);
            pre.add(i,swap);
            ind.add(i,min);

        }
        return pre;
    }
    public BList<T> slice(int start, int stop){
        BList b2 = new BList();
      //  T[] newList = new T[stop-start];
        for(int i = 0; i < stop-1; i++){
            b2.add(list[start+i]);
        }
        return b2;

    }
    public BList<T> slice(int start, int stop, int step){
        BList b2 = new BList();
        for(int i = 0; i < stop-1; i+=step){
            b2.add(list[start+i]);
        }
        return b2;
    }
    public void sort(boolean ascending) {
        if (ascending) {
            for (int i = 1; i < numberOfEntries - 1; i++) {
                int mini = i;
                for (int j = i + 1; j < numberOfEntries+1; j++) {
                    if ((list[i].compareTo(list[j])) > 0) {
                        mini = j;
                        T temp = list[mini];
                        list[mini] = list[i];
                        list[i] = temp;
                    }
                }
            }
            System.out.println(this);
        }
        if (ascending==false) {
            for (int i = 1; i < numberOfEntries - 1; i++) {
                int mini = i;
                for (int j = i + 1; j < numberOfEntries+1; j++) {
                    if ((list[i].compareTo(list[j])) < 0) {
                        mini = j;
                        T temp = list[mini];
                        list[mini] = list[i];
                        list[i] = temp;
                    }
                }
            }
            System.out.println(this);
        }
    }
    public static void main(String[] args){
        BList<String> b1 = new BList<String>(4);
        b1.add(new String[] {"a","b","c","d"});
        //System.out.println(b1.slice(1,3));
        //System.out.println(b1.slice(1,4, 2));
        //b1.sort(true);
        //b1.sort(false);
        System.out.println(fileToAList(new File("input.txt")));
    }
}