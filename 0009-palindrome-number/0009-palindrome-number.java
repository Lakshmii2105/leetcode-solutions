class Solution {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int temp=x;
     int r;
     int sum=0;
     while(x>0){
       r=x%10;
       sum=sum*10+r;
       x/=10;
     }
     return sum==temp;
    }
}