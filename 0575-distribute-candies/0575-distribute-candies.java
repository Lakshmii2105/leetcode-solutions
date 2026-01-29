 import java.util.HashSet;
 class Solution {
    public int distributeCandies(int[] candyType) {
     HashSet<Integer> arr= new HashSet<>();
     for(int i:candyType){
        arr.add(i);
     }
return Math.min(arr.size(), candyType.length / 2);
    }
 }