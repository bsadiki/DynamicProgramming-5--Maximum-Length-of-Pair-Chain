package com.xmart.solution;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[][] values = {{4, 5}, {1, 2}, {7, 8}, {2, 3}, {6, 7}, {9, 10}, {3, 4}};
        System.out.println(maxLengthOfPairChain(values));
    }

    private static int maxLengthOfPairChain(int[][] values) {
        if (values.length == 1 || values.length == 0)
            return values.length;
        values = sortValues(values);
        int[] maxLenghtInN = new int[values.length];
        maxLenghtInN[0] = 1;
        getMaxChainedWithPreviousPairs(values, maxLenghtInN);
        return maxLenghtInN[maxLenghtInN.length - 1];
    }

    private static int[][] sortValues(int[][] values) {
        return Arrays.stream(values).map(val -> new Pair(val[0], val[1])).sorted()
                .map(Pair::convert)
                .toArray(int[][]::new);
    }

    private static void getMaxChainedWithPreviousPairs(int[][] values, int[] maxLenghtInN) {
        for (int i = 1; i < values.length; i++) {
            for (int j = i - 1; j > 0; j--) {
                if (iCanChainWithN(values[i], values[j])) {
                    maxLenghtInN[i] = 1 + maxLenghtInN[j];
                    break;
                }
            }
            maxLenghtInN[i] = Math.max(maxLenghtInN[i], maxLenghtInN[i - 1]);
        }
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