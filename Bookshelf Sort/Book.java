public class Book implements Comparable<Book> {

    private String title;
    private String author;
    private String genre;
    private int numPages;
    private int a;


    public Book(String title, String author, String genre, int numPages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.numPages = numPages;

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumPages() {
        return numPages;
    }

    public boolean equals(Book other) {
        boolean authorMatches = author.equals(other.getAuthor());
        boolean titleMatches = title.equals(other.getTitle());
        return authorMatches && titleMatches;
    }

    // Implement this
    public int compareTo(Book other) {
        a = author.compareTo(other.author);
        if (a == 0) {
            return title.compareTo(other.title);
        }
        else {
            return a;
        }

    }
}