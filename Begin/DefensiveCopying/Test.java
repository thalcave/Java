import java.util.*;

class TestCopy
{
    private final String s;
    private final List<Integer> ls;
    public TestCopy(final String s, final List<Integer> ls)
    {
        this.s = s;
        this.ls = ls;
    }

    public void print()
    {
        System.out.println("in class s: " + s + " list size: " + ls.size());
    }
}


public class Test
{
    public static void main(String[] args)
    {
        List<Integer> ls = new ArrayList<>();
        ls.add(0);
        ls.add(1);
        String str = "first string";
        TestCopy tc = new TestCopy(str, ls);
        tc.print();

        str = "second string";
        ls.add(2);
        tc.print();

        System.out.println("end of program");
    }
}
