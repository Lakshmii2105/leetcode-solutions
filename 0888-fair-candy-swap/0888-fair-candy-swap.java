class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {

        int sumA = 0;
        int sumB = 0;

        for (int a : aliceSizes) sumA += a;
        for (int b : bobSizes) sumB += b;

        int n = aliceSizes.length;
        int m = bobSizes.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int newA = sumA - aliceSizes[i] + bobSizes[j];
                int newB = sumB - bobSizes[j] + aliceSizes[i];

                if (newA == newB) {
                    return new int[]{aliceSizes[i], bobSizes[j]};
                }
            }
        }

        return new int[0];
    }
}
