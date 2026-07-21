class Solution {
    public int[] distinctDifferenceArray(int[] nums) {
      HashSet<Integer> set= new HashSet<>();
      int[] prefix=new int[nums.length];
       int[] suffix=new int[nums.length];
       int[] answer=new int[nums.length];
      for(int i=0;i<nums.length;i++){
        set.add(nums[i]);
        prefix[i]=set.size();
      } 
      set.clear();
      for(int i=nums.length-1;i>=0;i--){
        set.add(nums[i]);
        suffix[i]=set.size();
        
      }
      for (int i = 0; i < nums.length; i++) {
      if (i == nums.length - 1)
    answer[i] = prefix[i];
     else
    answer[i] = prefix[i] - suffix[i + 1];
      } 
    return answer;
    }
    

}