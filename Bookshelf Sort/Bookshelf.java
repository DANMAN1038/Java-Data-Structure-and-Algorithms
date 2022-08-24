

public class Bookshelf {

    private Book[] books;
    public int numComparisons = 0;
    public Bookshelf(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }

    public Book[] booksCopy(){
        Book[] copy = new Book[books.length];
        for(int i = 0; i < books.length; i++){
            copy[i] = books[i];
        }
        return copy;
    }


    public Bookshelf mergeBookshelves(Bookshelf bookshelf){
        Book[] thisBooks = this.books;
        Book[] bookshelfBooks = bookshelf.books;
        Book[] mergedBooks = new Book[thisBooks.length + bookshelfBooks.length];

        int ptrThis = 0;
        int ptrBookshelf = 0;

        while(ptrThis < thisBooks.length && ptrBookshelf < bookshelfBooks.length){
            numComparisons+=1;
            if(thisBooks[ptrThis].compareTo(bookshelfBooks[ptrBookshelf]) < 0){
                mergedBooks[ptrBookshelf + ptrThis] = thisBooks[ptrThis];
                ptrThis++;
            }
            else{
                mergedBooks[ptrBookshelf + ptrThis] = bookshelfBooks[ptrBookshelf];
                ptrBookshelf++;
            }
        }

        if(ptrThis < thisBooks.length){
            for(int i = ptrThis; i < thisBooks.length; i++){
                mergedBooks[i + ptrBookshelf] = thisBooks[i];
            }
        }
        else {
            for(int i = ptrBookshelf; i < bookshelfBooks.length; i++){
                mergedBooks[i + ptrThis] = bookshelfBooks[i];
            }
        }

        Bookshelf newMerged = new Bookshelf(mergedBooks);
        newMerged.numComparisons = numComparisons+bookshelf.numComparisons;
        return newMerged;
    }

    public Bookshelf mergeSortBookshelf(){
        if (books.length > 1){
            Book[] books1 = Bookshelf.cloneBookArray(this.books, 0, books.length/2);
            Book[] books2 = Bookshelf.cloneBookArray(this.books, books.length/2, books.length);

            Bookshelf bookshelf1 = new Bookshelf(books1);
            Bookshelf bookshelf2 = new Bookshelf(books2);

            bookshelf1 = bookshelf1.mergeSortBookshelf();
            bookshelf2 = bookshelf2.mergeSortBookshelf();
            return bookshelf1.mergeBookshelves(bookshelf2);
        }
        else{
            return this;
        }
    }

    public static void printBookTitles(Book[] books){
        for (int i = 0; i < books.length; i++){
            System.out.println("Title: " + books[i].getTitle());
        }
    }

    public static void printBookTitlesAndAuthors(Book[] books){
        for (int i = 0; i < books.length; i++){
            System.out.println("Title: " + books[i].getTitle() + ", Authors: " + books[i].getAuthor());
        }
    }

    public static Book[] cloneBookArray(Book[] books, int startIndex, int endIndex){
        int diff = endIndex - startIndex;
        Book[] returnBooks = new Book[diff];
        for (int i = 0; i < returnBooks.length; i++){
            returnBooks[i] = books[startIndex + i];
        }
        return returnBooks;
    }

    public Bookshelf insertionSortBookshelf(){
        Book[] sortedBooks = this.booksCopy();
        int x, y;
        for(x = 1; x < sortedBooks.length; x++){
            Book largestBook = sortedBooks[x];
            for(y = x - 1; y >= 0 && largestBook.compareTo(sortedBooks[y]) < 0; y--){
                sortedBooks[y+1] = sortedBooks[y];
                numComparisons+=1;
            }
            sortedBooks[y+1] = largestBook;
        }
        Bookshelf newSorted = new Bookshelf(sortedBooks);
        newSorted.numComparisons = numComparisons;
        return newSorted;
    }
    public Bookshelf selectionSortBookshelf(){
        Book[] sortedBooks = this.booksCopy();
        //Book[] numComparisons = 0;
        int length = sortedBooks.length;
        for(int i = 0; i < length-1; i++){
            int mini = i;
            for(int j = i+1; j<length; j++){
                numComparisons+=1;
                if ((sortedBooks[i].compareTo(sortedBooks[j])) > 0){
                    mini = j;
                    Book temp = sortedBooks[mini];
                    sortedBooks[mini] = sortedBooks[i];
                    sortedBooks[i] = temp;
                }
            }
        }
        Bookshelf newSorted = new Bookshelf(sortedBooks);
        newSorted.numComparisons = numComparisons;
        return newSorted;
       // Bookshelf done = new Bookshelf(numComparisons);
        //return numComparisons;
    }
    public static void main(String[] args) {
        // random order
      //  Book[] books = {new Book("1984", "Orwell", "Fiction", 528),
        //        new Book("A Brief History Of Time", "Hawking", "Astronomy", 212),
//                new Book("Alice's Adventures in Wonderland", "Carroll", "Fantasy", 272),
//                new Book("Harry Potter : The Philosopher's Stone", "Rowling", "Fantasy", 256),
//                new Book("Harry Potter : The Chamber of Secrets", "Rowling", "Fantasy", 368),
//                new Book("Harry Potter : The Prisoner of Azkaban", "Rowling", "Fantasy", 464),
//                new Book("JK Rowling : Autobiography", "Rowling", "Non-Fiction", 500),
//                new Book("The Dark Tower: The Gunslinger", "King", "Horror", 224)};


        // sorted order
        Book[] books = {new Book("Alice's Adventures in Wonderland", "Carroll", "Fantasy", 272),
                new Book("1984", "Orwell", "Fiction", 528),
                new Book("A Brief History Of Time", "Hawking", "Astronomy", 212),
                new Book("The Dark Tower: The Gunslinger", "King", "Horror", 224),
                new Book("Harry Potter : The Chamber of Secrets", "Rowling", "Fantasy", 368),
                new Book("Harry Potter : The Philosopher's Stone", "Rowling", "Fantasy", 256),
                new Book("Harry Potter : The Prisoner of Azkaban", "Rowling", "Fantasy", 464),
                new Book("JK Rowling : Autobiography", "Rowling", "Non-Fiction", 500)};

        // Reverse order
//        Book.java[] books = {new Book.java("JK Rowling : Autobiography", "Rowling", "Non-Fiction", 500),
//                new Book.java("Harry Potter : The Prisoner of Azkaban", "Rowling", "Fantasy", 464),
//                new Book.java("Harry Potter : The Philosopher's Stone", "Rowling", "Fantasy", 256),
//                new Book.java("Harry Potter : The Chamber of Secrets", "Rowling", "Fantasy", 368),
//                new Book.java("The Dark Tower: The Gunslinger", "King", "Horror", 224),
//                new Book.java("A Brief History Of Time", "Hawking", "Astronomy", 212),
//                new Book.java("1984", "Orwell", "Fiction", 528),
//                new Book.java("Alice's Adventures in Wonderland", "Carroll", "Fantasy", 272)};

        Bookshelf bookshelf = new Bookshelf(books);
        //Uncomment below to start testing your sorting methods.
        Bookshelf sorted = bookshelf.insertionSortBookshelf();
        Bookshelf sorted2 = bookshelf.selectionSortBookshelf();
        Bookshelf sorted3 = bookshelf.mergeSortBookshelf();

        printBookTitlesAndAuthors(sorted.getBooks());
 //       System.out.println("this is the number of comparisons: " + sorted.numComparisons);
        System.out.println(sorted.numComparisons);
        System.out.println(sorted2.numComparisons);
        System.out.println(sorted3.numComparisons);
    }
}
