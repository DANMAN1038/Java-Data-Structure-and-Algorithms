import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> first, last;
    private int size, nextIndex;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
        nextIndex = 0;
    }

    public boolean add(T element) {
        // for the sake of lab, let's _not_ allow null data
        if (element == null) return false;

        Node<T> newNode = new Node<>(element);

        if (size == 0)
            first = newNode;
        else
            last.setNext(newNode);

        last = newNode;
        size++;

        return true;
    }

    public String toString() {
        String ret = "[";
        Node<T> ptr = first;
        for (int i = 0; i < size; i++) {
            ret += ptr.getData().toString() + ", ";
            ptr = ptr.getNext();
        }

        return size == 0 ? ret + "]" : ret.substring(0, ret.length() - 2) + "]";
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }


    // TODO implement me!
    private class LinkedListIterator implements Iterator<T> {
        Node<T> current = LinkedList.this.first;

        @Override
        public boolean hasNext() {
            if (current != null) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            if (hasNext()) {
                T nextEntry = current.getData();
                current = current.getNext();
                nextIndex++;
                return nextEntry;
            } else {
                throw new NoSuchElementException("Iterator is at the end of list");
            }
        }

        //@Override
        public Iterator<T> iterator() {
            return new LinkedListIterator();
        }
    }



        // TODO implement me!
   public static int[] nCopies(LinkedList<Integer> x){
       int newArray[];
       int count = 0;
       Node<Integer> hi = x.first;
       while (hi != null) {
           count += hi.getData();
           hi = hi.getNext();
       }

       hi = x.first;
       newArray = new int[count];
       int idx = 0;
       while(hi != null) {
           int c = hi.getData();
           for(int i =0; i < c; i++) {
               newArray[idx++] = c;
           }
           hi = hi.getNext();
       }
//       for(int i = 0; i < x.size; i++){
//           count = 0;
//           while(count<hi.getData()){
//               newArray[i]=hi.getData();
//               count++;
//           }
//           hi = hi.getNext();
//       }
       return newArray;
    }

    // TODO implement me!
    public static LinkedList<Integer> countingSort(LinkedList<Integer> lst) {
        Node<Integer> hi = lst.first;
        int l = lst.first.getData();
        while(hi!=null){
            if(l>hi.getData()){
                l = l;
            }
            else{
                l = hi.getData();
            }
            hi = hi.getNext();
        }
        int Array[];
        Array = new int[l+1];
        int counts = l+1;
        hi = lst.first;
        for(int i = 0; i < lst.size; i++){
            Array[hi.getData()] = Array[hi.getData()] + 1;
            hi = hi.getNext();
        }
        LinkedList<Integer> ret = new LinkedList<Integer>();
        for(int i = 0; i < counts; i++){
            for(int j = 0; j < Array[i]; j++){
                ret.add(i);
            }
        }
        return ret;
    }

//    // TODO implement me!
    public void reverse() {
        Node current = first;
        Node prev = null;
        Node next = null;
        Node hold = null;
        while(current!=null){
            next = current.getNext();
            current.setNext(prev);

//            hold = next.getNext();
//            hold = prev;

            prev = current;
            current=current.getNext();


//            Node<T> okay = current.getNext();
//            current.setNext(prev);
//            prev = current;
//            current = okay;
        }
        hold = first;
        first= last;
        last = hold;
    }

//    // TODO implement me!
//    public void shuffle() {
//
//    }
    public void print(){
        Node node = first;
        while(node!=null){
            System.out.println(node.getData());
            node = node.getNext();
        }
    }

        // TODO implement me!
        public static void main(String[] args) {
            LinkedList<Integer> list = new LinkedList<Integer>();
            list.add(1);
            list.add(2);
            list.add(3);
            //Iterator<String> it = list.iterator();
            //while(it.hasNext()){
              //  System.out.println(it.next());
            //}
            //System.out.println(it.next());//gives me NoSuchElementException
//            System.out.println(list.countingSort(list));
//            int[] arr = LinkedList.nCopies(list);
//            for(int i = 0; i < arr.length; i++){
//                System.out.println(arr[i]);
//            }
            list.reverse();
            list.print();
        }


}
