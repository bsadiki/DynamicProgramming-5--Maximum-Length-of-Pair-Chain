package com.xmart.solution;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[][] values = {{4, 5}, {1, 2}, {7, 8}, {2, 3}, {6, 7}, {9, 10}, {3, 4}};
        System.out.println(maxLengthOfPairChain(values, 7));
    }

    private static int maxLengthOfPairChain(int[][] values, int n) {
        if (n == 1 || n == 0)
            return n;
        values = sortValues(values);
        int maxLenght = 0;
        for (int i = n - 2; i >= 1; i--) {
            if (iCanChainWithN(values[n - 1], values[i])) {
                maxLenght = 1 + maxLengthOfPairChain(values, i + 1);
                break;
            }
        }
        return Math.max(maxLenght, maxLengthOfPairChain(values, n - 1));
    }

    private static int[][] sortValues(int[][] values) {
        return Arrays.stream(values).map(val -> new Pair(val[0], val[1])).sorted()
                .map(Pair::convert)
                .toArray(int[][]::new);
    }


    private static boolean iCanChainWithN(int[] currentPair, int[] previousPair) {
        return previousPair[1] < currentPair[0];
    }

    private static class Pair implements Comparable<Pair> {
        private final int numb1;
        private final int numb2;

        private Pair(int numb1, int numb2) {
            this.numb1 = numb1;
            this.numb2 = numb2;
        }

        @Override
        public int compareTo(Pair otherPair) {
            return Integer.compare(numb1, otherPair.numb1);
        }

        private int[] convert() {
            return new int[]{numb1, numb2};
        }
    }
}