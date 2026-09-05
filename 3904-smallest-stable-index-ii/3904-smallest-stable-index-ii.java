class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n=nums.length;
      int suffixMin = nums[n - 1];
    int[] min = new int[n];
        min[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            min[i] = Math.min(nums[i], min[i + 1]);
        }
        int prefixMax = nums[0];
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            if (prefixMax - min[i] <= k) {
                return i;
            }
        }
        return -1;
    }
}