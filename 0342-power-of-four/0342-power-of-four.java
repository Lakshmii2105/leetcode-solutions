class Solution {
    public boolean isPowerOfFour(int n) {
     for(int x=0;x<=100;x++){
        if(Math.pow(4,x)==n){
            return true;
        }
     } 
     return false;  
    }
}