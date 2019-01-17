public class Bug
{
    private static void foo(Object o) { }
    private static void foo2(final String o) { }
    public static void main(String[] args)
    {
        final String s1 = "";
        final String s2 = "";

	foo2(s1);
        boolean flag = true;    // or false, doesn't matter
        foo2(flag ? s1 : s2);
    }
}
