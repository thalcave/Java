import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class CollectionsEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		{
			List<String> strList = new LinkedList<String>();
			strList.add("first string");
			strList.add("second string");
			
			ListIterator<String> iter = strList.listIterator();
			
			while (iter.hasNext())
			{
				System.out.println(iter.next());
			}
			
			for (String string : strList) {
				System.out.println(string);
			}
		}

	}

}
