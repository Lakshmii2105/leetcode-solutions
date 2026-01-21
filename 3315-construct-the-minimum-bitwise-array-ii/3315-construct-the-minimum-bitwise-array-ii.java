class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
     int n=nums.size();
     int ans[]=new int[n];
     for(int i=0;i<n;i++){
        int x=nums.get(i);
        if(x%2==0){
            ans[i]=-1;
        }
        else{
int trailingOnes = Integer.numberOfTrailingZeros(~x);
                ans[i] = x - (1 << (trailingOnes - 1));
        }
     }
     return ans;   
    }
}