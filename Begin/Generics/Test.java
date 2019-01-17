

public class Test
{
    public static void main(String[] args)
    {
        MyInterface<? extends BaseClass> myInterface = new MyImpl();
        //        myInterface.process(new DerivedClass2());
        helper(myInterface);
        
        System.out.println("end of program");
    }

    private static <T> void helper(MyInterface<? extends BaseClass> l)
    {
        l.process(new DerivedClass2());
    }
}
