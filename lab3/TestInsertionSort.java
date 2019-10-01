import java.util.*;
class TestInsertionSort{

    public static void main(String args[]){

        //if no args run testFromConsole
        if(args.length == 0){
            testFromConsole();
        }

        //runs shuffleTest with args
        else {

            System.out.println("running shuffleTest with each int..");
            for(int i = 0; i < args.length; i++){
                int shuffleRun = Integer.parseInt(args[i]);
                shuffleTest(shuffleRun);
            }

        }

    }

    //checks if arr is non-decreasingly sorted
    private static boolean isSorted(int[] arr){
        int key = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    //checks if the frequency of elements in 2 arrays are the same
    private static boolean sameElements(int[] a, int[] b){
        HashMap<Integer, Integer> aMap = new HashMap<Integer, Integer>(); //mapping first array

        for(int every : a){
            if(aMap.containsKey(every)) {
                aMap.put(every, aMap.get(every) + 1);
            }
            else {
                aMap.put(every, 1);
            }
        }

        //subtracts counts in second array from hashmap (counts should cancel out)
        for(int every : b){
            if(aMap.containsKey(every)) {
                aMap.put(every, aMap.get(every) - 1);
            }
            else {
                return false;
            }
        }

        //checks that all counts are 0
        for(Integer value : aMap.values()){
            if(value != 0){
                return false;
            }
        }
        return true; //returns true if all elements 0
    }

    //takes user input to build array, tests insertionSort with isSorted and sameElements
    private static void testFromConsole(){
        System.out.println("give testFromConsole some ints to build an array");
        Scanner inputArgs = new Scanner(System.in);

        while(true){

            String numString = inputArgs.nextLine(); //takes line of args

            String[] strArr = numString.split(" "); //splits them into string arr

            //converts string[] to int[]
            int[] nums = new int[strArr.length];
            for(int i = 0; i < nums.length; i++){
                nums[i] = Integer.parseInt(strArr[i]);
            }

            //checks if [] is sorted and if frequency of elements before and after sort are equal
            if(isSorted(InsertionSort.insertionSort(nums)) && sameElements(InsertionSort.insertionSort(nums), nums)){
                System.out.println("Passed Test");
            }
            else{
                System.out.println("FAILED test");
            }

            System.out.println("give it some more numbers to test again");
        }
    }

    //builds a shuffled int[] of number 0 to N-1
    private static void shuffleTest(int N){
        ArrayList<Integer> testList = new ArrayList<Integer>(); //list of length N
        for(int i = 0; i < N; i++){ //populates list
            testList.add(i);
        }

        //converts initial list to int[]
        int[] testArr = new int[testList.size()];
        for(int i = 0; i < testList.size(); i++){
            testArr[i] = testList.get(i);
        }

        Collections.shuffle(testList); //shuffles list
        //converts shuffled list to array
        int[] shuffleArr = new int[testList.size()];
        for(int i = 0; i < testList.size(); i++){
            shuffleArr[i] = testList.get(i);
        }

        if(Arrays.equals(testArr, InsertionSort.insertionSort(shuffleArr))){ //compares original arr to (now sorted) shuffled arr
            System.out.println("Passed Test");
        }
        else{
            System.out.println("FAILED Test");
        }
    }
}