public class MyImpl implements MyInterface<DerivedClass2>
{
    @Override
    public void process(DerivedClass2 dc)
    {
        System.out.println("MyImpl: process()");
    }
}
