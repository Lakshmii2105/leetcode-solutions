class Solution {
    public int gcdOfOddEvenSums(int n) {
     int sumOdd=0;
     int sumEven=0;
     int gcd=1;
     for(int i=1;i<=2*n-1;i++){
        sumOdd=sumOdd+i;
        }
        for(int i=1;i<=2*n;i++){
            sumEven=sumEven+i;
        }
     
        while (sumEven != 0) {
    int rem = sumOdd % sumEven;
    sumOdd = sumEven;
    sumEven = rem;
} 
return sumOdd;   
     } 
     
    }
