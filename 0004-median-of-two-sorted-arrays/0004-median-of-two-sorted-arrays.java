class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        // Merge the two sorted arrays
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // Copy remaining elements of nums1
        while (i < m) {
            merged[k++] = nums1[i++];
        }

        // Copy remaining elements of nums2
        while (j < n) {
            merged[k++] = nums2[j++];
        }

        int len = merged.length;

        // Find median
        if (len % 2 == 1) {
            return merged[len / 2];
        } else {
            return (merged[len / 2] + merged[(len / 2) - 1]) / 2.0;
        }
    }
}