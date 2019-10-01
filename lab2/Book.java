class Book{ //defines attributes of book objects

    private String isbn;
    private String title;
    private String authorLast;
    private String authorFirst;
    private int year;

    public Book(String isbn, String title, String authorLast, String authorFirst, int year) {
        this.isbn = isbn;
        this.title = title;
        this.authorLast = authorLast;
        this.authorFirst = authorFirst;
        this.year = year;
    }

    public void setIsbn(String isbn) {  //setter methods for books
        this.isbn = isbn;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthorLast(String authorLast) {
        this.authorLast = authorLast;
    }
    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }
    public void setYear(int year) {
        this.year = year;
    }


    public String getIsbn() { //getter methods for books
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthorLast() {
        return authorLast;
    }
    public String getAuthorFirst() {
        return authorFirst;
    }
    public int getYear() {
        return year;
    }


    public boolean equals(Object obj){ //compares books
        Book comparedBook = (Book)obj;
        if(this.isbn == comparedBook.isbn && this.title == comparedBook.title){
            return true;
        }
        return false;
    }

    public String toString(){ //converts all attributes of book to string
        String bookLine = this.getIsbn() + " : " + " : " + this.getTitle() + " : " + this.getAuthorLast() + ", " + this.getAuthorFirst() + " : " + this.getYear();
        return bookLine;
    }
}