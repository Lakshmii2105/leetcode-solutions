class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        // Calculate total area and search range
        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            totalArea += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double half = totalArea / 2.0;

        // Binary search
        for (int i = 0; i < 60; i++) { // enough for 1e-5 precision
            double mid = (low + high) / 2.0;
            double below = areaBelow(squares, mid);

            if (below < half) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    // Calculate area below horizontal line y
    private double areaBelow(int[][] squares, double yLine) {
        double area = 0;

        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];

            if (yLine <= y) {
                continue; // completely above
            } else if (yLine >= y + l) {
                area += l * l; // completely below
            } else {
                area += (yLine - y) * l; // partially below
            }
        }
        return area;
    }
}
