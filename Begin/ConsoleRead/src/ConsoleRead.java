
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

class Employee implements Cloneable
{
	public Employee()
	{
		hireDate = new Date();
		name = new String();
		salary = 0.0;
	}
	
	public Employee(Date hire, String nm, double sal)
	{
		hireDate = (Date)hire.clone();
		name = nm;
		salary = sal;
	}
	
	public Date getDate()
	{
		//return hireDate; wrong: returns referencfe to mutable data
		return (Date)hireDate.clone();
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Employee:");
		builder.append("\n");
		builder.append(hireDate.toString());
		builder.append("\n");
		builder.append(name);
		builder.append("\n");
		builder.append(salary);
		builder.append("\n");
		
		return builder.toString();
	}
	
	public boolean testEmployee(Employee current)
	{
		return salary == current.salary;
	}
	
	//@Override public boolean equals(Employee other)	//error, because Object::equals is not overriden
	@Override public boolean equals(Object other)	//now Object::equals is overriden
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;
		//cast Object to Employee (we know it's not null and cast will succeed)
		Employee otherEmployee = (Employee)other;
		return name == otherEmployee.name && salary == otherEmployee.salary && hireDate == otherEmployee.hireDate;
	}
	
	//shallow-copy
	/*public Employee clone() throws CloneNotSupportedException
	{
		return (Employee)super.clone();
	}*/
	
	//deep-copy
	public Employee clone() throws CloneNotSupportedException
	{
		Employee cloned = (Employee)super.clone();
		//clone mutable fields
		cloned.hireDate = (Date)hireDate.clone();
		
		return cloned;
	}
	
	private Date hireDate;
	private String name;
	private double salary;
}

public class ConsoleRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username = new String();
		String password = new String();
		
		Console cons = System.console();
		if (cons == null)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("User name: ");
			try {
				username = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.print("Password: ");
			try {
				password = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			username = cons.readLine("User name: ");
			password = cons.readPassword("Password: ").toString();
		}
		
		System.out.println(username);
		System.out.println(password);
		
		Employee empl = new Employee(new Date(), "Florin", 1000);
		System.out.println(empl.toString());
		Date newDate = empl.getDate();
		newDate.setHours(10);
		
		System.out.println(empl.toString());
		
		{
			//ArrayList<int> intArray = new ArrayList<int>(100);	//error, only references
			ArrayList<Integer> intArray = new ArrayList<Integer>(100);
			System.out.println("array size: " + intArray.size());	//size is 0
			
			//intArray.set(0,  10);	//will throw IndexOutOfBounds
			intArray.add(10);
			
			System.out.println(intArray);
		}
		
		//Employee clone = empl.clone();	//clone() method is not visible
		try {
			Employee clone = empl.clone();//clone() method is redefined in Employee class
			System.out.print(clone.toString());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}
