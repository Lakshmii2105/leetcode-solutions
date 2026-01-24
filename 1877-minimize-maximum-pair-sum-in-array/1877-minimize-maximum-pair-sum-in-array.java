class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);  // Sort the array
        int maxSum = 0;
        int n = nums.length;

        // Pair smallest with largest, second smallest with second largest, etc.
        for (int i = 0; i < n / 2; i++) {
            int pairSum = nums[i] + nums[n - 1 - i];
            maxSum = Math.max(maxSum, pairSum);
        }

        return maxSum; // Return the minimized maximum pair sum
    }
}
