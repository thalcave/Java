import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


class ListHolder
{
    private List<String> strList = new LinkedList<String>();

    public ListHolder()
    {
            strList.add("first string");
            strList.add("second string");
    }

    public List<String> getList()
    {
            return strList;
    }
    
    public void modifyList()
    {
            strList.add("third string");
    }
}


public class CollectionsEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		{
                    ListHolder lHolder = new ListHolder();
                    List<String> strList = lHolder.getList();
			
                    ListIterator<String> iter = strList.listIterator();
                    
                    /*while (iter.hasNext())
                    {
                            System.out.println(iter.next());
                    }*/
                    
                    lHolder.modifyList();
                    
                    for (String string : strList) {
                            System.out.println(string);
                    }
		}

	}

}
