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
		pojoxml.addClassAlias(Employee.class, "EmployeeInfo");
		pojoxml.addFieldAlias(Employee.class, "name", "employeeName");
		pojoxml.addFieldAlias(Employee.class, "address","employeeAddress");
		pojoxml.addClassAlias(Address.class, "employeeAddess");
		pojoxml.addFieldAlias(Address.class, "address2", "townName");
		employee.setName("Mike");
		employee.setAge(45);
		employee.setSalary(20000.45);
	    
		list.add(getAddress());
		list.add(getAddress());
		list.add(getAddress());
		employee.setAddress(list);
		
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
