import java.util.*;
public class Test{

    public static void main(String args[]){
        Library<Book> myLibrary = new PublicLibrary<Book>();

        String[] isbns = {"ABCDEFGHIJ", "QRSTUVWXYZ", "0123456789", "ABCDEFGHIJ"};

        for(int i = 0; i < isbns.length; i++){
            Book unkown = new Book(isbns[i], "unknownTitle", "unknownLast", "unknownFirst", i);
            myLibrary.add(unkown);
        }
        System.out.println(myLibrary);
    }
}