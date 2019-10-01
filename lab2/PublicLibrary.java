import java.util.ArrayList;
import java.lang.*;

public class PublicLibrary<T> implements Library<T>{ //can be passed any object - generic

    private final ArrayList<T> publicLibrary;

    PublicLibrary(){
        this.publicLibrary = new ArrayList<T>();
    }


    public boolean add(T newEntry){
        for(T every : publicLibrary){
            if(every.equals(newEntry)){ //checks for duplicate objects
                return false;
            }
        }
        publicLibrary.add(newEntry); //adds object if duplicate doesn't exist
        return true;
    }

//    public String get(T current){
//        return;
//    }

    public String toString(){  //concatenates all objects in library to print them
        String everythingInLib = "";
        for(int i = 0; i < publicLibrary.size(); i++){
            // publicLibrary.get(i)
            everythingInLib += (publicLibrary.get(i).toString() +"\n");
        }
        return everythingInLib;
    }



}