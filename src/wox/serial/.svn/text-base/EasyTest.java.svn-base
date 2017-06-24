package wox.serial;

import java.math.BigInteger;

public class EasyTest {
    public static void main(String[] args) {
        Object ob = new BigInteger("10");
        Easy.save(ob, "test.xml");
        Object back = Easy.load("test.xml");
        System.out.println("Loaded object back in");


        Easy.save(back, "back.xml");

        
        System.out.println(back);
    }

    public static void main2(String[] args) {
        TestObject ob = new TestObject(5);
        System.out.println(ob.inc());
        Easy.save(ob, "test.xml");
        Object back = Easy.load("test.xml");
        System.out.println("Loaded object back in");
        Easy.save(back, "back.xml");
        System.out.println(((TestObject) back).inc());
    }
}
