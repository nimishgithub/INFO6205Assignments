package edu.neu.coe.info6205.pell;

public class Pell {

    public Pell() {}

    public long get(int n) {
        if (n < 0) throw new UnsupportedOperationException("Pell.get is not supported for negative n");

        Long[] arrayLong = new Long[3];

        arrayLong[0] = Long.valueOf(0);

        arrayLong[1] = Long.valueOf(1);

        if (n <= 1) return Long.valueOf(n);

        for (int k = 2; k <= n; k++) {
            //2 * pell num of (num - 1) + pell num of (num - 2);
            arrayLong[k % 3] = 2 * arrayLong[(k + 2) % 3] + arrayLong[(k + 1) % 3];
        }

        return arrayLong[n%3];
    }
}