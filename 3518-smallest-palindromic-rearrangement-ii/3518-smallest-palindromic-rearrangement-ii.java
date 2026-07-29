class Solution {

    static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if ((freq[i] & 1) == 1)
                mid = (char) ('a' + i);
        }

        if (countWays(half, halfLen) < k)
            return "";

        StringBuilder first = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half, halfLen - pos - 1);

                if (ways >= k) {
                    first.append((char) ('a' + c));
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        String second = new StringBuilder(first).reverse().toString();

        return mid == 0
                ? first.toString() + second
                : first.toString() + mid + second;
    }

    private long countWays(int[] cnt, int total) {

        long ans = 1;
        int remain = total;

        for (int i = 0; i < 26; i++) {

            if (cnt[i] == 0)
                continue;

            ans *= comb(remain, cnt[i]);

            if (ans > LIMIT)
                return LIMIT;

            remain -= cnt[i];
        }

        return ans;
    }

    private long comb(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            res = res * (n - r + i) / i;

            if (res > LIMIT)
                return LIMIT;
        }

        return res;
    }
}