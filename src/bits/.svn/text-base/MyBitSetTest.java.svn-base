package bits;

public class MyBitSetTest {
    public static void main(String[] args) {
        MyBitSet mbs = new MyBitSet(200);
        System.out.println(mbs);
        mbs.clear(2);
        System.out.println(mbs);
        mbs.set(2);
        System.out.println(mbs);
        String s = mbs.toString();
        System.out.println(s);
        MyBitSet mbs2 = MyBitSet.fromString(s);
        mbs2.set(150);
        mbs2.clear(150);
        System.out.println("BitSet 2:   " + mbs2);
    }

}
