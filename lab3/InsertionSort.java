import java.util.*;
class InsertionSort{

    public static int[] insertionSort(int[] arr){
       for(int i = 1; i < arr.length; i++) {
           int key = arr[i];
           int indexLeftOfKey = i - 1;

           while (indexLeftOfKey >= 0 && arr[indexLeftOfKey] > key){ //performs sorting for every i
               arr[indexLeftOfKey + 1] = arr[indexLeftOfKey];
               indexLeftOfKey--;
           }
           arr[indexLeftOfKey + 1] = key;
       }
       return arr; //return sorted arr
    }
}