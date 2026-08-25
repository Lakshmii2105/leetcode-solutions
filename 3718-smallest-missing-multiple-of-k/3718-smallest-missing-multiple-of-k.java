class Solution {
    public int missingMultiple(int[] nums, int k) {
    int current = k;
    while (true) {
    boolean found = false;
     for (int num : nums) {
     if (num == current) {
     found = true;
    break;
                }
            }
            if (!found) {
                return current;
            }
            current += k;
        }
    }
}