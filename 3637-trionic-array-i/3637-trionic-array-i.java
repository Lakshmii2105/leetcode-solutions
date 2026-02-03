class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return false;

        int i = 0;

        // 1️⃣ strictly increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i == 0) return false;   // no increasing
        int p = i;

        // 2️⃣ strictly decreasing
        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }
        if (i == p) return false;   // no decreasing
        int q = i;

        // 3️⃣ strictly increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // ✅ must end at last index AND third segment must exist
        return i == n - 1 && q < n - 1;
    }
}
