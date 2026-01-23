import java.util.*;

class Solution {

    static class Node {
        long val;
        int idx;
        Node prev, next;
        boolean alive = true;

        Node(long v, int i) {
            val = v;
            idx = i;
        }
    }

    static class Pair {
        long sum;
        int idx;
        Node l, r;

        Pair(Node a, Node b) {
            sum = a.val + b.val;
            idx = a.idx;
            l = a;
            r = b;
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++)
            nodes[i] = new Node(nums[i], i);

        for (int i = 0; i + 1 < n; i++) {
            nodes[i].next = nodes[i + 1];
            nodes[i + 1].prev = nodes[i];
        }

        Node head = nodes[0];

        // Count initial violations
        int bad = 0;
        Node cur = head;
        while (cur.next != null) {
            if (cur.val > cur.next.val) bad++;
            cur = cur.next;
        }

        if (bad == 0) return 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> a.sum != b.sum
                ? Long.compare(a.sum, b.sum)
                : Integer.compare(a.idx, b.idx)
        );

        for (int i = 0; i + 1 < n; i++)
            pq.offer(new Pair(nodes[i], nodes[i + 1]));

        int ops = 0;

        while (bad > 0) {
            Pair p = pq.poll();
            if (!p.l.alive || !p.r.alive || p.l.next != p.r)
                continue;

            Node L = p.l.prev;
            Node R = p.r.next;

            // Remove old violations
            if (L != null && L.val > p.l.val) bad--;
            if (p.l.val > p.r.val) bad--;
            if (R != null && p.r.val > R.val) bad--;

            Node merged = new Node(p.sum, p.l.idx);
            merged.prev = L;
            merged.next = R;

            if (L != null) L.next = merged;
            else head = merged;

            if (R != null) R.prev = merged;

            p.l.alive = false;
            p.r.alive = false;

            // Add new violations
            if (L != null && L.val > merged.val) bad++;
            if (R != null && merged.val > R.val) bad++;

            if (merged.prev != null)
                pq.offer(new Pair(merged.prev, merged));
            if (merged.next != null)
                pq.offer(new Pair(merged, merged.next));

            ops++;
        }

        return ops;
    }
}
