import java.util.*;

class Solution {
    private TreeMap<Integer, Integer> left = new TreeMap<>();
    private TreeMap<Integer, Integer> right = new TreeMap<>();
    private int leftCount = 0;
    private long leftSum = 0;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int m = k - 1; // We need to pick k-1 elements total from the window
        long minSum = Long.MAX_VALUE;

        left.clear();
        right.clear();
        leftSum = 0;
        leftCount = 0;

        // The window size is dist + 1. 
        // We look at all elements from index 1 to 1 + dist.
        // We need to pick the smallest m (which is k-1) elements from this window.
        for (int i = 1; i <= dist + 1; i++) {
            add(nums[i], m);
        }

        minSum = nums[0] + leftSum;

        // Slide the window across the rest of the array
        for (int i = 2; i <= n - dist; i++) {
            // Remove the element that is falling out of the window (nums[i-1])
            remove(nums[i - 1], m);
            
            // Add the new element entering the window (nums[i + dist])
            if (i + dist < n) {
                add(nums[i + dist], m);
            }
            
            // Only update minSum if we have enough elements to satisfy m
            if (leftCount == m) {
                minSum = Math.min(minSum, nums[0] + leftSum);
            }
        }

        return minSum;
    }

    private void add(int val, int m) {
        left.put(val, left.getOrDefault(val, 0) + 1);
        leftSum += val;
        leftCount++;

        // If left set exceeds the number of elements we need, move the largest to right
        if (leftCount > m) {
            int largestInLeft = left.lastKey();
            removeFromMap(left, largestInLeft);
            leftSum -= largestInLeft;
            leftCount--;
            right.put(largestInLeft, right.getOrDefault(largestInLeft, 0) + 1);
        }
    }

    private void remove(int val, int m) {
        if (left.containsKey(val)) {
            removeFromMap(left, val);
            leftSum -= val;
            leftCount--;
        } else {
            removeFromMap(right, val);
        }

        // If left set is now too small, pull the smallest from right
        if (leftCount < m && !right.isEmpty()) {
            int smallestInRight = right.firstKey();
            removeFromMap(right, smallestInRight);
            left.put(smallestInRight, left.getOrDefault(smallestInRight, 0) + 1);
            leftSum += smallestInRight;
            leftCount++;
        }
    }

    private void removeFromMap(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);
        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }
}