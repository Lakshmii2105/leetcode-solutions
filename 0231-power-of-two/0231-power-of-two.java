class Solution {
    public boolean isPowerOfTwo(int n) {
      for(int x=0;x<=100;x++){
        if(Math.pow(2,x)==n){
        return true;
        }
      }
      return false;
        
}
}