class Solution {
    public boolean isHappy(int n) {
         HashSet<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
        int sum=0;
        int rem;
     while(n>0){
        rem=n%10;
        sum=sum+rem*rem;
        n/=10;
     }
     n=sum;
     }
     return n==1;   
    }
}