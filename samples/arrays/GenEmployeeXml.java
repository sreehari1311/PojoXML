import java.util.ArrayList;

import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;

public class GenEmployeeXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PojoXml pojoxml = PojoXmlFactory.createPojoXml();
		ArrayList list = new ArrayList();
		Employee employee = new Employee();
		 
		employee.setName("Mike");
		employee.setAge(45);
		employee.setSalary(20000.45);
	    
		list.add(getAddress());
		list.add(getAddress());
		list.add(getAddress());
		employee.setAddress(new Address[]{getAddress(),getAddress()});
		
		String xml = pojoxml.getXml(employee);
		System.out.println(xml);

	}
	
	public static Address getAddress(){
		Address address = new Address();
		address.setAddress1("Sagar");
		address.setAddress2("Nerul");
		address.setZip(002345);
		address.setCity("Mumbai");
		return address;
	}

}
