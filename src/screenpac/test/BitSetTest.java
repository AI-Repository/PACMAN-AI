package screenpac.test;

import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        BitSet a = new BitSet(100);
        BitSet b = new BitSet(100);
        // b.set(50);
        a.xor(b);
        // System.out.println(a.nextSetBit(0));

        System.out.println(a.size());
        System.out.println(a.length());
        a.set(50);
        a.set(56);
        BitSet c = (BitSet) a.clone();
        c.clear(50);
        c.clear(56);
        a.clear(50);
        a.clear(56);

        System.out.println("BitSet a: ");
        System.out.println(a.length());
        System.out.println(a.cardinality());
        System.out.println(a.isEmpty());

        System.out.println("BitSet c: (results should be the same as a)");
        System.out.println(c.length());
        System.out.println(c.cardinality());
        System.out.println(c.isEmpty());
    }
}
