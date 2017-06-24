package wox.serial;

public class ReferenceTest {
    int w,z;
    Integer x,y;
    ReferenceTest next;

    public static void main(String[] args) {
        ReferenceTest rt = new ReferenceTest();
        rt.w = 5;
        rt.x = rt.w;
        rt.y = new Integer(8);
        rt.z = rt.y;
        rt.next = rt;
        Easy.save(rt, "ReferenceTest.xml");
    }
}
