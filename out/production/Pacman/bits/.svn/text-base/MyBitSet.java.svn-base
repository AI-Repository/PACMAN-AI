package bits;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: sml
 * Date: 13-Jul-2010
 * Time: 17:51:27
 * To change this template use File | Settings | File Templates.
 */

public class MyBitSet {
    int n; // number of bits set to one
    int maxSize; // the number of bits the set contains
    long[] a; // array of long to store the bits

    public MyBitSet(int maxSize) {
        this.maxSize = maxSize;
        a = new long[1 + (maxSize / 64)];
        n = 0;
    }

    public MyBitSet copy() {
        MyBitSet mbs = new MyBitSet(maxSize);
        mbs.n = n;
        for (int i=0; i<a.length; i++) {
            mbs.a[i] = a[i];
        }
        return mbs;
    }

    public boolean get(int i) {
        checkRange(i);
        // find the correct array entry
        int li = i / 64;
        // then the bit to query within that
        int ix = i % 64;
        int bit = 1 << ix;
        return (bit & a[li]) > 0;
    }

    public void set(int i) {
        checkRange(i);
        // find the correct array entry
        int li = i / 64;
        // then the bit to query within that
        int ix = i % 64;
        int bit = 1 << ix;

        // only set it and increment the counter if it is not already set
        if ((bit & a[li]) == 0) {
            a[li] |= bit;
            n++;
        }
    }

    public void clear(int i) {
        checkRange(i);
        // find the correct array entry
        int li = i / 64;
        // then the bit to query within that
        int ix = i % 64;
        int bit = 1 << ix;

//        System.out.println("Bit = " + bit);
//        System.out.println("Value: " + (bit & a[li]));

        // only clear it and increment the counter if it is currently set
        if ((bit & a[li]) != 0) {
            a[li] ^= bit;
            n--;
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(maxSize + " ");
        sb.append(n + " ");
        for (long x : a) {
            sb.append(x + " ");
        }
        return sb.toString();
    }

    public static MyBitSet fromString(String s) {
        return new MyBitSet(s);
    }
    
    public MyBitSet (String s) {
        Scanner scan = new Scanner(s);
        maxSize = scan.nextInt();
        n = scan.nextInt();
        a = new long[1 + (maxSize / 64)];
        for (int i=0; i<a.length; i++) {
            a[i] = scan.nextLong();
        }
    }

    private void checkRange(int i) {
        if (i >= maxSize || i < 0) {
            throw new RuntimeException("MyBitSet index out of range: " + i);
        }
    }


}
